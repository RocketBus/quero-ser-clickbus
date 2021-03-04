package br.com.clickbus.mapper;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Component;

/** @author Danilo Bitencourt */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Mapper {

  /**
   * The value may indicate a suggestion for a logical component name, to be turned into a Spring
   * bean in case of an auto-detected component.
   *
   * @return the suggested component name, if any
   */
  String value() default "";
}
