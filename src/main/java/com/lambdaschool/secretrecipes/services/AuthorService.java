package com.lambdaschool.secretrecipes.services;

import com.lambdaschool.secretrecipes.models.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();

    Author save(Author author);
}