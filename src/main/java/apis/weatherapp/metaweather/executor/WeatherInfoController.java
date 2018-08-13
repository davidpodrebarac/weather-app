package apis.weatherapp.metaweather.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherInfoController {
    @Autowired
    private WeatherInfoService wiService;

    @RequestMapping(value = "all-weather-info", method = RequestMethod.GET)
    public List<SubscriptionWeatherInfo> getWeatherInfoForSubscriptions() {
        return wiService.getWeatherInfoForSubscriptions();
    }

}
