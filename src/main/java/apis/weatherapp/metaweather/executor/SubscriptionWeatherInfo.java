package apis.weatherapp.metaweather.executor;

import apis.weatherapp.metaweather.city.subscription.CitySubscription;

public class SubscriptionWeatherInfo {
	private WeatherInfo weatherInfo;
	private CitySubscription citySubscription;

	public SubscriptionWeatherInfo(WeatherInfo weatherInfo, CitySubscription citySubscription) {
		super();
		this.weatherInfo = weatherInfo;
		this.citySubscription = citySubscription;
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
