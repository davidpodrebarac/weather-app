package apis.weatherapp.metaweather.executor;

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
