package apis.weatherapp.metaweather.rest.daos;

import apis.weatherapp.metaweather.city.subscription.CitySubscription;
import apis.weatherapp.metaweather.rest.models.SubscriptionWeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionWeatherInfoDAO extends JpaRepository<SubscriptionWeatherInfo, Long> {
    List<SubscriptionWeatherInfo> findDistinctByCitySubscriptionIn(List<CitySubscription> subs);
}
