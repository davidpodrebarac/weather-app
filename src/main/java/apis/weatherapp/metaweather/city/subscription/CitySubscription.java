package apis.weatherapp.metaweather.city.subscription;

import apis.weatherapp.metaweather.city.City;
import apis.weatherapp.metaweather.rest.models.SubscriptionWeatherInfo;
import apis.weatherapp.security.model.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@citySubscriptionId")
public class CitySubscription {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "city_id")
    private City city;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "citySubscription", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SubscriptionWeatherInfo> subscriptionWeatherInfos;

    public CitySubscription() {

    }

    public CitySubscription(User user, City city) {
        super();
        this.user = user;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    public Date getCreated() {
        return created;
    }

    public User getUser() {
        return user;
    }

    public City getCity() {
        return city;
    }


}
