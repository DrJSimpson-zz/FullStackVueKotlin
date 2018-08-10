package com.example.kotlindemo.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Producto (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @get: NotBlank
        val nombre: String = "",

        @Enumerated(EnumType.STRING)
        val unidadDeMedida: UnidadDeMedida = UnidadDeMedida.UNIDAD,

        @Enumerated(EnumType.STRING)
        val categoria: Categoria = Categoria.OTROS,

        @Enumerated(EnumType.STRING)
        val tipo: Tipo = Tipo.NOCONSUMIBLE
        )