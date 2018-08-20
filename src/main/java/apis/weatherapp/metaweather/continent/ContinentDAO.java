package apis.weatherapp.metaweather.continent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContinentDAO extends JpaRepository<Continent, Long> {
    Continent findByTitle(String title);
}
