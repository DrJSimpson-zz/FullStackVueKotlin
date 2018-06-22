package com.example.kotlindemo.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
data class Compra (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @get: NotBlank
        val monto: Double = 0.0,

        @get: NotBlank
        val formaDePago: FormaDePago = FormaDePago.EFECTIVO,

        @get: NotBlank
        val detalle: String = ""
)