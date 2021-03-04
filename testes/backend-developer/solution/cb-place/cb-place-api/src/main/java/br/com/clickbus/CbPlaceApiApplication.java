package br.com.clickbus;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = "br.com.clickbus")
public class CbPlaceApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(CbPlaceApiApplication.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(getApiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("br.com.clickbus.resource"))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo getApiInfo() {
    Contact contact =
        new Contact(
            "Danilo Bitencourt",
            "https://github.com/danbit/quero-ser-clickbus",
            "daniloesi@gmail.com");

    return new ApiInfoBuilder()
        .title("Place API")
        .description("A SpringBoot API developed for the ClickBus back-end developer challenge")
        .version("1.0.0")
        .license("Apache 2.0")
        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
        .contact(contact)
        .build();
  }
}
