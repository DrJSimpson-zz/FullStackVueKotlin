package com.example.kotlindemo.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Stock (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "productoId")
        val producto: Producto = Producto(),

        val cantidad: Double = 0.0
)