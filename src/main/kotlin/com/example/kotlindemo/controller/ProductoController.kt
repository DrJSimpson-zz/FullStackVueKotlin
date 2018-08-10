package com.example.kotlindemo.controller

import com.example.kotlindemo.model.Producto
import com.example.kotlindemo.repository.ProductoRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@CrossOrigin(origins =  ["*"]) //this will enable CORS on all endpoints for the controller
@RequestMapping("/api")
class ProductoController(private val productoRepository: ProductoRepository) {

    @GetMapping("/productos")
    fun getAllProductos(): List<Producto> =
            productoRepository.findAll()


    @PostMapping("/productos")
    fun createNewProducto(@Valid @RequestBody producto: Producto): Producto =
            productoRepository.save(producto)


    @GetMapping("/productos/{id}")
    fun getProductoById(@PathVariable(value = "id") productoId: Long): ResponseEntity<Producto> {
        return productoRepository.findById(productoId).map { producto ->
            ResponseEntity.ok(producto)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/productos/{id}")
    fun updateProductoById(@PathVariable(value = "id") productoId: Long,
                          @Valid @RequestBody newProducto: Producto): ResponseEntity<Producto> {

        return productoRepository.findById(productoId).map { existingProducto ->
            val updatedProducto: Producto = existingProducto
                    .copy(categoria = newProducto.categoria, nombre = newProducto.nombre, tipo = newProducto.tipo, unidadDeMedida = newProducto.unidadDeMedida)
            ResponseEntity.ok().body(productoRepository.save(updatedProducto))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/productos/{id}")
    fun deleteProductoById(@PathVariable(value = "id") productoId: Long): ResponseEntity<Void> {

        return productoRepository.findById(productoId).map { producto  ->
            productoRepository.delete(producto)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}