package com.isi.services;

import com.isi.entities.Article;
import com.isi.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ArticleService {


    public Page<Article> getAllArticles(Optional<Integer> pageNo, Integer pageSize, String sortBy);

    Article findById(long id) throws ResourceNotFoundException;
    public List<Article> getAllArticles();

    void save(Article article);

    void deleteById(long id);

    Article findByIdWithTags(@Param("id") long id);


}
