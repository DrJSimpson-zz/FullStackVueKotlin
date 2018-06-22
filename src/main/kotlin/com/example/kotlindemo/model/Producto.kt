package com.example.kotlindemo.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
data class Producto (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @get: NotBlank
        val nombre: String = "",

        @get: NotBlank
        val unidadDeMedida: UnidadDeMedida = UnidadDeMedida.UNIDAD,

        @get: NotBlank
        val categoria: Categoria = Categoria.OTROS,

        @get: NotBlank
        val tipo: Tipo = Tipo.NOCONSUMIBLE
        )