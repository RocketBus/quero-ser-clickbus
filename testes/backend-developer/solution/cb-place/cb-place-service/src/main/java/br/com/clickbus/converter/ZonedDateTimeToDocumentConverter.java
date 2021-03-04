package br.com.clickbus.converter;

import br.com.clickbus.util.Constants;
import com.sun.istack.Nullable;
import java.time.ZonedDateTime;
import java.util.Date;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

/** @author Danilo Bitencourt */
@Component
@WritingConverter
public class ZonedDateTimeToDocumentConverter implements Converter<ZonedDateTime, Document> {

  @Override
  public Document convert(@Nullable ZonedDateTime zonedDateTime) {
    if (zonedDateTime == null) {
      return null;
    }

    Document document = new Document();
    document.put(Constants.DATE_TIME, Date.from(zonedDateTime.toInstant()));
    document.put(Constants.ZONE, zonedDateTime.getZone().getId());
    document.put("offset", zonedDateTime.getOffset().toString());
    return document;
  }
}
