package fr.ali.business.control;

import fr.ali.business.boundary.CustomerHolder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
/**
 *
 * @author Adouche Ali
 */
public class ForceSignInPhaseListener implements PhaseListener {

    private static final List<String> PAGES_WITH_AUTHENTICATION;
    static {
      PAGES_WITH_AUTHENTICATION = new ArrayList<String>();
      PAGES_WITH_AUTHENTICATION.add("/signInSuccess.xhtml");
      PAGES_WITH_AUTHENTICATION.add("/needSignInToAccessIt.xhtml");
    }
    
    @Override
    public void afterPhase(PhaseEvent event) {
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String viewId = facesContext.getViewRoot().getViewId();
        
        if ( PAGES_WITH_AUTHENTICATION.contains(viewId) ) {
            Application app = facesContext.getApplication();
            CustomerHolder ch = (CustomerHolder) app.evaluateExpressionGet(facesContext,
                    "#{customerHolder}", CustomerHolder.class);
            
            if (ch.getCurrentCustomer() == null || ch.getCurrentCustomer().getId() == null) {
                ch.setOriginalViewId(viewId);//permet de sauvegarder la page qui a été demandée avant la redirection vers la page de login
                ViewHandler viewHandler = app.getViewHandler();
                UIViewRoot viewRoot = viewHandler.createView(facesContext,
                        "/signIn.xhtml");
                String texte = MessageFormat.format("veuillez vous authentifier pour acceder à la page {0}.", viewId.substring(1));
                FacesMessage msg = new FacesMessage(texte);
                facesContext.addMessage(null, msg);
                facesContext.setViewRoot(viewRoot);
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }
}
