package com.github.osmundf.mongo.board.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "document-board")
public record DocumentBoardProperties(
    String hostType, String host, String username, char[] password, String options) {}
