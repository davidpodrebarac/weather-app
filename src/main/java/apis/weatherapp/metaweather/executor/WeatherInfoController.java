package apis.weatherapp.metaweather.executor;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import apis.weatherapp.metaweather.city.subscription.CitySubscription;
import apis.weatherapp.metaweather.city.subscription.CitySubscriptionDAO;
import apis.weatherapp.metaweather.city.subscription.CitySubscriptionService;

@RestController
public class WeatherInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherInfoController.class);

	@Value("${metaweather-api-url}")
	private String URL;
	@Autowired
	private CitySubscriptionService citySubService;
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "all-weather-info", method = RequestMethod.GET)
	public List<SubscriptionWeatherInfo> getWeatherInfoForSubscriptions() {
		List<CitySubscription> subscriptions = citySubService.getAllSubscriptions();
		List<SubscriptionWeatherInfo> results = new ArrayList<>();
		for (int i = 0; i < subscriptions.size(); i++) {
//			RestTemplate restTemplate = new RestTemplate();
			CitySubscription s = subscriptions.get(i);
			String url = URL + s.getCity().getWoeid();
			LOGGER.info("url za povezivanje je {}", url);
			WeatherInfo w = restTemplate.getForObject(url, WeatherInfo.class);
			results.add(new SubscriptionWeatherInfo(w, s));
		}
		return results;
	}

}
