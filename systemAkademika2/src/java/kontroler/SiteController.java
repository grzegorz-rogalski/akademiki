/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

/**
 *
 * @author marekszymanski
 */
public class SiteController {
    
    public boolean edit;

    public SiteController() {
        edit = false;
    }
    
    public String logIn(String kto)
    {
        if(kto.equals("Administrator"))
        {
            System.out.println("Administrator");
            return "adminstrator";
        }   
        if(kto.equals("Mieszkaniec")){
            System.out.println("mieszkaniec>przejscie   "+kto);
            return "inhabitant";
        }
        if(kto.equals("Portier"))
            return "porter";
        return "home";
    }
    
    public void changeEdit()
    {
        if(edit)
            edit = false;
        else
            edit = true;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
    
}
