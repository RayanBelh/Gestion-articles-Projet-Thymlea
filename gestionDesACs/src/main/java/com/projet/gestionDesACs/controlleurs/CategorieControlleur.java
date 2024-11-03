package com.projet.gestionDesACs.controlleurs;

import com.projet.gestionDesACs.entities.Categorie;
import com.projet.gestionDesACs.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategorieControlleur {

    @Autowired
    private CategorieRepository categorieRepository;

    // Afficher le formulaire pour ajouter une catégorie
    @GetMapping("/add-categorie")
    public String showAddCategorieForm(Model model) {
        model.addAttribute("categorie", new Categorie());
        return "add-categorie"; // Assurez-vous que le template add-categorie.html existe
    }

    // Ajouter une nouvelle catégorie
    @PostMapping("/add-categorie")
    public String addCategorie(@Valid @ModelAttribute("categorie") Categorie categorie,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-categorie";
        }
        categorieRepository.save(categorie);
        return "redirect:/articles"; // Redirige vers la liste des articles après ajout
    }

    // Afficher la liste des catégories
    @GetMapping
    public String showCategoriesList(Model model) {
        List<Categorie> categories = (List<Categorie>) categorieRepository.findAll();
        model.addAttribute("categories", categories);
        return "index";
    }

    // Afficher les détails d'une catégorie spécifique (avec les articles associés)
    @GetMapping("/{id}")
    public String showCategoryDetails(@PathVariable Long id, Model model) {
        Categorie categorie = categorieRepository.findById(id).orElse(null);

        if (categorie == null) {
            return "redirect:/categories"; // Redirige si la catégorie n'existe pas
        }

        model.addAttribute("categorie", categorie);
        model.addAttribute("articles", categorie.getArticles());

        return "categorie-detail"; // Assurez-vous que ce fichier existe dans les templates
    }

    // Afficher le formulaire de modification d'une catégorie
    @GetMapping("/edit-categorie/{id}")
    public String showEditCategorieForm(@PathVariable("id") long id, Model model) {
        Categorie categorie = categorieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id: " + id));
        model.addAttribute("categorie", categorie);
        return "edit-categorie";
    }

    // Gérer la mise à jour ou la suppression de la catégorie dans la même méthode
    @PostMapping("/edit-categorie/{id}")
    public String handleUpdateOrDelete(
            @PathVariable("id") long id,
            @RequestParam(value = "action") String action,
            @Valid @ModelAttribute("categorie") Categorie categorie,
            BindingResult result,
            Model model) {

        if (action.equals("update")) {
            // Vérifie les erreurs de validation lors de la mise à jour
            if (result.hasErrors()) {
                categorie.setId(id); // Conserve l'ID en cas d'erreur
                return "edit-categorie"; // Retourne au formulaire si des erreurs sont présentes
            }
            categorieRepository.save(categorie); // Enregistre la catégorie mise à jour
        } else if (action.equals("delete")) {
            // Supprime la catégorie
            categorieRepository.deleteById(id);
        }
        return "redirect:/articles"; // Redirige vers la liste des catégories après mise à jour ou suppression
    }
}
