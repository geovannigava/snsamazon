package com.example.sqsamazon.mapper

import com.example.sqsamazon.dto.MensagemDTO
import com.example.sqsamazon.model.Mensagem
import org.bson.types.ObjectId
import org.springframework.stereotype.Component

@Component
class MensagemMapper : Mapper<Mensagem, MensagemDTO> {

    override fun modelToDTO(obj: Mensagem): MensagemDTO = MensagemDTO(obj.id, obj.title)

    override fun dtoToModel(dto: MensagemDTO): Mensagem =
        dto.let(MensagemDTO::id)?.let { Mensagem(it, dto.title) } ?: Mensagem(dto.title)

}