
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

class Main {
    static void main(String[] args) {
        Project project = ProjectBuilder.builder()
                .build()

        project.pluginManager.apply 'java'
        project.pluginManager.apply 'maow.maowgradle'
    }
}