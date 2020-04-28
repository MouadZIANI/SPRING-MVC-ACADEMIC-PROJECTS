package com.isi.services;


import com.isi.entities.Article;
import com.isi.exceptions.ResourceNotFoundException;
import com.isi.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl  implements ArticleService{

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    @Transactional
    public Page<Article>  getAllArticles(Optional<Integer> pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging =PageRequest.of(0, pageSize, Sort.by(sortBy));
        if(pageNo.isPresent()){
             paging = PageRequest.of(pageNo.get(), pageSize, Sort.by(sortBy));
        }
        return articleRepository.findAll(paging);
    }

    @Override
    @Transactional
    public Article findById(long id) throws ResourceNotFoundException {
        return articleRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(id)
        );
    }

    @Override
    @Transactional
    public void save(Article article) {
         Timestamp timestamp = new Timestamp(System.currentTimeMillis());
         article.setCreated(timestamp);
         articleRepository.save(article);
    }

    @Override
    @Transactional
    public void deleteById(long id) {       articleRepository.deleteById(id); }

    @Override
    @Transactional
    public Article findByIdWithTags(long id) {
        return articleRepository.findByIdWithTags(id);
    }

	@Override
	public List<Article> getAllArticles() {
		// TODO Auto-generated method stub
		return articleRepository.findAll();
	}


}
