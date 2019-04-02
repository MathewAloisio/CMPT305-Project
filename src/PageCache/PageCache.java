package PageCache;

import java.util.HashMap;

import javafx.scene.layout.GridPane;

/**
 * Caches images, removing them from the cache when they haven't been used in a given # of seconds.
 * @author Mathew Aloisio
 */
public class PageCache {
    private static final HashMap<Integer, GridPane> PAGE_MAP = new HashMap<>();
    
    /**
     * Clears the page cache completely.
     */
    public static void Clear() {
        PAGE_MAP.clear();
    }
    
    /**
     * Requests a page from the cache.
     * @param pPageIndex - The page # to request.
     * @return GridPane - The cached page, or null if this page # isn't cached.
     */
    public static GridPane RequestPage(int pPageIndex) {
        return PAGE_MAP.containsKey(pPageIndex) ? PAGE_MAP.get(pPageIndex) : null;
    }
    
    /**
     * Adds a page (GridPane) to the cache @ page # index pPageNumber.
     * @param pPageIndex - The page #.
     * @param pPage - The GridPane object to cache.
     */
    public static void CachePage(int pPageIndex, GridPane pPage) {
        PAGE_MAP.put(pPageIndex, pPage);
    }
}
