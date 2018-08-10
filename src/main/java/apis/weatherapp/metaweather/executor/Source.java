package apis.weatherapp.metaweather.executor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "title", "slug", "url", "crawl_rate" })
public class Source {

	@JsonProperty("title")
	private String title;
	@JsonProperty("slug")
	private String slug;
	@JsonProperty("url")
	private String url;
	@JsonProperty("crawl_rate")
	private Integer crawlRate;
	@JsonIgnore

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
	public Integer getCrawlRate() {
		return crawlRate;
	}

	@JsonProperty("crawl_rate")
	public void setCrawlRate(Integer crawlRate) {
		this.crawlRate = crawlRate;
	}
}