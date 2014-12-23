/***************************** BEGIN LICENSE BLOCK ***************************

 The contents of this file are subject to the Mozilla Public License Version
 1.1 (the "License"); you may not use this file except in compliance with
 the License. You may obtain a copy of the License at
 http://www.mozilla.org/MPL/MPL-1.1.html
 
 Software distributed under the License is distributed on an "AS IS" basis,
 WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 for the specific language governing rights and limitations under the License.
 
 The Original Code is the "OGC Service Framework".
 
 The Initial Developer of the Original Code is Sensia Software LLC.
 Portions created by the Initial Developer are Copyright (C) 2014
 the Initial Developer. All Rights Reserved.
 
 Please Contact Alexandre Robin <alex.robin@sensiasoftware.com> or
 Mike Botts <mike.botts@botts-inc.net> for more information.

 Contributor(s): 
    Alexandre Robin <alex.robin@sensiasoftware.com>
 
******************************* END LICENSE BLOCK ***************************/

package net.opengis.fes.v20.impl;

import net.opengis.fes.v20.GMLExpression;


public class GMLExpressionImpl implements GMLExpression
{
    Object gmlObj;
    
    
    public GMLExpressionImpl(Object gmlObj)
    {
        this.gmlObj = gmlObj;
    }
    
    
    @Override
    public Object getGmlObject()
    {
        return gmlObj;
    }


    @Override
    public void setGmlObject(Object gmlObj)
    {
        this.gmlObj = gmlObj;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof GMLExpression))
            return false;
        
        if (!getGmlObject().equals(((GMLExpression)obj).getGmlObject()))
            return false;
        
        return true;
    }


    @Override
    public String toString()
    {
        return gmlObj.toString();
    }
}
