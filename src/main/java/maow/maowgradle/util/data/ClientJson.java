package maow.maowgradle.util.data;

import java.util.Map;

public class ClientJson {
    private final AssetIndex assetIndex;
    private final String assets;
    private final Map<String, Download> downloads;
    private final String id;
    private final Library[] libraries;

    public ClientJson(AssetIndex assetIndex, String assets, Map<String, Download> downloads, String id, Library[] libraries) {
        this.assetIndex = assetIndex;
        this.assets = assets;
        this.downloads = downloads;
        this.id = id;
        this.libraries = libraries;
    }

    public AssetIndex getAssetIndex() {
        return assetIndex;
    }
    public String getAssets() {
        return assets;
    }
    public Map<String, Download> getDownloads() {
        return downloads;
    }
    public String getId() {
        return id;
    }
    public Library[] getLibraries() {
        return libraries;
    }

    public static class AssetIndex {
        private final String id;
        private final String sha1;
        private final int size;
        private final int totalSize;
        private final String url;

        public AssetIndex(String id, String sha1, int size, int totalSize, String url) {
            this.id = id;
            this.sha1 = sha1;
            this.size = size;
            this.totalSize = totalSize;
            this.url = url;
        }

        public String getId() {
            return id;
        }
        public String getSha1() {
            return sha1;
        }
        public int getSize() {
            return size;
        }
        public int getTotalSize() {
            return totalSize;
        }
        public String getUrl() {
            return url;
        }
    }

    public static class Download {
        private final String sha1;
        private final int size;
        private final String url;

        public Download(String sha1, int size, String url) {
            this.sha1 = sha1;
            this.size = size;
            this.url = url;
        }

        public String getSha1() {
            return sha1;
        }
        public int getSize() {
            return size;
        }
        public String getUrl() {
            return url;
        }
    }

    public static class Library {
        private final String name;

        public Library(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
