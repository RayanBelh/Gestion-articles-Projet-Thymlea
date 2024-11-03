package com.projet.gestionDesACs.controlleurs;

import com.projet.gestionDesACs.entities.Article;
import com.projet.gestionDesACs.entities.Categorie;
import com.projet.gestionDesACs.repositories.ArticleRepository;
import com.projet.gestionDesACs.repositories.CategorieRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ArticleControlleur {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    // Redirection vers la liste des articles
    @GetMapping("/")
    public String redirectToArticlesList() {
        return "redirect:/articles"; // Redirige vers la liste des articles
    }

    // Afficher la liste des articles
    @GetMapping("/articles")
    public String listArticles(Model model) {
        model.addAttribute("categories", categorieRepository.findAll());
        return "articles-list"; // Renvoie à la vue articles-list.html
    }

    // Détails d'une catégorie avec ses articles
    @GetMapping("/articles/category/{id}")
    public String viewCategory(@PathVariable Long id, Model model) {
        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id: " + id));
        List<Article> articles = articleRepository.findByCategorieId(id);

        model.addAttribute("categorie", categorie);
        model.addAttribute("articles", articles);
        return "category-detail"; // Renvoie à la vue category-detail.html
    }

    // Afficher le formulaire pour ajouter un nouvel article
    @GetMapping("/add-article")
    public String showAddArticleForm(Model model) {
        model.addAttribute("article", new Article()); // Ensure this line is present
        model.addAttribute("categories", categorieRepository.findAll()); // Include categories if needed
        return "add-article";
    }
    @GetMapping("/articles-list")
    public String showArticlesList(Model model) {
        // Ajoutez ici la logique pour récupérer la liste des articles
        return "redirect:/articles"; // Nom de la vue Thymeleaf
    }
    // Gestion de l'ajout d'un article
    @PostMapping("/add-article")
    public String addArticle(@Valid @ModelAttribute("article") Article article,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categorieRepository.findAll());
            return "add-article";
        }
        articleRepository.save(article);
        return "redirect:/articles"; // Redirige vers la liste des articles
    }

    // Afficher le formulaire de modification d'un article
    @GetMapping("/edit-article/{id}")
    public String showEditArticleForm(@PathVariable("id") long id, Model model) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article Id: " + id));
        model.addAttribute("article", article);
        model.addAttribute("categories", categorieRepository.findAll()); // Charge les catégories pour le formulaire
        return "edit-article"; // Renvoie à la vue edit-article.html
    }

    // Gérer la mise à jour ou la suppression de l'article dans la même méthode
    // Gérer la mise à jour ou la suppression de l'article dans la même méthode
    @PostMapping("/edit-article/{id}")
    public String handleUpdateOrDelete(
            @PathVariable("id") long id,
            @RequestParam(value = "action") String action,
            @Valid @ModelAttribute("article") Article article,
            BindingResult result,
            Model model) {

        if (action.equals("update")) {
            System.out.println("Mise à jour de l'article...");

            if (result.hasErrors()) {
                model.addAttribute("categories", categorieRepository.findAll());
                article.setId(id);
                return "edit-article";
            }
            articleRepository.save(article);
            return "redirect:/categories/" + article.getCategorie().getId(); // Redirect to category detail after update
        } else if (action.equals("delete")) {
            System.out.println("Suppression de l'article...");

            // Get the category ID before deletion
            Long categoryId = article.getCategorie().getId();
            articleRepository.deleteById(id);

            // Redirect to the categorie-detail page with the category ID
            return "redirect:/categories/" + categoryId;
        }

        return "redirect:/articles"; // Par défaut, redirige vers la liste des articles
    }



}
