package com.example.sqsamazon.service


import com.example.sqsamazon.dao.MensagemDAO
import com.example.sqsamazon.model.Mensagem
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class MensagemServiceImpl(val mensagemDAO: MensagemDAO) : MensagemService<Mensagem, String> {

    override fun getAll(pageable: Pageable): Page<Mensagem> = mensagemDAO.findAll(pageable)

    override fun getById(id: String): Optional<Mensagem> = mensagemDAO.findById(id)

    override fun create(obj: Mensagem): Mensagem = mensagemDAO.save(obj)

    override fun update(obj: Mensagem): Mensagem = mensagemDAO.save(obj)

    override fun deleteById(id: String) = mensagemDAO.deleteById(id)

}