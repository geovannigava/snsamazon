package com.example.sqsamazon.controller

import com.example.sqsamazon.dto.MensagemDTO
import com.example.sqsamazon.mapper.MensagemMapper
import com.example.sqsamazon.service.MensagemServiceImpl
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/mensagem")
class MensagemController(private val mensagemService: MensagemServiceImpl, private val mapper: MensagemMapper) {

    @GetMapping
    fun getAll(pageable: Pageable): ResponseEntity<Page<MensagemDTO>> = ResponseEntity.ok(mensagemService.getAll(pageable).map(mapper::modelToDTO))

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<Optional<MensagemDTO>> {
        var retorno = id.let(mensagemService::getById).map(mapper::modelToDTO)
        if(retorno.isPresent){
            return ResponseEntity.ok(retorno)
        } else {
            return ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun save(@RequestBody obj: MensagemDTO): ResponseEntity<MensagemDTO> =
        ResponseEntity(
            obj.let(mapper::dtoToModel).let(mensagemService::create).let(mapper::modelToDTO), HttpStatus.CREATED
        )

    @PutMapping("/{id}")
    fun update(@RequestBody obj: MensagemDTO, @PathVariable id: String): ResponseEntity<MensagemDTO> {
        val mensagemDBOptional = id.let(mensagemService::getById).map(mapper::modelToDTO)
        var toSave = mensagemDBOptional
            .orElseThrow{ RuntimeException("Mensagem: $id não encontrada !")}
            .copy(title = obj.title)
        return ResponseEntity.ok(toSave.let(mapper::dtoToModel).let(mensagemService::update).let(mapper::modelToDTO))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) = mensagemService.deleteById(id)

}