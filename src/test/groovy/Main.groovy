import maow.maowgradle.MaowGradlePluginExtension
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

class Main {
    static void main(String[] args) {
        Project project = ProjectBuilder.builder()
                .build()

        project.extensions.create("minecraft", MaowGradlePluginExtension)
        MaowGradlePluginExtension ex = project.extensions.getByName("minecraft") as MaowGradlePluginExtension
        ex.version = "1.16.4-pre2"
        ex.mappings = "https://maven.fabricmc.net/net/fabricmc/yarn/1.16.4-pre2%2Bbuild.1/yarn-1.16.4-pre2%2Bbuild.1-v2.jar"
        ex.intermediaryMappings = "https://maven.fabricmc.net/net/fabricmc/intermediary/1.16.4-pre2/intermediary-1.16.4-pre2-v2.jar"

        project.pluginManager.apply 'java'
        project.pluginManager.apply 'maow.maowgradle'
    }
}