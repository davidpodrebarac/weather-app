package apis.weatherapp.metaweather;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Data that is used in every location, country, city or continent.
 * 
 * @author dpodrebarac
 *
 */
@MappedSuperclass
public abstract class LocationData {
	@Column
	private long woeid;
	@Column
	private String lattLong;
	@Column
	private String title;
	
	public LocationData(long woeid, String lattLong, String title) {
		super();
		this.woeid = woeid;
		this.lattLong = lattLong;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getWoeid() {
		return woeid;
	}

	public void setWoeid(long woeid) {
		this.woeid = woeid;
	}

	public String getLattLong() {
		return lattLong;
	}

	public void setLattLong(String lattLong) {
		this.lattLong = lattLong;
	}
	
}
