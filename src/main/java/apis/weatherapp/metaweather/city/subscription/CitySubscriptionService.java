package apis.weatherapp.metaweather.city.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitySubscriptionService {
    @Autowired
    private CitySubscriptionDAO citySubDAO;

    public List<CitySubscription> findAll() {
        return citySubDAO.findAll();
    }

    public List<CitySubscription> findAll(Long userId) {
        return citySubDAO.findByUserId(userId);
    }

    public CitySubscription findByCityTitleAndUserId(String title, Long id) {
        return citySubDAO.findByCityTitleAndUserId(title, id);
    }

    public void add(CitySubscription cs) {
        citySubDAO.save(cs);
    }

    public long removeById(long id) {
        return citySubDAO.removeById(id);
    }
}
