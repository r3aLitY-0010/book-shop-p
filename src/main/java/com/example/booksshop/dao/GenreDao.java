package com.example.booksshop.dao;

import com.example.booksshop.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreDao extends JpaRepository<Genre, Integer> {
}
