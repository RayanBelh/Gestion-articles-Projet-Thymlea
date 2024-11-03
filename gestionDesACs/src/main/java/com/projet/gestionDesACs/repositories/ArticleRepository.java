package com.projet.gestionDesACs.repositories;

import com.projet.gestionDesACs.entities.Article;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    List<Article> findByCategorieId(Long id); // Method name retained
}
