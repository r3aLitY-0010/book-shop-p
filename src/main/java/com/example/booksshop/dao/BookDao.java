package com.example.booksshop.dao;

import com.example.booksshop.entity.Book;
import com.example.booksshop.entity.BookId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, BookId> {
}
