package maow.maowgradle.util;

import java.io.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

public class IOUtil {
    public static void extractTinyFileFromJar(File file) {
        try {
            JarFile jar = new JarFile(file);
            JarInputStream jis = new JarInputStream(new FileInputStream(file));
            JarEntry entry;
            while ((entry = jis.getNextJarEntry()) != null) {
                if (entry.getName().endsWith(".tiny")) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("mappings/" + file.getName().substring(0, file.getName().indexOf('.')) + ".tiny"));
                    InputStreamReader reader = new InputStreamReader(jar.getInputStream(entry));
                    while (reader.ready()) {
                        writer.write(reader.read());
                    }
                    writer.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
