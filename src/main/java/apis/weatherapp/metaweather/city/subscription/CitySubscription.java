package apis.weatherapp.metaweather.city.subscription;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import apis.weatherapp.metaweather.city.City;
import apis.weatherapp.security.model.User;

@Entity
public class CitySubscription {

	@Id
	@GeneratedValue
	private Long id;
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user;
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cityId")
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
