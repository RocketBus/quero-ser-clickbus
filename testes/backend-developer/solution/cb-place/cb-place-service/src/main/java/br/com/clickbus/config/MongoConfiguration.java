package br.com.clickbus.config;

import br.com.clickbus.converter.DocumentToZonedDateTimeConverter;
import br.com.clickbus.converter.ZonedDateTimeToDocumentConverter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * MongoDB database configurations
 *
 * @author Danilo Bitencourt
 */
@Configuration
@EnableMongoRepositories(basePackages = "br.com.clickbus.repository")
public class MongoConfiguration {

  @Bean
  public MongoCustomConversions customConversions() {
    List<Converter<?, ?>> converters = new ArrayList<>();

    converters.add(new ZonedDateTimeToDocumentConverter());
    converters.add(new DocumentToZonedDateTimeConverter());
    return new MongoCustomConversions(converters);
  }
}
