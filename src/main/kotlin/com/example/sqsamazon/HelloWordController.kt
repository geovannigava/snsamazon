package com.example.sqsamazon

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/HelloPrimeiroTeste")
class HelloWordController {

    @GetMapping
    fun getAll(): String = "olá !"

}