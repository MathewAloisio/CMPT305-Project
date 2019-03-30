package ImageCache;

import GoogleImageSearch.GoogleImageSearch;

import java.io.IOException;

import java.util.HashMap;

import java.awt.image.BufferedImage;

import javafx.scene.image.Image;
import javafx.embed.swing.SwingFXUtils;

/**
 * Caches images, removing them from the cache when they haven't been used in a given # of seconds.
 * @author Mathew Aloisio
 */
public class ImageCache {
    private static final long NANOSECONDS_TIL_CLEANUP = 15000000000L; // After NANOSECONDS_TIL_CLEANUPns without a request, an image will be removed from the cache.
    private static final HashMap<String, ImageCacheEntry> IMAGE_MAP = new HashMap<>();
    
    public static Image RequestImage(String pLaureateName) {
        // Checek if the image is available in the cache, if so return it and update the last request time.
        if (IMAGE_MAP.containsKey(pLaureateName)) {
            ImageCacheEntry entry = IMAGE_MAP.get(pLaureateName);
            entry.m_LastRequestTime = System.nanoTime();
            
            return entry.m_Image;
        }
        
        // Otherwise, since it wasn't in the cache load buffered image.
        BufferedImage bufferedImage = null;   
        try {
            bufferedImage = GoogleImageSearch.FindImage(pLaureateName);
        }
        catch (IOException pException) {
            System.out.println("IOException! Failed to find image for \"" + pLaureateName + "\".\n" +  pException.toString());
        }
        
        if (bufferedImage != null) {
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            
            // Add image to the cache.
            IMAGE_MAP.put(pLaureateName, new ImageCacheEntry(image, System.nanoTime()));
            
            return image;
        }

        return null;
    }
    
    public static void Update() {
        for (HashMap.Entry<String, ImageCacheEntry> entry : IMAGE_MAP.entrySet()) {
            long difference = System.nanoTime() - entry.getValue().m_LastRequestTime;
            if (difference > NANOSECONDS_TIL_CLEANUP) {
                IMAGE_MAP.remove(entry.getKey());
                break; // Only remove a max of 1 image per update from the cache to avoid a slow down.
            }
        }
    }
}
