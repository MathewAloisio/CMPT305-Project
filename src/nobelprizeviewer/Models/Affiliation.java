package nobelprizeviewer.Models;

/**
 *
 * @author Mathew Aloisio
 */
public class Affiliation {
    public final String m_Name;
    public final String m_City;
    public final String m_Country;
    
    public Affiliation(String pName, String pCity, String pCountry) {
        m_Name = pName;
        m_City = pCity;
        m_Country = pCountry;
    }
}
