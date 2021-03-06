package nobelprizeviewer;

import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Utility functions.
 * @author Mathew Aloisio
 */
public class Util {
    public static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    
    /**
     * Given a date in the format "yyyy-MM-dd" returns a Date object.
     * @param pDate - The date in the format "yyyy-MM-dd".
     * @see https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
     * @return Date representation of pDate or null if conversion failed.
     */
    public static Date GetDateFromString(String pDate){
        try {
            return DATE_FORMAT.parse(pDate);
        }
        catch (ParseException pException) {
            System.out.println("ParseException!\n" + pException.toString());
        }
        
        return null;
    }
    
    /**
     * Given a word, convert it to Title case.
     * @param word - The word to convert
     * @return Title-cased word (e.g. cat -> Cat)
     */
    public static String ConvertWordToTitleCase(String word) {
        return word.charAt(0) + word.substring(1).toLowerCase();
    }
}
