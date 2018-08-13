package apis.weatherapp.metaweather.rest.daos;

import apis.weatherapp.metaweather.rest.models.SubscriptionWeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionWeatherInfoDAO extends JpaRepository<SubscriptionWeatherInfo, Long> {
}
