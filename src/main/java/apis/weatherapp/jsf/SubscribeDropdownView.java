package apis.weatherapp.jsf;

import apis.weatherapp.metaweather.LocationData;
import apis.weatherapp.metaweather.city.City;
import apis.weatherapp.metaweather.city.CityService;
import apis.weatherapp.metaweather.city.subscription.CitySubscription;
import apis.weatherapp.metaweather.city.subscription.CitySubscriptionService;
import apis.weatherapp.metaweather.continent.Continent;
import apis.weatherapp.metaweather.continent.ContinentService;
import apis.weatherapp.metaweather.country.Country;
import apis.weatherapp.metaweather.country.CountryService;
import apis.weatherapp.security.model.User;
import apis.weatherapp.utils.UserExtractor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class SubscribeDropdownView extends UserExtractor {
    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ContinentService continentService;
    @Autowired
    private CitySubscriptionService citySubscriptionService;

    private Continent continentObj;
    private Country countryObj;
    private City cityObj;

    private String continent;
    private String country;
    private String city;

    private List<Country> countries;
    private List<City> cities;

    public String getContinent() {
        return continent;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Map<String, String> getContinents() {
        return continentService.findAll().stream().collect(Collectors.toMap(LocationData::getTitle, LocationData::getTitle));
    }

    public Map<String, String> getCountries() {
        return countries == null ? new HashMap<>() :
                countries.stream().collect(Collectors.toMap(LocationData::getTitle, LocationData::getTitle));
    }

    public Map<String, String> getCities() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        User user = this.getAuthenticatedUser(request);
        Set<String> alreadySubscribed = citySubscriptionService.findAll(user.getId()).stream().map(cs -> cs.getCity().getTitle()).collect(Collectors.toSet());
        if (cities == null)
            return new HashMap<>();
        else {
            cities.removeIf(c -> alreadySubscribed.contains(c.getTitle()));
            return cities.stream().collect(Collectors.toMap(LocationData::getTitle, LocationData::getTitle));
        }
    }

    public void onContinentChange() {
        if (continent != null && !continent.equals("")) {
            continentObj = continentService.findByTitle(continent);
            countries = countryService.findByContinentId(continentObj.getId());
            cities = new ArrayList<>();
        } else {
            countries = new ArrayList<>();
            cities = new ArrayList<>();
        }

    }

    public void onCountryChange() {
        if (country != null && !country.equals("")) {
            countryObj = countryService.findByTitle(country);
            cities = cityService.findByCountryId(countryObj.getId());
        } else {
            cities = new ArrayList<>();
            city = "";
        }
    }

    public void subscribe() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();

        FacesMessage msg;
        if (city != null && country != null && continent != null && !city.isEmpty() && !country.isEmpty() && !continent.isEmpty()) {
            cityObj = cityService.findByTitle(city);
            User user = this.getAuthenticatedUser(request);
            CitySubscription cs = new CitySubscription(user, cityObj);
            citySubscriptionService.add(cs);
            msg = new FacesMessage("Subscribed to ", city + " of " + country + " of " + continent);
            countryObj = null;
            cityObj = null;
        } else
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected.");

        facesContext.addMessage(null, msg);
    }
}
