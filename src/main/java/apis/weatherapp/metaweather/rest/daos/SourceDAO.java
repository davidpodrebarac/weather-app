package apis.weatherapp.metaweather.rest.daos;

import apis.weatherapp.metaweather.rest.models.Source;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceDAO extends JpaRepository<Source, Long> {
    Source findByUrl(String url);
}
