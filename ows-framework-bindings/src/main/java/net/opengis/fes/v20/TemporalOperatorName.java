package net.opengis.fes.v20;



/**
 * POJO class for XML type TemporalOperatorNameType(@http://www.opengis.net/fes/2.0).
 *
 */
public enum TemporalOperatorName
{
    AFTER("After"),
    BEFORE("Before"),
    BEGINS("Begins"),
    BEGUN_BY("BegunBy"),
    T_CONTAINS("TContains"),
    DURING("During"),
    T_EQUALS("TEquals"),
    T_OVERLAPS("TOverlaps"),
    MEETS("Meets"),
    OVERLAPPED_BY("OverlappedBy"),
    MET_BY("MetBy"),
    ENDS("Ends"),
    ENDED_BY("EndedBy");
    
    private final String text;
    
    
    
    /**
     * Private constructor for storing string representation
     */
    private TemporalOperatorName(String s)
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
    public static TemporalOperatorName fromString(String s)
    {
        if (s.equals("After"))
            return AFTER;
        else if (s.equals("Before"))
            return BEFORE;
        else if (s.equals("Begins"))
            return BEGINS;
        else if (s.equals("BegunBy"))
            return BEGUN_BY;
        else if (s.equals("TContains"))
            return T_CONTAINS;
        else if (s.equals("During"))
            return DURING;
        else if (s.equals("TEquals"))
            return T_EQUALS;
        else if (s.equals("TOverlaps"))
            return T_OVERLAPS;
        else if (s.equals("Meets"))
            return MEETS;
        else if (s.equals("OverlappedBy"))
            return OVERLAPPED_BY;
        else if (s.equals("MetBy"))
            return MET_BY;
        else if (s.equals("Ends"))
            return ENDS;
        else if (s.equals("EndedBy"))
            return ENDED_BY;
        
        throw new IllegalArgumentException("Invalid token " + s + " for enum TemporalOperatorName");
    }
}
