package apis.weatherapp.metaweather.continent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContinentService {
    @Autowired
    private ContinentDAO continentDAO;

    public List<Continent> findAll() {
        return continentDAO.findAll();
    }

    public Continent findByTitle(String title) {
        return continentDAO.findByTitle(title);
    }
}
