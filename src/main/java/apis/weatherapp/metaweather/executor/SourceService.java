package apis.weatherapp.metaweather.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceService {
    @Autowired
    private SourceDAO sourceDAO;

    public Source getBySourceURL(String url) {
        return sourceDAO.findByUrl(url);
    }

}
