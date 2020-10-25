package maow.maowgradle.util;

import maow.maowgradle.util.data.ClientJson;
import maow.maowgradle.util.data.VersionManifest;

public class VersionUtil {
    public static final String VERSION_MANIFEST_URL = "https://launchermeta.mojang.com/mc/game/version_manifest.json";

    public static VersionManifest findVersionManifest() {
        return GsonUtil.fromJson(VERSION_MANIFEST_URL, VersionManifest.class);
    }

    public static ClientJson findClientJson(VersionManifest.Version version) {
        String url = version.getUrl();
        return GsonUtil.fromJson(url, ClientJson.class);
    }

    public static String findClientJarUrl(String version) {
        ClientJson json = findClientJson(findVersionManifest().getVersion(version));
        return json.getDownloads().get("client").getUrl();
    }
}
