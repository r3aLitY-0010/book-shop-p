package com.example.booksshop.dao;

import com.example.booksshop.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherDao extends JpaRepository<Publisher, Integer> {
}
