package apis.weatherapp.metaweather.rest.services;

import apis.weatherapp.metaweather.rest.daos.SubscriptionWeatherInfoDAO;
import apis.weatherapp.metaweather.rest.models.SubscriptionWeatherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubsWIService {
    @Autowired
    private SubscriptionWeatherInfoDAO subsWIDAO;

    public void add(SubscriptionWeatherInfo subscriptionWeatherInfo) {
        subsWIDAO.save(subscriptionWeatherInfo);
    }

}
