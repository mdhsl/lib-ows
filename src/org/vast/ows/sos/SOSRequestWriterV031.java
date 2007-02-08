/***************************** BEGIN LICENSE BLOCK ***************************

 The contents of this file are subject to the Mozilla Public License Version
 1.1 (the "License"); you may not use this file except in compliance with
 the License. You may obtain a copy of the License at
 http://www.mozilla.org/MPL/MPL-1.1.html
 
 Software distributed under the License is distributed on an "AS IS" basis,
 WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 for the specific language governing rights and limitations under the License.
 
 The Original Code is the "SensorML DataProcessing Engine".
 
 The Initial Developer of the Original Code is the
 University of Alabama in Huntsville (UAH).
 Portions created by the Initial Developer are Copyright (C) 2006
 the Initial Developer. All Rights Reserved.
 
 Contributor(s): 
    Alexandre Robin <robin@nsstc.uah.edu>
 
******************************* END LICENSE BLOCK ***************************/

package org.vast.ows.sos;

import org.vast.xml.DOMHelper;
import org.vast.ows.OWSException;
import org.vast.ows.gml.GMLEnvelopeWriter;
import org.vast.ows.gml.GMLException;
import org.vast.ows.gml.GMLTimeWriter;
import org.vast.ows.util.Bbox;
import org.vast.ows.util.TimeInfo;
import org.w3c.dom.Element;


/**
 * <p><b>Title:</b><br/>
 * SOS Request Builder v0.0.31
 * </p>
 *
 * <p><b>Description:</b><br/>
 * Provides methods to generate a GET or POST SOS request based
 * on values contained in a SOSQuery object for OWS4 version 0.0.31
 * </p>
 *
 * <p>Copyright (c) 2005</p>
 * @author Alexandre Robin
 * @date Sep 15, 2005
 * @version 1.0
 */
public class SOSRequestWriterV031 extends SOSRequestWriter
{
	protected GMLEnvelopeWriter bboxWriter;
    protected GMLTimeWriter timeWriter;
    
    
	public SOSRequestWriterV031()
	{
        bboxWriter = new GMLEnvelopeWriter();
        timeWriter = new GMLTimeWriter();
	}

	
	@Override
	public String buildURLQuery(SOSQuery query) throws OWSException
	{
		StringBuffer urlBuff;
		
		urlBuff = new StringBuffer(query.getGetServer());
		urlBuff.append("service=SOS");
		urlBuff.append("&version=" + query.getVersion());
		urlBuff.append("&request=" + query.getRequest());
		urlBuff.append("&offering=" + query.getOffering());
        
		urlBuff.append("&time=");
        this.writeTimeArgument(urlBuff, query.getTime());
        
        // add bbox only if specified
        if (query.getBbox() != null && !query.getBbox().isNull())
        {
            urlBuff.append("&bbox=");
            this.writeBboxArgument(urlBuff, query.getBbox());
        }        
        
        // format
		urlBuff.append("&format=" + query.getFormat());
        
        // response mode
        if (query.getResponseMode() != null)
            urlBuff.append("&responseMode=" + this.getResponseMode(query));
        
        // result model
        if (query.getResultModel() != null)
            urlBuff.append("&resultModel=" + query.getResultModel());
        
		// add observable list
		int obsCount = query.getObservables().size();
		for (int i=0; i<obsCount; i++)
		{
			if (i == 0)
				urlBuff.append("&observables=");
			
			urlBuff.append(query.getObservables().get(i));
			
			if (i != obsCount-1)
				urlBuff.append(',');				
		}
		
		return urlBuff.toString();
	}
	
	
	@Override
	public Element buildXMLQuery(DOMHelper dom, SOSQuery query) throws OWSException
	{
		Element elt;
		
        if (query.getVersion().equals("0.0.31"))
            dom.addUserPrefix("sos", "http://www.opengeospatial.net/sos");
        else
            dom.addUserPrefix("sos", "http://www.opengis.net/sos");
		dom.addUserPrefix("ogc", "http://www.opengis.net/ogc");
		dom.addUserPrefix("gml", "http://www.opengis.net/gml");
		dom.addUserPrefix("swe", "http://www.opengeospatial.net/swe");
		
		// root element
		Element rootElt = dom.createElement("sos:GetObservation");
		dom.setAttributeValue(rootElt, "version", query.getVersion());
		dom.setAttributeValue(rootElt, "service", query.getService());
		
		// offering ID
		dom.setElementValue(rootElt, "sos:offering", query.getOffering());
		
		// time period
        try
        {
            TimeInfo timeInfo = query.getTime();
            if (timeInfo != null)
            {
                Element timeElt = timeWriter.writeTime(dom, timeInfo);
                elt = dom.addElement(rootElt, "sos:eventTime/ogc:During");
                elt.appendChild(timeElt);
            }
        }
        catch (GMLException e)
        {
            throw new SOSException("Error while writing time", e);
        }
        
        // bbox
        try
        {
            Bbox bbox = query.getBbox();
            if (bbox != null && !bbox.isNull())
            {
                Element envelopeElt = bboxWriter.writeEnvelope(dom, bbox);
                elt = dom.addElement(rootElt, "sos:featureOfInterest/ogc:BBOX");
                elt.appendChild(envelopeElt);
            }
        }
        catch (GMLException e)
        {
            throw new SOSException("Error while writing bbox", e);
        }
		
		// procedures
		int procCount = query.getProcedures().size();
		for (int i=0; i<procCount; i++)
			dom.setElementValue(rootElt, "+sos:procedure", query.getProcedures().get(i));
		
		// observables
		int obsCount = query.getObservables().size();
		for (int i=0; i<obsCount; i++)
			dom.setElementValue(rootElt, "+sos:observedProperty", query.getObservables().get(i));
		
		// result format
		dom.setElementValue(rootElt, "sos:resultFormat", query.getFormat());
        
		// result model
        if (query.getResultModel() != null)
            dom.setElementValue(rootElt, "sos:resultModel", query.getResultModel());
        
        // response mode (inline, attached, etc...)
        if (query.getResponseMode() != null)
            dom.setElementValue(rootElt, "sos:responseMode", this.getResponseMode(query));
        
		return rootElt;
	}
    
    
    /**
     * Convert response mode to token according to standard
     * @param query
     * @return
     */
    protected String getResponseMode(SOSQuery query)
    {
        switch (query.getResponseMode())
        {
            case INLINE: return "inline";
            case ATTACHED: return "attached";
            case OUT_OF_BAND: return "out-of-band";
            case RESULT_TEMPLATE: return "resultTemplate";
            case RESULT_ONLY: return "resultOnly";
            default: return null;
        }
    }
}