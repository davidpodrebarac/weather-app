package apis.weatherapp.metaweather.executor;

import apis.weatherapp.metaweather.city.subscription.CitySubscription;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@subscriptionWeatherInfoId")
@Entity
public class SubscriptionWeatherInfo {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_info_id")
    private WeatherInfo weatherInfo;
    @ManyToOne(optional = false)
    @JoinColumn(name = "city_subscription_id")
    private CitySubscription citySubscription;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }


    public SubscriptionWeatherInfo(WeatherInfo weatherInfo, CitySubscription citySubscription) {
        super();
        this.weatherInfo = weatherInfo;
        this.citySubscription = citySubscription;
    }

    public SubscriptionWeatherInfo() {
    }

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(WeatherInfo weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public CitySubscription getCitySubscription() {
        return citySubscription;
    }

    public void setCitySubscription(CitySubscription citySubscription) {
        this.citySubscription = citySubscription;
    }

}
