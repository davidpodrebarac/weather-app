package apis.weatherapp.metaweather.continent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import apis.weatherapp.metaweather.LocationData;

@Entity
public class Continent extends LocationData {
	@Id
	@GeneratedValue
	private Long id;

	public Continent() {
		super(0, "random", "0,0");
	}

	public Continent(long woeid, String lattLong, String title) {
		super(woeid, lattLong, title);
	}

	public Long getId() {
		return id;
	}

}
