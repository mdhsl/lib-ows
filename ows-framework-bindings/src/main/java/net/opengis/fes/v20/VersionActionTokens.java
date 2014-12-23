package net.opengis.fes.v20;



/**
 * POJO class for XML type VersionActionTokens(@http://www.opengis.net/fes/2.0).
 *
 */
public enum VersionActionTokens
{
    FIRST("FIRST"),
    LAST("LAST"),
    PREVIOUS("PREVIOUS"),
    NEXT("NEXT"),
    ALL("ALL");
    
    private final String text;
    
    
    
    /**
     * Private constructor for storing string representation
     */
    private VersionActionTokens(String s)
    {
        this.text = s;
    }
    
    
    
    /**
     * To convert an enum constant to its String representation
     */
    public String toString()
    {
        return text;
    }
    
    
    
    /**
     * To get the enum constant corresponding to the given String representation
     */
    public static VersionActionTokens fromString(String s)
    {
        if (s.equals("FIRST"))
            return FIRST;
        else if (s.equals("LAST"))
            return LAST;
        else if (s.equals("PREVIOUS"))
            return PREVIOUS;
        else if (s.equals("NEXT"))
            return NEXT;
        else if (s.equals("ALL"))
            return ALL;
        
        throw new IllegalArgumentException("Invalid token " + s + " for enum VersionActionTokens");
    }
}
