package com.example.booksshop.service;

import com.example.booksshop.dao.BookDao;
import com.example.booksshop.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookDao bookDao;

    public List<Book> listAllBooks() {
        return bookDao.findAll();
    }

}
