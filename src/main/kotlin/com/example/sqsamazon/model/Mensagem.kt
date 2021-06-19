package com.example.sqsamazon.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Mensagem(@Id val id: String, val title: String) {
    constructor(title: String) : this(ObjectId().toString(), title)


}
