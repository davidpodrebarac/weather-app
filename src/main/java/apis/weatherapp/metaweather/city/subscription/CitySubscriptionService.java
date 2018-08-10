package apis.weatherapp.metaweather.city.subscription;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitySubscriptionService {
	@Autowired
	private CitySubscriptionDAO citySubDAO;

	public List<CitySubscription> getAllSubscriptions() {
		return citySubDAO.findAll();
	}
	
	public List<CitySubscription> getAllSubscriptions(Long userId) {
		return citySubDAO.findByUserId(userId);
	}

	public void add(CitySubscription cs) {
		citySubDAO.save(cs);
	}
	
}
