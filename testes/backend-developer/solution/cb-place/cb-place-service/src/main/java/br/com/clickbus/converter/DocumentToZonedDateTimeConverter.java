package br.com.clickbus.converter;

import static br.com.clickbus.util.Constants.DATE_TIME;
import static br.com.clickbus.util.Constants.ZONE;

import com.sun.istack.Nullable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

/** @author Danilo Bitencourt */
@Component
@ReadingConverter
public class DocumentToZonedDateTimeConverter implements Converter<Document, ZonedDateTime> {

  @Override
  public ZonedDateTime convert(@Nullable Document document) {
    if (document == null) {
      return null;
    }

    Date dateTime = document.getDate(DATE_TIME);
    String zoneId = document.getString(ZONE);
    ZoneId zone = ZoneId.of(zoneId);

    return ZonedDateTime.ofInstant(dateTime.toInstant(), zone);
  }
}
