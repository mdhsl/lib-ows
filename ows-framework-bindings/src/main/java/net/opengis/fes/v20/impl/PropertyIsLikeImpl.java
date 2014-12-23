package net.opengis.fes.v20.impl;

import net.opengis.fes.v20.Expression;
import net.opengis.fes.v20.PropertyIsLike;


public class PropertyIsLikeImpl extends ComparisonOpsImpl implements PropertyIsLike
{
    static final long serialVersionUID = 1L;
    protected Expression expression1;
    protected Expression expression2;
    protected String wildCard = "";
    protected String singleChar = "";
    protected String escapeChar = "";
    
    
    public PropertyIsLikeImpl()
    {
    }


    @Override
    public Expression getOperand1()
    {
        return expression1;
    }
    
    
    public void setOperand1(Expression exp)
    {
        this.expression1 = exp;
    }
    
    
    @Override
    public Expression getOperand2()
    {
        return expression2;
    }

    
    @Override
    public void setOperand2(Expression exp)
    {
        this.expression2 = exp;
    }
    
    
    @Override
    public String getWildCard()
    {
        return wildCard;
    }
    
    
    @Override
    public void setWildCard(String wildCard)
    {
        this.wildCard = wildCard;
    }
    
    
    @Override
    public String getSingleChar()
    {
        return singleChar;
    }
    
    
    @Override
    public void setSingleChar(String singleChar)
    {
        this.singleChar = singleChar;
    }
    
    
    @Override
    public String getEscapeChar()
    {
        return escapeChar;
    }
    
    
    @Override
    public void setEscapeChar(String escapeChar)
    {
        this.escapeChar = escapeChar;
    }
}
