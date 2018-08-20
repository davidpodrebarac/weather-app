package apis.weatherapp.metaweather;

import apis.weatherapp.metaweather.city.City;
import apis.weatherapp.metaweather.city.CityDAO;
import apis.weatherapp.metaweather.continent.Continent;
import apis.weatherapp.metaweather.continent.ContinentDAO;
import apis.weatherapp.metaweather.country.Country;
import apis.weatherapp.metaweather.country.CountryDAO;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class PreloadDatabaseWithWOEIDSpring implements ApplicationRunner {
	private static class BasicLocationDataFromJson {
		public BasicLocationDataFromJson(JSONArray children, String locationType, String title, long woeID,
				String lattLong) {
			super();
			this.children = children;
			this.locationType = locationType;
			this.title = title;
			this.woeID = woeID;
			this.lattLong = lattLong;
		}

		JSONArray children;
		String locationType;
		String title;
		long woeID;
		String lattLong;
	}

	private static String WOEID_file = "database.json";

	@Autowired
	private CityDAO cityRepository;
	@Autowired
	private CountryDAO countryRepository;
	@Autowired
	private ContinentDAO continentRepository;

	public PreloadDatabaseWithWOEIDSpring() {
	}

	public void loadJsonDataIntoDB() throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(new File(WOEID_file)));
		String jsonTxt = IOUtils.toString(is);

		JSONObject json = new JSONObject(jsonTxt);
		// System.out.println(json);
		for (String key : json.keySet()) {
			JSONObject continentJson = json.getJSONObject(key);
			handleContinent(continentJson);
		}
	}

	private void handleContinent(JSONObject continentJson) {
		BasicLocationDataFromJson bldfj = PreloadDatabaseWithWOEIDSpring.getBasicLocationDataFromJson(continentJson);
		if (!bldfj.locationType.equals("Continent")) {
			throw new IllegalArgumentException("Excpected continent for first pass!");
		} else {
			Continent cont = new Continent(bldfj.woeID, bldfj.lattLong, bldfj.title);
			continentRepository.save(cont);
			for (Object object : bldfj.children) {
				JSONObject continentChild = (JSONObject) object;
				if (!continentChild.getString("location_type").equals("Country")) {
					System.out.println(continentChild.toMap());
					throw new IllegalArgumentException(
							"Excpected country in second pass! " + continentChild.getString("location_type"));
				} else {
					this.handleCountry(continentChild, cont);
				}
			}
		}

	}

	private void handleCountry(JSONObject countryJson, Continent cont) {
		BasicLocationDataFromJson bldfj = PreloadDatabaseWithWOEIDSpring.getBasicLocationDataFromJson(countryJson);
		Country country = new Country(bldfj.woeID, bldfj.lattLong, bldfj.title, cont);
		countryRepository.save(country);
		if (bldfj.children != null) {
			for (Object object : bldfj.children) {
				JSONObject countryChild = (JSONObject) object;
				if (countryChild.getString("location_type").equals("City")) {
					handleCity(countryChild, country);
				} else {
					handleCountry(countryChild, cont); }
			}
		}
	}

	private void handleCity(JSONObject cityJson, Country country) {
		BasicLocationDataFromJson bldfj = PreloadDatabaseWithWOEIDSpring.getBasicLocationDataFromJson(cityJson);
		City city = new City(bldfj.woeID, bldfj.lattLong, bldfj.title, country);
		cityRepository.save(city);
	}

	private static BasicLocationDataFromJson getBasicLocationDataFromJson(JSONObject continentJson) {
		JSONArray children = null;
		if (continentJson.has("children")) {
			children = continentJson.getJSONArray("children");
		}
		String locationType = continentJson.getString("location_type");
		String title = continentJson.getString("title");
		long woeID = continentJson.getLong("woeid");
		String lattLong = continentJson.getString("latt_long");
		return new BasicLocationDataFromJson(children, locationType, title, woeID, lattLong);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (this.cityRepository.count() == 0) {
			this.loadJsonDataIntoDB();
		}
	}

}
