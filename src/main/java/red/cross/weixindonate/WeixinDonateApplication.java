package red.cross.weixindonate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@MapperScan("red.cross.weixindonate.dao")
@SpringBootApplication
public class WeixinDonateApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(WeixinDonateApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**")
				.allowCredentials(true)
				.allowedHeaders("*")
				.allowedOrigins("*")
				.allowedMethods("*");

	}
}
