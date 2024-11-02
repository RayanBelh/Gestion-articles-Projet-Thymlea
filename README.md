# Gestion des Articles et Catégories

Ce projet est une application web développée avec Spring Boot, permettant de gérer des articles et leurs catégories associées. Il offre des fonctionnalités CRUD (Créer, Lire, Mettre à jour, Supprimer) pour les articles et les catégories.

## Fonctionnalités

- **Gestion des catégories** :
  - Ajouter une nouvelle catégorie.
  - Consulter la liste des catégories.
  - Modifier les détails d'une catégorie.
  - Supprimer une catégorie.

- **Gestion des articles** :
  - Ajouter un nouvel article.
  - Consulter la liste des articles.
  - Modifier les détails d'un article.
  - Supprimer un article.

## Prérequis

- Java 17 ou supérieur.
- Maven 3.x.
- Un IDE compatible avec Spring Boot (par exemple, IntelliJ IDEA ou Eclipse).

## Structure du projet 
La structure du projet est organisée comme suit :
```css
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── projet/
│   │           └── gestionDesACs/
│   │               ├── controlleurs/
│   │               ├── entities/
│   │               └── repositories/
│   └── resources/
│       ├── templates/
│       └── application.properties

```
- src/main/java/com/projet/gestionDesACs :

`controlleurs :` Contient les classes contrôleurs gérant les requêtes HTTP.

`entities :` Contient les classes entités représentant les modèles de données.

`repositories :` Contient les interfaces des dépôts pour l'accès aux données.

- src/main/resources/templates : Contient les fichiers Thymeleaf pour les vues.

- src/main/resources/application.properties : Fichier de configuration de l'application.

 ## Diagramme de classe 

### 1. Key Entities

#### `Categorie` Entity
- **id** : `Long` — Unique identifier for each category.
- **name** : `String` — Name of the category.
- **description** : `String` — Brief description of the category.
- **articles** : `List<Article>` — Collection of articles within this category.

#### `Article` Entity
- **id** : `Long` — Unique identifier for each article.
- **title** : `String` — Title of the article.
- **content** : `String` — Content/body of the article.
- **publicationDate** : `Date` — Date when the article was published.
- **categorie** : `Categorie` — Category this article is associated with.

### 2. Entity Relationships

- **Categorie** and **Article** share a bidirectional relationship:
  - A **Categorie** can contain multiple **Articles** (one-to-many).
  - An **Article** is associated with a single **Categorie** (many-to-one).

### 3. Repository Interfaces

#### `CategorieRepository`
- Inherits from `JpaRepository<Categorie, Long>`.
- Provides CRUD methods for the `Categorie` entity.

#### `ArticleRepository`
- Inherits from `JpaRepository<Article, Long>`.
- Provides CRUD methods for the `Article` entity.
- Custom method: `List<Article> findByCategorieId(Long id)` — Fetches articles linked to a specific category.

### 4. Controllers

#### `ArticleControlleur`
Handles HTTP requests for managing articles and categories.

##### Key Methods:
- **redirectToArticlesList()** : Redirects to the article list view.
- **listArticles(Model model)** : Displays the list of all articles.
- **viewCategory(Long id, Model model)** : Shows details of a category along with its articles.
- **showAddArticleForm(Model model)** : Shows the form to add a new article.
- **addArticle(Article article, BindingResult result, Model model)** : Processes the creation of a new article.
- **showEditArticleForm(long id, Model model)** : Displays the form to edit an article.
- **handleUpdateOrDelete(long id, String action, Article article, BindingResult result, Model model)** : Processes update or deletion of an article based on specified action.

### 5. Class Relationships

- **ArticleControlleur** depends on **ArticleRepository** and **CategorieRepository** for data access.
- **ArticleRepository** and **CategorieRepository** interact with **Article** and **Categorie** entities.
- **Article** and **Categorie** are connected by a bidirectional association as described.

This project structure and class relationships improve code organization and support effective maintenance and scalability.
