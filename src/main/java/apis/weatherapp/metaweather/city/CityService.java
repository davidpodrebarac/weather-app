package apis.weatherapp.metaweather.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

	public List<City> findByCountryId(long countryId) {
		return cityRepository.findByCountryId(countryId);
	}

	public City findByTitle(String title) {
		return cityRepository.findByTitle(title);
	}
}
