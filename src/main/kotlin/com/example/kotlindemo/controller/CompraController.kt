package com.example.kotlindemo.controller

import com.example.kotlindemo.model.Compra
import com.example.kotlindemo.repository.CompraRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@CrossOrigin(origins =  ["*"]) //this will enable CORS on all endpoints for the controller
@RequestMapping("/api")
class CompraController(private val compraRepository: CompraRepository) {

    @GetMapping("/compras")
    fun getAllCompras(): List<Compra> =
            compraRepository.findAll()


    @PostMapping("/compras")
    fun createNewCompra(@Valid @RequestBody compra: Compra): Compra =
            compraRepository.save(compra)


    @GetMapping("/compras/{id}")
    fun getCompraById(@PathVariable(value = "id") compraId: Long): ResponseEntity<Compra> {
        return compraRepository.findById(compraId).map { compra ->
            ResponseEntity.ok(compra)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/compras/{id}")
    fun updateCompraById(@PathVariable(value = "id") compraId: Long,
                          @Valid @RequestBody newCompra: Compra): ResponseEntity<Compra> {

        return compraRepository.findById(compraId).map { existingCompra ->
            val updatedCompra: Compra = existingCompra
                    .copy(monto = newCompra.monto, formaDePago = newCompra.formaDePago, detalle = newCompra.detalle)
            ResponseEntity.ok().body(compraRepository.save(updatedCompra))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/compras/{id}")
    fun deleteCompraById(@PathVariable(value = "id") compraId: Long): ResponseEntity<Void> {

        return compraRepository.findById(compraId).map { compra  ->
            compraRepository.delete(compra)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}