package apis.weatherapp.metaweather.country;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CountryDAO extends JpaRepository<Country, Long> {
	public List<Country> findByContinentId(Long continentId);
}
