package maow.maowgradle.task;

import maow.maowgradle.util.HttpUtil;
import maow.maowgradle.util.VersionUtil;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.*;

public class DownloadJarTask extends DefaultTask {
    private String version;

    public void setVersion(String version) {
        this.version = version;
    }

    @TaskAction
    public void downloadJar() {
        File output = new File("libs/minecraft-client-" + version + ".jar");
        if (!output.exists()) {
            getProject().getLogger().lifecycle("- Downloading jar for " + version);
            String clientJarUrl = VersionUtil.findClientJarUrl(version);
            getProject().getLogger().lifecycle("- Found client.jar URL: " + clientJarUrl);
            HttpUtil.downloadFileFromPage(clientJarUrl, output);
        }
        getProject().getLogger().lifecycle("- Downloaded client jar.");
    }
}
