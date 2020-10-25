package maow.maowgradle.task;

import maow.maowgradle.util.VersionUtil;
import maow.maowgradle.util.data.ClientJson;
import maow.maowgradle.util.data.VersionManifest;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class GrabDependenciesTask extends DefaultTask {
    private String version;

    public void setVersion(String version) {
        this.version = version;
    }

    @TaskAction
    public void grabDependencies() {
        VersionManifest.Version versionObj = VersionUtil.findVersionManifest().getVersion(version);
        ClientJson clientJson = VersionUtil.findClientJson(versionObj);
        for (ClientJson.Library library : clientJson.getLibraries()) {
            getProject().getConfigurations().getByName("implementation").getDependencies().add(getProject().getDependencies().create(library.getName()));
        }
        getProject().getLogger().lifecycle("- Added Minecraft dependencies to classpath.");
    }
}
