package maow.maowgradle.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

import java.io.File;

public class InitTask extends DefaultTask {
    private String version;
    private String mappings;
    private String intermediaryMappings;

    public void setVersion(String version) {
        this.version = version;
    }

    @TaskAction
    public void doInit() {
        downloadJar();
        downloadMappings();
        remapJar();
        grabDependencies();
        getProject().getLogger().lifecycle("- Initialization finished.");
    }

    // Utilities

    private void downloadJar() {
        DownloadJarTask downloadJarTask = (DownloadJarTask) getProject().getTasks().getByName("downloadJar");
        downloadJarTask.setVersion(version);
        downloadJarTask.downloadJar();
    }

    private void downloadMappings() {
        DownloadMappingsTask downloadMappingsTask = (DownloadMappingsTask) getProject().getTasks().getByName("downloadMappings");
        if (intermediaryMappings.equals("")) {
            downloadMappingsTask.setIntermediaryUrl(intermediaryMappings);
        }
        if (mappings.equals("")) {
            downloadMappingsTask.setMappingsUrl(mappings);
        }
        downloadMappingsTask.downloadMappings();
    }

    private void remapJar() {
        RemapJarTask remapJarTask = (RemapJarTask) getProject().getTasks().getByName("remapJar");
        remapJarTask.setIntermediaryFile(new File("mappings/intermediary.tiny"));
        remapJarTask.setMappingsFile(new File("mappings/mappings.tiny"));
        remapJarTask.setJarFile(new File("libs/minecraft-client-" + version + ".jar"));
        remapJarTask.remapJar();
    }

    private void grabDependencies() {
        GrabDependenciesTask grabDependenciesTask = (GrabDependenciesTask) getProject().getTasks().getByName("grabDependencies");
        grabDependenciesTask.setVersion(version);
        grabDependenciesTask.grabDependencies();
    }
}
