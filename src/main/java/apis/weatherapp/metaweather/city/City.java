package apis.weatherapp.metaweather.city;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.*;

import apis.weatherapp.metaweather.LocationData;
import apis.weatherapp.metaweather.city.subscription.CitySubscription;
import apis.weatherapp.metaweather.country.Country;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@cityId")
public class City extends LocationData {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Country country;
	@OneToMany(mappedBy = "city")
	private List<CitySubscription> subscriptions;

	public City() {
		super(0, "random", "0,0");
	}

	public City(long woeid, String lattLong, String title, Country country) {
		super(woeid, lattLong, title);
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public List<CitySubscription> getSubscriptions() {
		return subscriptions;
	}

}
