package com.example.sqsamazon.controller

import com.example.sqsamazon.dto.MensagemDTO
import com.example.sqsamazon.model.Mensagem
import com.example.sqsamazon.service.MensagemJMSService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/aws")
class MensagemJMSController {

    @Autowired
    private val mensagemJmsService: MensagemJMSService? = null

    @PostMapping
    fun enviarMsg(@RequestBody obj: Mensagem) {
        println(obj)
        mensagemJmsService?.mensagemProducer(obj);
    }

}