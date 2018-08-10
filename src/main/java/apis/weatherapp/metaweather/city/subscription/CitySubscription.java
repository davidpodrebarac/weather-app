package apis.weatherapp.metaweather.city.subscription;

import apis.weatherapp.metaweather.city.City;
import apis.weatherapp.security.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@citySubscriptionId")
public class CitySubscription {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cityId")
    private City city;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date created;

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
