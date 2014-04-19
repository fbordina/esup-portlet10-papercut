package org.esupportail.papercut.esup_portlet10_papercut.web.springmvc;

import java.util.Map;

import javax.annotation.Resource;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.log4j.Logger;
import org.esupportail.papercut.esup_portlet10_papercut.domain.UserPapercutInfos;
import org.esupportail.papercut.esup_portlet10_papercut.domain.beans.User;
import org.esupportail.papercut.esup_portlet10_papercut.services.EsupPaperCutService;
import org.esupportail.papercut.esup_portlet10_papercut.services.auth.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

@Controller
public class WebController extends AbastractExceptionController {
    
	static Logger log = Logger.getLogger(WebController.class);
	
	private final static String PREF_PAPERCUT_CONTEXT = "paperCutContext";
	
	private final static String PREF_PAPERCUT_USER_UID_ATTR = "papercutUserUidAttr";
	
	private final static String PREF_PAPERCUT_USER_MAIL_ATTR = "userEmail";
	
	private final static String PREF_VALIDATE_AFTER_REDIRECT = "validatePayboxJustWithRedirection";
	
	@Resource 
	Map<String, EsupPaperCutService> esupPaperCutServices;
	
    @Autowired
	private Authenticator authenticator;
    
    @RequestMapping("VIEW")
    protected ModelAndView renderView(RenderRequest request, RenderResponse response) throws Exception {
        
    	ModelMap model = new ModelMap();
    	
    	model.put("htmlHeader", request.getPreferences().getValue("htmlHeader", ""));
    	model.put("htmlFooter", request.getPreferences().getValue("htmlFooter", ""));
    	model.put("payboxMontantMin", request.getPreferences().getValue("payboxMontantMin", "0.5"));
    	model.put("payboxMontantMax", request.getPreferences().getValue("payboxMontantMax", "5.0"));
    	model.put("payboxMontantStep", request.getPreferences().getValue("payboxMontantStep", "0.5"));	
    	model.put("payboxMontantDefaut", request.getPreferences().getValue("payboxMontantDefaut", "2.0"));
    	
    	String paperCutContext = request.getPreferences().getValue(PREF_PAPERCUT_CONTEXT, null);
        EsupPaperCutService esupPaperCutService = esupPaperCutServices.get(paperCutContext);
        log.info("PapercutContyext : " + paperCutContext);
        
        String uid = getUid(request);
        String userMail = getUserMail(request);
    	
    	UserPapercutInfos userPapercutInfos = esupPaperCutService.getUserPapercutInfos(uid);   		
		model.put("userPapercutInfos", userPapercutInfos);
		
		String portletContextPath = response.createRenderURL().toString();

    	/*PayBoxForm payBoxForm = esupPaperCutService.getPayBoxForm(uid, userMail, 2, paperCutContext, portletContextPath);
    	model.put("payBoxForm", payBoxForm);*/
    	
    	model.put("isAdmin", isAdmin(request));
    	
    	User userFromEC2 = authenticator.getUser();
    	model.put("userFromEC2", userFromEC2);

        return new ModelAndView("view", model);
    }

    @RequestMapping("ABOUT")
	public ModelAndView renderAboutView(RenderRequest request, RenderResponse response) throws Exception {
		ModelMap model = new ModelMap();
		return new ModelAndView("about", model);
	}
    
    private String getUid(PortletRequest request) {
		String uidAttr = request.getPreferences().getValue(PREF_PAPERCUT_USER_UID_ATTR, null);
		Map<String,String> userinfo = (Map<String,String>)request.getAttribute(PortletRequest.USER_INFO);
		String uid = userinfo.get(uidAttr);
		return uid;
	}

    
	private String getUserMail(PortletRequest request) {
		String mailAttr = request.getPreferences().getValue(PREF_PAPERCUT_USER_MAIL_ATTR, null);
		Map<String,String> userinfo = (Map<String,String>)request.getAttribute(PortletRequest.USER_INFO);
		String mail = userinfo.get(mailAttr);
		return mail;
	}
	
	private boolean isAdmin(PortletRequest request) {
		return request.isUserInRole("esupPapercutAdmin");
	}
    

}
