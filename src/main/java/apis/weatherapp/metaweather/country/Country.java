package apis.weatherapp.metaweather.country;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import apis.weatherapp.metaweather.LocationData;
import apis.weatherapp.metaweather.continent.Continent;

@Entity
public class Country extends LocationData {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Continent continent;

	public Country() {
		super(0, "random", "0,0");
	}

	public Country(long woeid, String lattLong, String title, Continent cont) {
		super(woeid, lattLong, title);
		this.continent = cont;
	}

	public Long getId() {
		return id;
	}

}
