package com.projet.gestionDesACs.repositories;

import com.projet.gestionDesACs.entities.Categorie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends CrudRepository<Categorie, Long> {
}
