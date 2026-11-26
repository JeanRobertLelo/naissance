package fr.jrlelo.bnaiss.shared.notifications;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.mailpit")
public record MailpitProperties(String baseUrl, String from) {
}
