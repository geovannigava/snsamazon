package com.example.sqsamazon.config

import com.amazon.sqs.javamessaging.SQSConnectionFactory
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.internal.StaticCredentialsProvider
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.support.destination.DynamicDestinationResolver
import javax.jms.Session


@Configuration
@EnableJms
class JmsConfig {

    private val accessKey: String? = "AKIA57IXW34RNUSLKMXE"

    private val secretKey: String? = "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"

    @Autowired
    private val connectionFactory: SQSConnectionFactory? = null
    @Bean
    fun createConnectionFactory(): SQSConnectionFactory {
        return SQSConnectionFactory.builder()
                .withRegion(Region.getRegion(Regions.SA_EAST_1))
                .withAWSCredentialsProvider(StaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
                .build()
    }

    @Bean
    fun jmsListenerContainerFactory(): DefaultJmsListenerContainerFactory {
        val factory = DefaultJmsListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory!!)
        factory.setDestinationResolver(DynamicDestinationResolver())
        factory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE)
        return factory
    }

    @Bean
    fun defaultJmsTemplate(): JmsTemplate {
        return JmsTemplate(connectionFactory!!)
    }
}