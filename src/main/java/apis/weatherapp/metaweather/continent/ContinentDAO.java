package apis.weatherapp.metaweather.continent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ContinentDAO extends JpaRepository<Continent, Long> {
}
