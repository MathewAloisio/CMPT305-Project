package GoogleImageSearch;

import java.io.IOException;

import java.net.URL;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Modified version of Brian Brookewell's GoogleImageSearch class.
 * The class uses jsoup to get images from a google image search.
 * @author Brian Brookewell
 */
public class GoogleImageSearch {
    /**
     * Find Image takes a search term as a parameter (name of the Nobel Laureate),
     * substitutes '+' for spaces and performs a google search for the image.  The
     * returned HTML is parsed for the actual URL and this is used to load a BufferedImage
     * 
     * @param searchTerm Name being sought
     * @return BufferedImage of the search term
     * 
     * @throws IOException If the image can't be read
     */
    public static BufferedImage FindImage(String searchTerm) throws IOException {
        searchTerm = searchTerm.trim().replace(" ", "+")+"+wikimedia";

        String searchURL = "https://www.google.com/search?q=" + searchTerm + "&source=lnms&tbm=isch&num=1";

        //without proper User-Agent, we will get 403 error
        Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();

        //Extract the google search name from the HTML sent using the connect
        String body = doc.body().toString();
        
        int start = body.indexOf("images_table");
        start = body.indexOf("<img", start);
        start = body.indexOf("src=\"", start) + 4;
        int finish = body.indexOf("\"", start+1);

        System.out.println (body.substring(start + 1, finish));
        
        URL imageURL = new URL(body.substring(start + 1, finish));

        BufferedImage image = ImageIO.read(imageURL);

        return image;
    }
}
