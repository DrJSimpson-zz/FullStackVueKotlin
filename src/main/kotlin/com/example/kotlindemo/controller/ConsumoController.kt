package com.example.kotlindemo.controller

import com.example.kotlindemo.model.Consumo
import com.example.kotlindemo.repository.ConsumoRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@CrossOrigin(origins =  ["*"]) //this will enable CORS on all endpoints for the controller
@RequestMapping("/api")
class ConsumoController(private val consumoRepository: ConsumoRepository) {

    @GetMapping("/consumos")
    fun getAllConsumos(): List<Consumo> =
            consumoRepository.findAll()


    @PostMapping("/consumos")
    fun createNewConsumo(@Valid @RequestBody consumo: Consumo): Consumo =
            consumoRepository.save(consumo)


    @GetMapping("/consumos/{id}")
    fun getConsumoById(@PathVariable(value = "id") consumoId: Long): ResponseEntity<Consumo> {
        return consumoRepository.findById(consumoId).map { consumo ->
            ResponseEntity.ok(consumo)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/consumos/{id}")
    fun updateConsumoById(@PathVariable(value = "id") consumoId: Long,
                          @Valid @RequestBody newConsumo: Consumo): ResponseEntity<Consumo> {

        return consumoRepository.findById(consumoId).map { existingConsumo ->
            val updatedConsumo: Consumo = existingConsumo
                    .copy(cantidad = newConsumo.cantidad, detalle = newConsumo.detalle, producto = newConsumo.producto)
            ResponseEntity.ok().body(consumoRepository.save(updatedConsumo))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/consumos/{id}")
    fun deleteConsumoById(@PathVariable(value = "id") consumoId: Long): ResponseEntity<Void> {

        return consumoRepository.findById(consumoId).map { consumo  ->
            consumoRepository.delete(consumo)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}