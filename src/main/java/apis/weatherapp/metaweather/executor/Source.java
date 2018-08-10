package apis.weatherapp.metaweather.executor;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"title", "slug", "url", "crawl_rate"})
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@sourceId")
@Entity
public class Source {

    @Id
    @GeneratedValue
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("url")
    @Column(unique = true)
    private String url;
    @JsonProperty("crawl_rate")
    private Long crawlRate;

    @JsonIgnore
    @ManyToMany
    @JoinColumn(name = "weather_info_fk")
    private List<WeatherInfo> weatherInfos;

    public List<WeatherInfo> getWeatherInfos() {
        return weatherInfos;
    }

    public void setWeatherInfos(List<WeatherInfo> weatherInfo) {
        this.weatherInfos = weatherInfo;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("slug")
    public String getSlug() {
        return slug;
    }

    @JsonProperty("slug")
    public void setSlug(String slug) {
        this.slug = slug;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("crawl_rate")
    public Long getCrawlRate() {
        return crawlRate;
    }

    @JsonProperty("crawl_rate")
    public void setCrawlRate(Long crawlRate) {
        this.crawlRate = crawlRate;
    }
}