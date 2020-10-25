package maow.maowgradle.task;

import maow.maowgradle.util.HttpUtil;
import maow.maowgradle.util.IOUtil;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DownloadMappingsTask extends DefaultTask {
    private String intermediaryUrl = "https://maven.fabricmc.net/net/fabricmc/intermediary/1.16.4-pre2/intermediary-1.16.4-pre2-v2.jar";
    private String mappingsUrl = "https://maven.fabricmc.net/net/fabricmc/yarn/1.16.4-pre2%2Bbuild.1/yarn-1.16.4-pre2%2Bbuild.1-v2.jar";

    public void setIntermediaryUrl(String intermediaryUrl) {
        this.intermediaryUrl = intermediaryUrl;
    }

    public void setMappingsUrl(String mappingsUrl) {
        this.mappingsUrl = mappingsUrl;
    }

    @TaskAction
    public void downloadMappings() {
        File mappingJarsDir = new File("mappings/jars");
        if (!mappingJarsDir.exists()) {
            try {
                Files.createDirectories(mappingJarsDir.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File intermediaryJar = new File("mappings/jars/intermediary.jar");
        File mappingsJar = new File("mappings/jars/mappings.jar");

        if (!intermediaryJar.exists()) {
            HttpUtil.downloadFileFromPage(intermediaryUrl, intermediaryJar);
            getProject().getLogger().lifecycle("- Downloaded intermediary mappings jar.");
            if (!new File("mappings/intermediary.tiny").exists()) {
                IOUtil.extractTinyFileFromJar(intermediaryJar);
                getProject().getLogger().lifecycle("- Extracting intermediary mappings jar.");
            }
        }
        if (!mappingsJar.exists()) {
            HttpUtil.downloadFileFromPage(mappingsUrl, mappingsJar);
            getProject().getLogger().lifecycle("- Downloaded named mappings jar.");
            if (!new File("mappings/mappings.tiny").exists()) {
                IOUtil.extractTinyFileFromJar(mappingsJar);
                getProject().getLogger().lifecycle("- Extracting named mappings jar.");
            }
        }

        getProject().getLogger().lifecycle("- Downloaded and extracted mappings.");
    }
}
