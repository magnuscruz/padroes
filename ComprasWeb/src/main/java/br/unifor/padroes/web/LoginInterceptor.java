package br.unifor.padroes.web;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor @Login
public class LoginInterceptor implements Serializable {
 
    /**
     *
     */
    private static final long serialVersionUID = -3592596773179102164L;
 
    @Inject
    private ControleUsuarioLogado session;
 
    @AroundInvoke
    public Object test(InvocationContext ic) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (!session.isLogado()) {
            context.getApplication().getNavigationHandler().handleNavigation(context, null, "login");
        }
        try {
            return ic.proceed();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
