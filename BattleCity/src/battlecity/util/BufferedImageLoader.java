package battlecity.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.imageio.ImageIO;

/**
 *
 * @author xGod
 */
public class BufferedImageLoader {

    private static BufferedImageLoader loader;

    private Map<String, BufferedImage> bufferMap;

    public BufferedImageLoader() {
        bufferMap = new HashMap<>();
    }

    public Map<String, BufferedImage> getBufferMap() {
        return bufferMap;
    }

    public static BufferedImageLoader getInstance() {
        if (loader == null) {
            loader = new BufferedImageLoader();
        }
        return loader;
    }
    
    public void loadBuffer(String path, String key){
        loadBuffer(new File(path), key);
    }

    public void loadBuffer(File file, String key) {
        if (key == null || key.trim().isEmpty()) {
            System.err.println("Error, no key name valid ...");
            return;
        }
        if (file == null || !file.exists()) {
            System.err.println("Error, no image file found ...");
            return;
        }
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (IOException ex) {
            System.err.println("Error loading the image " + file.getAbsolutePath());
        }
        if (bi != null) {
            bufferMap.put(key, bi);
            System.out.println("Loaded '" + key + "': " + file.getAbsoluteFile());
        } else {
            System.err.println("BufferedImage not loaded.");
        }
    }

    public void loadBufferFromProperties(String properties) {
        Properties prop = new Properties();
        try {
            InputStream is = new FileInputStream(properties);
            prop.load(is);
        } catch (IOException ex) {
            System.err.println("Error, unable to find " + properties);
            return;
        }
        Enumeration<?> e = prop.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String)e.nextElement();
            String value = prop.getProperty(key);
            loadBuffer(value, key);
        }
    }
    
}
