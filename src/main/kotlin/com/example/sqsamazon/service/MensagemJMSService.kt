package com.example.sqsamazon.service

import com.example.sqsamazon.model.Mensagem
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.annotation.JmsListener
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Service
import javax.jms.JMSException

@Service
class MensagemJMSService(private val mensagemService: MensagemServiceImpl) {

    private val objectMapper = ObjectMapper()
    private val logger: Logger = LogManager.getLogger(MensagemJMSService::class.java)

    @Autowired
    private val jmsTemplate: JmsTemplate? = null

    @JmsListener(destination = "mensagensQueue")
    @Throws(Exception::class)
    fun mensagemConsumer(msgContent: String?) {
        try {
            objectMapper.registerModule(KotlinModule())
            val mensagem: Mensagem = objectMapper.readValue(msgContent, Mensagem::class.java)
            println(mensagem)
            mensagemService.create(mensagem);
        } catch (e: Exception) {
            throw e
        }
    }

    fun mensagemProducer(message: Mensagem) {
        try {
            val jsonString = objectMapper.writeValueAsString(message)
            jmsTemplate!!.convertAndSend("mensagensQueue", jsonString)
        } catch (e: JMSException) {
            println(e)
            throw e
        }
    }

}