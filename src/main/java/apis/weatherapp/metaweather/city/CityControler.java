package apis.weatherapp.metaweather.city;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import apis.weatherapp.metaweather.city.subscription.CitySubscription;
import apis.weatherapp.metaweather.city.subscription.CitySubscriptionService;
import apis.weatherapp.security.model.User;
import apis.weatherapp.utils.UserExtractor;

@RequestMapping("/city")
@RestController
public class CityControler extends UserExtractor {
	
	@Autowired
	private CityService cityService;
	@Autowired
	private CitySubscriptionService csService;

	@RequestMapping(method=RequestMethod.GET)
	public List<City> getAllCities() {
		return cityService.getAllCities();
	}

	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public City getCity(@PathVariable Long id) {
		return cityService.getCity(id);
	}

	@RequestMapping(method=RequestMethod.POST, value="{id}/subscribe")
	public void subscribe(@PathVariable Long id, HttpServletRequest request) {
		City city = cityService.getCity(id);
		User user = this.getAuthenticatedUser(request);
		CitySubscription cs = new CitySubscription(user, city);
		csService.add(cs);
	}
}
