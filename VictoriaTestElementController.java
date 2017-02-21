package uk.co.archant.layout.element.victoriatest;

import uk.co.archant.rendering.RenderController;
import uk.co.polopoly.RenderContext;
import com.polopoly.cm.ContentId;
import com.polopoly.cm.app.policy.SingleValuePolicy;
import com.polopoly.cm.client.CMException;
import com.polopoly.cm.policy.Policy;
import com.polopoly.cm.policy.PolicyCMServer;
import com.polopoly.cm.app.policy.SelectPolicy;
import com.polopoly.model.ModelWrite;
import com.polopoly.render.RenderRequest;
import com.polopoly.siteengine.dispatcher.ControllerContext;
import com.polopoly.siteengine.model.TopModel;
import edu.emory.mathcs.backport.java.util.Collections;

public class VictoriaTestElementController extends RenderController
{
    @Override
	public void populateModelBeforeCacheKey(RenderRequest request, TopModel m, ControllerContext context)    
	{
        /*Set variable and declare defaults*/
        ModelWrite localModel = m.getLocal();
		PolicyCMServer cmServer;
		ContentId contentId;
		Policy thisPolicy;
        String paragraphInformation = "";
        
       try 
       {
            /*Get the policy associated with this contentId*/
			cmServer = getCMServer(context);
			contentId = getRenderedContentId(m, context);
			thisPolicy = cmServer.getPolicy(contentId);
            
            //get the paragraph information and assign it to a variable
            paragraphInformation = ((SingleValuePolicy)thisPolicy.getChildPolicy("paragraphInformation")).getValue();
       
            //regex to remove all non alphanumeric characters from the string
            paragraphInformation = paragraphInformation.replaceAll("[^a-zA-Z0-9]", "");
       }
       catch (CMException e) 
	   {
			// TODO Auto-generated catch block
			e.printStackTrace();
	   }
       
       //Assign the paragraph to a variable to the local model which is accessible within the associated velocity file i.e. $m.local.incidents
	   localModel.setAttribute("paragraphInformation", paragraphInformation);
    }
}
