package maow.maowgradle.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.util.Objects;

public class HttpUtil {
    private static final OkHttpClient client = new OkHttpClient();

    public static String getJsonFromPage(String url) {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{}";
    }

    public static void downloadFileFromPage(String url, File output) {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            InputStream is = Objects.requireNonNull(response.body()).byteStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            FileOutputStream fos = new FileOutputStream(output);

            byte[] buffer = new byte[4096];
            int count;

            while ((count = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, count);
            }

            fos.flush();

            fos.close();
            bis.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
