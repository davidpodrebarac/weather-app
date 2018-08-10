package apis.weatherapp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WeatherAppApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(WeatherAppApplication.class, args);
	}

//	@Bean
//	public RestTemplate restTemplate() {
//		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//
//		Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("http://www.apis-it.local", 8080));
//		requestFactory.setProxy(proxy);
//
//		return new RestTemplate(requestFactory);
//	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
