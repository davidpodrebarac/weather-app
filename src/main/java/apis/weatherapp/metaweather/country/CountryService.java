package apis.weatherapp.metaweather.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryDAO countryDAO;

    public List<Country> findByContinentId(long continentId) {
        return countryDAO.findByContinentId(continentId);
    }

    public Country findByTitle(String country) {
        return countryDAO.findByTitle(country);
    }
}
