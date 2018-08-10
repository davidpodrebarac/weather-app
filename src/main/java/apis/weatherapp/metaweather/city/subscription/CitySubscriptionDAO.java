package apis.weatherapp.metaweather.city.subscription;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CitySubscriptionDAO extends JpaRepository<CitySubscription, Long> {
	List<CitySubscription> findByUserId(Long id);
}
