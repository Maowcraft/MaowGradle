package maow.maowgradle.util;

import com.google.gson.Gson;

public class GsonUtil {
    private static final Gson gson = new Gson();

    public static <T> T fromJson(String url, Class<T> type) {
        return gson.fromJson(HttpUtil.getJsonFromPage(url), type);
    }
}
