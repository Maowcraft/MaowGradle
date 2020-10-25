package maow.maowgradle.util.data;

public class VersionManifest {
    private final Version[] versions;

    public VersionManifest(Version[] versions) {
        this.versions = versions;
    }

    public Version getVersion(String id) {
        for (Version version : versions) {
            if (version.getId().equals(id)) {
                return version;
            }
        }
        return null;
    }

    public static class Version {
        private final String id;
        private final String type;
        private final String url;
        private final String time;
        private final String releaseTime;

        public Version(String id, String type, String url, String time, String releaseTime) {
            this.id = id;
            this.type = type;
            this.url = url;
            this.time = time;
            this.releaseTime = releaseTime;
        }

        public String getId() {
            return id;
        }
        public String getType() {
            return type;
        }
        public String getUrl() {
            return url;
        }
        public String getTime() {
            return time;
        }
        public String getReleaseTime() {
            return releaseTime;
        }
    }
}
