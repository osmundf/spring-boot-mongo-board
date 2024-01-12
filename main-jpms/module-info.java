@SuppressWarnings({"requires-automatic"})
open module osmundf.document.board {
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.core;
  requires spring.context;
  requires spring.beans;
  requires spring.web;
  requires spring.webmvc;
  requires org.apache.tomcat.embed.core;
  requires com.fasterxml.jackson.core;
  requires com.fasterxml.jackson.databind;
  requires org.mongodb.driver.core;
  requires org.mongodb.driver.sync.client;
  requires org.mongodb.bson;
  requires org.slf4j;
}
