package io.github.singhalmradul.namingserver.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationServerRegistrationListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    ApplicationEventPublisher eventPublisher;

    /**
     * @param eventPublisher
     */
    public ConfigurationServerRegistrationListener(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @EventListener
    public void handle(EurekaInstanceRegisteredEvent event) {

        if ("configuration-server".equals(event.getInstanceInfo().getAppName())) {

            logger.info("\033[0;35m" + "refresh triggered" + "\033[0m");

            this.eventPublisher.publishEvent(
                    new RefreshEvent(
                            this,
                            "RefreshEvent",
                            "Refreshing scope"));

        }
    }
}
