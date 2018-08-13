package apis.weatherapp.threading;

import apis.weatherapp.metaweather.executor.SubscriptionWeatherInfo;
import apis.weatherapp.metaweather.executor.WeatherInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class PeriodicSaver extends Thread {
    private final static Logger LOGGER = LoggerFactory.getLogger(PeriodicSaver.class.getName());

    private boolean needToPause;

    @Value("${refresher.thread-sleep-time}")
    private String sleepTime;

    @Autowired
    private WeatherInfoService wiService;

    @PostConstruct
    public void init() {
        this.start();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    public void run() {
        long sleepTime = Long.valueOf(this.sleepTime) * 1000;
        while (true) {
            try {
                Thread.sleep(sleepTime);
                LOGGER.info("Fetching new weather infos");
                long start = System.currentTimeMillis();
                List<SubscriptionWeatherInfo> weatherInfoList = this.wiService.getWeatherInfoForSubscriptions();
                float total = (System.currentTimeMillis() - start) * 1000;
                LOGGER.info("Fetched %d new weather infos in %.2fs.", weatherInfoList.size(), total);
                this.pausePoint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void pausePoint() throws InterruptedException {
        while (needToPause) {
            this.wait();
        }
    }

    public synchronized void pause() {
        LOGGER.info("Pausing thread.");
        needToPause = true;
    }

    public synchronized void unpause() {
        LOGGER.info("Un-pausing thread.");
        needToPause = false;
        this.notifyAll();
    }

}