package maow.maowgradle.task;

import maow.maowgradle.MaowGradlePluginExtension;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;

public class InitTask extends DefaultTask {
    @TaskAction
    public void doInit() {
        MaowGradlePluginExtension extension = getProject().getExtensions().create("minecraft", MaowGradlePluginExtension.class);
        downloadJar(extension.getVersion());
        downloadMappings(extension.getMappings(), extension.getIntermediaryMappings());
        remapJar(extension.getVersion());
        grabDependencies(extension.getVersion());
        getProject().getLogger().lifecycle("- Initialization finished.");
    }

    // Utilities

    private void downloadJar(String version) {
        DownloadJarTask downloadJarTask = (DownloadJarTask) getProject().getTasks().getByName("downloadJar");
        downloadJarTask.setVersion(version);
        downloadJarTask.downloadJar();
    }

    private void downloadMappings(String mappings, String intermediaryMappings) {
        DownloadMappingsTask downloadMappingsTask = (DownloadMappingsTask) getProject().getTasks().getByName("downloadMappings");
        if (intermediaryMappings.equals("")) {
            downloadMappingsTask.setIntermediaryUrl(intermediaryMappings);
        }
        if (mappings.equals("")) {
            downloadMappingsTask.setMappingsUrl(mappings);
        }
        downloadMappingsTask.downloadMappings();
    }

    private void remapJar(String version) {
        RemapJarTask remapJarTask = (RemapJarTask) getProject().getTasks().getByName("remapJar");
        remapJarTask.setIntermediaryFile(new File("mappings/intermediary.tiny"));
        remapJarTask.setMappingsFile(new File("mappings/mappings.tiny"));
        remapJarTask.setJarFile(new File("libs/minecraft-client-" + version + ".jar"));
        remapJarTask.remapJar();
    }

    private void grabDependencies(String version) {
        GrabDependenciesTask grabDependenciesTask = (GrabDependenciesTask) getProject().getTasks().getByName("grabDependencies");
        grabDependenciesTask.setVersion(version);
        grabDependenciesTask.grabDependencies();
    }
}
