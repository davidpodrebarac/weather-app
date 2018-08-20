package apis.weatherapp.metaweather.country;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryDAO extends JpaRepository<Country, Long> {
	public List<Country> findByContinentId(Long continentId);
	public Country findByTitle(String title);
}
