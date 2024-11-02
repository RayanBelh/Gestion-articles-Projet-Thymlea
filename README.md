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

-controlleurs :- Contient les classes contrôleurs gérant les requêtes HTTP.
entities : Contient les classes entités représentant les modèles de données.
repositories : Contient les interfaces des dépôts pour l'accès aux données.
src/main/resources/templates : Contient les fichiers Thymeleaf pour les vues.

src/main/resources/application.properties : Fichier de configuration de l'application.
 
