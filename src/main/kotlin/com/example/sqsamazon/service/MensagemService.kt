package com.example.sqsamazon.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface MensagemService<T, K> {
    fun getAll(pageable: Pageable): Page<T>
    fun getById(id: K): Optional<T>
    fun create(obj: T): T
    fun update(obj: T): T
    fun deleteById(id: K)
}