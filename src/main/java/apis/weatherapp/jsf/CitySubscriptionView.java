package apis.weatherapp.jsf;

import apis.weatherapp.metaweather.city.subscription.CitySubscription;
import apis.weatherapp.metaweather.city.subscription.CitySubscriptionService;
import apis.weatherapp.security.model.User;
import apis.weatherapp.utils.UserExtractor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Named
@ViewScoped
public class CitySubscriptionView extends UserExtractor {
    @Autowired
    private CitySubscriptionService citySubscriptionService;

    public List<CitySubscription> getUserSubscriptions() {
        User user = this.getAuthenticatedUser((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
        return citySubscriptionService.findAll(user.getId());
    }
}
