package com.github.osmundf.mongo.board;

import static java.time.ZoneOffset.UTC;

import com.github.osmundf.mongo.board.configuration.DocumentBoardProperties;
import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Application.
 *
 * @author osmundf
 * @version $Id: $Id
 */
@SpringBootApplication
@EnableConfigurationProperties({DocumentBoardProperties.class})
public class Application {

  /**
   * Main application execution.
   *
   * @param args arguments
   */
  public static void main(String[] args) {
    TimeZone.setDefault(TimeZone.getTimeZone(UTC));
    SpringApplication.run(Application.class, args);
  }
}
