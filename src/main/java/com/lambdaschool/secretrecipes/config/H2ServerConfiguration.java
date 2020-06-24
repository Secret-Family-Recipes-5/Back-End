package com.lambdaschool.secretrecipes.config;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * Configures H2 access through the JetBrains IntelliJ IDEA IDE.
 * <p>
 * Adapted from https://techdev.io/en/developer-blog/querying-the-embedded-h2-database-of-a-spring-boot-application
 * necessary for using the database tool built into intellij
 */
@Configuration
public class H2ServerConfiguration
{
    @Value("${h2.tcp.port:9092}")
    private String h2TcpPort;

    @Value("${h2.web.port:8082}")
    private String h2WebPort;


    @Bean
    @ConditionalOnExpression("${h2.tcp.enabled:true}")
    public Server h2TcpServer()
            throws
            SQLException
    {
        return Server.createTcpServer("-tcp",
                "-tcpAllowOthers",
                "-tcpPort",
                h2TcpPort)
                .start();
    }

    @Bean
    @ConditionalOnExpression("${h2.web.enabled:true}")
    public Server h2WebServer()
            throws
            SQLException
    {
        return Server.createWebServer("-web",
                "-webAllowOthers",
                "-webPort",
                h2WebPort)
                .start();
    }
}