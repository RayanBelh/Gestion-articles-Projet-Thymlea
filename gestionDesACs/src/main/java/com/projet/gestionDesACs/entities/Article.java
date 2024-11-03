package com.projet.gestionDesACs.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "Articles")
public class Article {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @NotBlank(message = "Title is mandatory")
        private String title;

        @NotBlank(message = "Content is mandatory")
        private String content;

        @Temporal(TemporalType.DATE)
        private Date publicationDate = new Date();

        @ManyToOne
        @JoinColumn(name = "categorie_id", nullable = false)
        private Categorie categorie;

        public Article() {
                super();
        }

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public Date getPublicationDate() {
                return publicationDate;
        }

        public void setPublicationDate(Date publicationDate) {
                this.publicationDate = publicationDate;
        }

        public Categorie getCategorie() {
                return categorie;
        }

        public void setCategorie(Categorie categorie) {  // Corrected setter name to match "Categorie"
                this.categorie = categorie;
        }
}
