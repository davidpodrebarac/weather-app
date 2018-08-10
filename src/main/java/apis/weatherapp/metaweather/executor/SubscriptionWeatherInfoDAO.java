package apis.weatherapp.metaweather.executor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionWeatherInfoDAO extends JpaRepository<SubscriptionWeatherInfo, Long> {
}
