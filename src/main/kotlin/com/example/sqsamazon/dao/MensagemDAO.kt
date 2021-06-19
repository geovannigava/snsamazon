package com.example.sqsamazon.dao

import com.example.sqsamazon.model.Mensagem
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface MensagemDAO : MongoRepository<Mensagem, String>