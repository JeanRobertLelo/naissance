package fr.jrlelo.bnaiss.configurations;

import fr.jrlelo.bnaiss.shared.notifications.MailPitClient;
import fr.jrlelo.bnaiss.shared.notifications.MailpitProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(MailpitProperties.class)
public class ApplicationConfiguration {


    @Bean
    public MailPitClient mailpitClient(MailpitProperties mailpitProperties) {
        RestClient restClient = RestClient.create(mailpitProperties.baseUrl());
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build();
        return httpServiceProxyFactory.createClient(MailPitClient.class);

    }

}
