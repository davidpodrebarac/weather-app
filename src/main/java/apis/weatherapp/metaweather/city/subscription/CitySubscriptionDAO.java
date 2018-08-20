package apis.weatherapp.metaweather.city.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CitySubscriptionDAO extends JpaRepository<CitySubscription, Long> {
	List<CitySubscription> findByUserId(Long id);
	@Transactional
	Long removeById(Long id);
	CitySubscription findByCityTitleAndUserId(String title, Long id);
}
