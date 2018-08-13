package apis.weatherapp.metaweather.executor;

import apis.weatherapp.metaweather.city.subscription.CitySubscription;
import apis.weatherapp.metaweather.city.subscription.CitySubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherInfoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherInfoController.class);

    @Value("${refresher.metaweather-api-url}")
    private String URL;
    @Autowired
    private CitySubscriptionService citySubService;
    @Autowired
    private SubsWIService subsWIService;
    @Autowired
    private SourceService sourceService;

    @Transactional
    public List<SubscriptionWeatherInfo> getWeatherInfoForSubscriptions() {
        List<CitySubscription> subscriptions = citySubService.getAllSubscriptions();
        List<SubscriptionWeatherInfo> results = new ArrayList<>();
        for (int i = 0; i < subscriptions.size(); i++) {
            RestTemplate restTemplate = new RestTemplate();
            CitySubscription s = subscriptions.get(i);
            String url = URL + s.getCity().getWoeid();
            LOGGER.info("url za povezivanje je {}", url);
            WeatherInfo w = restTemplate.getForObject(url, WeatherInfo.class);
            w.getConsolidatedWeather().forEach(cw -> cw.setWeatherInfo(w));

            // replace new sources with already existing ones if they exist
            List<Source> newSources = new ArrayList<>();

            List<Source> createdSources = w.getSources();
            List<Source> dbSources = createdSources.stream().map(so -> sourceService.getBySourceURL(so.getUrl())).collect(Collectors.toList());
            for (int j = 0; j < dbSources.size(); j++) {
                Source dbSource = dbSources.get(j);
                if (dbSource == null) {
                    newSources.add(createdSources.get(j));
                } else {
                    newSources.add(dbSource);
                }
            }
            newSources.forEach(ns -> {
                List<WeatherInfo> existing = ns.getWeatherInfos();
                if (existing == null) {
                    existing = new ArrayList<>();
                }
                existing.add(w);
                ns.setWeatherInfos(existing);
            });
            w.setSources(newSources);

            SubscriptionWeatherInfo subscriptionWeatherInfo = new SubscriptionWeatherInfo(w, s);
            subsWIService.add(subscriptionWeatherInfo);
            results.add(subscriptionWeatherInfo);
        }
        return results;
    }
}
