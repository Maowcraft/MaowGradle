package maow.maowgradle.task;

import net.fabricmc.tinyremapper.OutputConsumerPath;
import net.fabricmc.tinyremapper.TinyRemapper;
import net.fabricmc.tinyremapper.TinyUtils;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;

public class RemapJarTask extends DefaultTask {
    private File jarFile;
    private File intermediaryFile = new File("mappings/intermediary.tiny");
    private File mappingsFile = new File("mappings/mappings.tiny");

    public void setJarFile(File jarFile) {
        this.jarFile = jarFile;
    }

    public void setIntermediaryFile(File intermediaryFile) {
        this.intermediaryFile = intermediaryFile;
    }

    public void setMappingsFile(File mappingsFile) {
        this.mappingsFile = mappingsFile;
    }

    @TaskAction
    public void remapJar() {
        if (intermediaryFile.exists() && mappingsFile.exists()) {
            File intermediaryJar = new File("libs/intermediary-" + jarFile.getName());
            File namedJar = new File("libs/remapped-" + jarFile.getName());

            if (!intermediaryJar.exists()) {
                map(intermediaryFile, jarFile, intermediaryJar, "official", "intermediary");
            }
            if (!namedJar.exists() && intermediaryJar.exists()) {
                map(mappingsFile, intermediaryJar, namedJar, "intermediary", "named");
            }
            getProject().getConfigurations().getByName("implementation").getDependencies()
                    .add(getProject().getDependencies().create(getProject().files("libs/remapped-" + jarFile.getName())));
            getProject().getLogger().lifecycle("- Remapping finished.");
        } else {
            getProject().getLogger().error("- Could not find intermediary/named mapping files.");
        }
    }

    // Utilities

    private void map(File mappings, File input, File output, String fromM, String toM) {
        TinyRemapper remapper = TinyRemapper.newRemapper()
                .withMappings(TinyUtils.createTinyMappingProvider(mappings.toPath(), fromM, toM))
                .build();
        try (OutputConsumerPath outputConsumer = new OutputConsumerPath.Builder(output.toPath()).build()) {
            outputConsumer.addNonClassFiles(input.toPath());
            remapper.readInputs(input.toPath());
            remapper.apply(outputConsumer);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            remapper.finish();
        }
        getProject().getLogger().lifecycle("- Remapped jar " + input.getName() + " from mappings \"" + fromM + "\" to mappings \"" + toM + "\"");
    }
}