package apis.weatherapp.metaweather.city;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apis.weatherapp.metaweather.city.subscription.CitySubscription;

@Service
public class CityService {

	@Autowired
	private CityDAO cityRepository;

	public List<City> getAllCities() {
		List<City> cities = new ArrayList<>();
		cityRepository.findAll().forEach(c -> cities.add(c));
		return cities;
	}

	public City getCity(Long id) {
		return cityRepository.findById(id).get();
	}

}
