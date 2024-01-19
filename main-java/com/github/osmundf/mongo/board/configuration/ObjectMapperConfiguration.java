package com.github.osmundf.mongo.board.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Object Mapper Configuration.
 *
 * @author osmundf
 * @version $Id: $Id
 */
@Configuration
public class ObjectMapperConfiguration {

  /**
   * Update default object mapper
   *
   * @param objectMapper object mapper
   */
  @Autowired
  void updateObjectMapper(ObjectMapper objectMapper) {
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
  }
}
