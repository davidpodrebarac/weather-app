package apis.weatherapp.jsf;

import apis.weatherapp.security.controller.AuthenticationException;
import apis.weatherapp.utils.LoginHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Named("login")
@ViewScoped
public class LoginView {
    private String username;
    private String password;

    @Autowired
    private LoginHelper loginHelper;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void logout() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> props = new HashMap<>();
        FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("jwt_token", "", props);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.sendRedirect("/login.xhtml");
    }

    public String login() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        try {
            this.loginHelper.authenticate(this.username, this.password);
            final String token = loginHelper.generateTokenForUser(username);
            loginHelper.addJwtToCookie(response, token);
            response.sendRedirect("/index.xhtml");
        } catch (AuthenticationException e) {
            facesContext.addMessage("loginForm:usernameInput", new FacesMessage(e.getMessage()));
        }
        return null;
    }

}
