package com.example.kotlindemo.controller

import com.example.kotlindemo.model.Stock
import com.example.kotlindemo.repository.StockRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@CrossOrigin(origins =  ["*"]) //this will enable CORS on all endpoints for the controller
@RequestMapping("/api")
class StockController(private val stockRepository: StockRepository) {

    @GetMapping("/stocks")
    fun getAllStocks(): List<Stock> =
            stockRepository.findAll()


    @PostMapping("/stocks")
    fun createNewStock(@Valid @RequestBody stock: Stock): Stock =
            stockRepository.save(stock)


    @GetMapping("/stocks/{id}")
    fun getStockById(@PathVariable(value = "id") stockId: Long): ResponseEntity<Stock> {
        return stockRepository.findById(stockId).map { stock ->
            ResponseEntity.ok(stock)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/stocks/{id}")
    fun updateStockById(@PathVariable(value = "id") stockId: Long,
                          @Valid @RequestBody newStock: Stock): ResponseEntity<Stock> {

        return stockRepository.findById(stockId).map { existingStock ->
            val updatedStock: Stock = existingStock
                    .copy(cantidad = newStock.cantidad, producto = newStock.producto)
            ResponseEntity.ok().body(stockRepository.save(updatedStock))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/stocks/{id}")
    fun deleteStockById(@PathVariable(value = "id") stockId: Long): ResponseEntity<Void> {

        return stockRepository.findById(stockId).map { stock  ->
            stockRepository.delete(stock)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}