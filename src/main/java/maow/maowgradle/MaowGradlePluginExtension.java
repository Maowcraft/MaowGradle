package maow.maowgradle;

public class MaowGradlePluginExtension {
    private String version = "1.16.4-pre2";
    private String mappings = "";
    private String intermediaryMappings = "";

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
