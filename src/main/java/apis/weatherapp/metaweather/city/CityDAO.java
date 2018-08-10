package apis.weatherapp.metaweather.city;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDAO extends JpaRepository<City, Long> {
	public List<City> findByCountryId(Long countryId);
}
