package ImageCache;

import javafx.scene.image.Image;

/**
 * ImageCacheEntry used by the ImageCache
 * @author Mathew Aloisio
 */
public class ImageCacheEntry {
    public final Image m_Image;     // The image itself.
    public long m_LastRequestTime;  // The last time the image was requested.

    public ImageCacheEntry(Image pImage, long pRequestTime) {
        m_Image = pImage;
        m_LastRequestTime = pRequestTime;
    }
}
