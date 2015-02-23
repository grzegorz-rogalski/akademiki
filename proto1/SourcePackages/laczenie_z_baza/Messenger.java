/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package laczenie_z_baza;

import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Komputer
 */
public final class Messenger {
    public static String getStringFromBundle(String bundle, String message)
    {
        return ResourceBundle.getBundle(bundle).getString(message);
    }
    
    public static void addErrorMessage(Exception ex, String defailtMsg) 
    {
        String msg = ex.getLocalizedMessage();
        if(msg != null && msg.length()>0)
        {
            addErrorMessage(msg);
        }
        else
        {
            addErrorMessage(defailtMsg);
        }
    }
    
    public static void addErrorMessages(List<String> messages)
    {
        for(String message: messages)
            addErrorMessage(message);
    }
    
    public static void addErrorMessage(String msg)
    {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
    
    public static void addErrorMessage(FacesContext context, String msg)
    {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        context.addMessage(null, facesMsg);
    }
    
    public static void addSuccessMessage(String msg)
    {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("SuccessInfo", facesMsg);
    }
    
    public static String getRequestParametr(String key)
    {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }
    
    public static Object getObjectFromRequestParametr(String requestParametrName, Converter converter, UIComponent component)
    {
        String theId = Messenger.getRequestParametr(requestParametrName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }
}
