package com.example.kotlindemo.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Compra (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        val monto: Double = 0.0,

        @Enumerated(EnumType.STRING)
        val formaDePago: FormaDePago = FormaDePago.EFECTIVO,

        @get: NotBlank
        val detalle: String = ""
)