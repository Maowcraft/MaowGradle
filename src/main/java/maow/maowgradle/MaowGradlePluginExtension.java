package maow.maowgradle;

public class MaowGradlePluginExtension {
    private String version = "1.16.4-pre2";
    private String mappings = "https://maven.fabricmc.net/net/fabricmc/yarn/1.16.4-pre2%2Bbuild.1/yarn-1.16.4-pre2%2Bbuild.1-v2.jar";
    private String intermediaryMappings = "https://maven.fabricmc.net/net/fabricmc/intermediary/1.16.4-pre2/intermediary-1.16.4-pre2-v2.jar";

    public void setVersion(String version) {
        this.version = version;
    }
    public void setMappings(String mappings) {
        this.mappings = mappings;
    }
    public void setIntermediaryMappings(String intermediaryMappings) {
        this.intermediaryMappings = intermediaryMappings;
    }

    public String getVersion() {
        return version;
    }
    public String getMappings() {
        return mappings;
    }
    public String getIntermediaryMappings() {
        return intermediaryMappings;
    }
}
