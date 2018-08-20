package apis.weatherapp.metaweather.city;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityDAO extends JpaRepository<City, Long> {
    List<City> findByCountryId(Long countryId);

    City findByTitle(String title);
}
