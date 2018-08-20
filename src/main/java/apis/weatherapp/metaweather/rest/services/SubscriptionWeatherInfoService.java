package apis.weatherapp.metaweather.rest.services;

import apis.weatherapp.metaweather.city.subscription.CitySubscription;
import apis.weatherapp.metaweather.rest.daos.SubscriptionWeatherInfoDAO;
import apis.weatherapp.metaweather.rest.models.SubscriptionWeatherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionWeatherInfoService {
    @Autowired
    private SubscriptionWeatherInfoDAO subsWIDAO;

    public void add(SubscriptionWeatherInfo subscriptionWeatherInfo) {
        subsWIDAO.save(subscriptionWeatherInfo);
    }

    public List<SubscriptionWeatherInfo> findDistinctByCitySubscriptionIn(List<CitySubscription> subscriptions) {
        return subsWIDAO.findDistinctByCitySubscriptionIn(subscriptions);
    }

}
