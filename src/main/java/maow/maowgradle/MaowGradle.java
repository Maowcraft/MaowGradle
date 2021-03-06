package maow.maowgradle;

import maow.maowgradle.task.*;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MaowGradle implements Plugin<Project> {
    @Override
    public void apply(@NotNull Project project) {
        MaowGradlePluginExtension extension = project.getExtensions().create("minecraft", MaowGradlePluginExtension.class);

        File libsDir = new File("libs");
        if (!libsDir.exists()) {
            try {
                Files.createDirectory(libsDir.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        project.getTasks().register("downloadJar", DownloadJarTask.class);
        project.getTasks().register("downloadMappings", DownloadMappingsTask.class);
        project.getTasks().register("remapJar", RemapJarTask.class);
        project.getTasks().register("grabDependencies", GrabDependenciesTask.class);
        project.getTasks().register("initMaowGradle", InitTask.class);

        InitTask initTask = (InitTask) project.getTasks().getByName("initMaowGradle");
        initTask.doInit();
    }
}
