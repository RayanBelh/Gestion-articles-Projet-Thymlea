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

### 1. Entités Principales

#### Entité `Categorie`
- **id** : `Long` — Identifiant unique de chaque catégorie.
- **name** : `String` — Nom de la catégorie.
- **description** : `String` — Brève description de la catégorie.
- **articles** : `List<Article>` — Collection d'articles associés à cette catégorie.

#### Entité `Article`
- **id** : `Long` — Identifiant unique de chaque article.
- **title** : `String` — Titre de l'article.
- **content** : `String` — Contenu ou corps de l'article.
- **publicationDate** : `Date` — Date de publication de l'article.
- **categorie** : `Categorie` — Catégorie à laquelle l'article est associé.

### 2. Relations entre les Entités

- **Categorie** et **Article** partagent une relation bidirectionnelle :
  - Une **Categorie** peut contenir plusieurs **Articles** (relation un-à-plusieurs).
  - Un **Article** est associé à une seule **Categorie** (relation plusieurs-à-un).

### 3. Interfaces de Dépôt (Repositories)

#### `CategorieRepository`
- Hérite de `JpaRepository<Categorie, Long>`.
- Fournit des méthodes CRUD pour l'entité `Categorie`.

#### `ArticleRepository`
- Hérite de `JpaRepository<Article, Long>`.
- Fournit des méthodes CRUD pour l'entité `Article`.
- Méthode personnalisée : `List<Article> findByCategorieId(Long id)` — Récupère les articles liés à une catégorie spécifique.

### 4. Contrôleurs

#### `ArticleControlleur`
Gère les requêtes HTTP pour la gestion des articles et des catégories.

##### Méthodes principales :
- `redirectToArticlesList() :` Redirige vers la vue de la liste des articles.
- `listArticles(Model model) :` Affiche la liste de tous les articles.
- `viewCategory(Long id, Model model) :` Affiche les détails d'une catégorie avec ses articles.
- `showAddArticleForm(Model model) :` Affiche le formulaire pour ajouter un nouvel article.
- `addArticle(Article article, BindingResult result, Model model) :` Traite la création d'un nouvel article.
- `showEditArticleForm(long id, Model model) :` Affiche le formulaire de modification d'un article.
- `handleUpdateOrDelete(long id, String action, Article article, BindingResult result, Model model) :` Gère la mise à jour ou la suppression d'un article en fonction de l'action spécifiée.

#### `CategorieControlleur`

Gère les requêtes HTTP pour la gestion des catégories.

#### Méthodes principales :

- `listCategories(Model model)` : Affiche la liste de toutes les catégories.
- `showAddCategorieForm(Model model)` : Affiche le formulaire pour ajouter une nouvelle catégorie.
- `addCategorie(Categorie categorie, BindingResult result, Model model)` : Traite la création d'une nouvelle catégorie.
- `showEditCategorieForm(long id, Model model)` : Affiche le formulaire de modification d'une catégorie.
- `handleUpdateOrDelete(long id, String action, Categorie categorie, BindingResult result, Model model)` : Gère la mise à jour ou la suppression d'une catégorie en fonction de l'action spécifiée.

### 5. Relations entre les Classes

- **ArticleControlleur** dépend de **ArticleRepository** et **CategorieRepository** pour l'accès aux données.
- **ArticleRepository** et **CategorieRepository** interagissent avec les entités **Article** et **Categorie**.
- **Article** et **Categorie** sont connectés par une association bidirectionnelle comme décrit ci-dessus.

Cette structure et ces relations entre classes améliorent l'organisation du code et soutiennent une maintenance et une évolutivité efficaces.
