package net.opengis.fes.v20;

import java.util.List;


/**
 * POJO class for XML type PropertyIsLikeType(@http://www.opengis.net/fes/2.0).
 *
 * This is a complex type.
 */
public interface PropertyIsLike extends ComparisonOps
{
    
    /**
     * Gets 1st operand property
     */
    public Expression getOperand1();
    
    
    /**
     * Sets 1st operand property
     */
    public void setOperand1(Expression exp);
    
    
    /**
     * Gets 2nd operand property
     */
    public Expression getOperand2();
    
    
    /**
     * Sets 2nd operand property
     */
    public void setOperand2(Expression exp);
    
    
    /**
     * Gets the wildCard property
     */
    public String getWildCard();
    
    
    /**
     * Sets the wildCard property
     */
    public void setWildCard(String wildCard);
    
    
    /**
     * Gets the singleChar property
     */
    public String getSingleChar();
    
    
    /**
     * Sets the singleChar property
     */
    public void setSingleChar(String singleChar);
    
    
    /**
     * Gets the escapeChar property
     */
    public String getEscapeChar();
    
    
    /**
     * Sets the escapeChar property
     */
    public void setEscapeChar(String escapeChar);
}
