package com.example.kotlindemo.repository

import com.example.kotlindemo.model.Article
import com.example.kotlindemo.model.Producto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductoRepository : JpaRepository<Producto, Long>