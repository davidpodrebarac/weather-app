package apis.weatherapp.jsf;

import apis.weatherapp.metaweather.city.subscription.CitySubscription;
import apis.weatherapp.metaweather.city.subscription.CitySubscriptionService;
import apis.weatherapp.security.model.User;
import apis.weatherapp.utils.UserExtractor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class UnsubscribeDropdownView extends UserExtractor {
    @Autowired
    private CitySubscriptionService citySubscriptionService;

    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getSubscriptions() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        User user = this.getAuthenticatedUser(request);
        return citySubscriptionService.findAll(user.getId()).stream().map(cs -> cs.getCity().getTitle()).collect(Collectors.toList());
    }

    public void unsubscribe() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();

        FacesMessage msg;
        if (city != null && !city.equals("")) {
            User user = this.getAuthenticatedUser(request);
            CitySubscription cs = citySubscriptionService.findByCityTitleAndUserId(city, user.getId());
            citySubscriptionService.removeById(cs.getId());
            msg = new FacesMessage("Unsubscribed from ", city);
        } else
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City subscription is not selected.");

        facesContext.addMessage(null, msg);
    }
}
