package apis.weatherapp.metaweather.executor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceDAO extends JpaRepository<Source, Long> {
    Source findByUrl(String url);
}
