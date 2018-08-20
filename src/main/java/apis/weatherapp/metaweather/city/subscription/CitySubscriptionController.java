package apis.weatherapp.metaweather.city.subscription;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/subscriptions")
@RestController
public class CitySubscriptionController {

	@Autowired
	private CitySubscriptionService citySubService;

	@RequestMapping(method = RequestMethod.GET)
	public List<CitySubscription> getAllSubscriptionsForUser(HttpServletRequest request) {
		List<CitySubscription> all = citySubService.findAll();
		return all;
	}

}
