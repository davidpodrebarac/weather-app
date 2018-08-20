package apis.weatherapp.metaweather.rest.daos;

import apis.weatherapp.metaweather.rest.models.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherInfoDAO extends JpaRepository<WeatherInfo, Long> {
}
