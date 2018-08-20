package apis.weatherapp.jsf;

import apis.weatherapp.metaweather.city.subscription.CitySubscription;
import apis.weatherapp.metaweather.city.subscription.CitySubscriptionService;
import apis.weatherapp.metaweather.rest.models.SubscriptionWeatherInfo;
import apis.weatherapp.metaweather.rest.services.SubscriptionWeatherInfoService;
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
public class SubscriptionWeatherInfoView extends UserExtractor {
    @Autowired
    private SubscriptionWeatherInfoService subscriptionWeatherInfoService;
    @Autowired
    private CitySubscriptionService citySubscriptionService;

    public List<SubscriptionWeatherInfo> getWeatherInfos() {
        User user = this.getAuthenticatedUser((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
        List<CitySubscription> subscriptions = citySubscriptionService.findAll(user.getId());
        return subscriptionWeatherInfoService.findDistinctByCitySubscriptionIn(subscriptions);
    }
}
