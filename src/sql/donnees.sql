-- Données pour la table admin
INSERT INTO admin (pseudo, mdp) VALUES ('admin1', 'adminpass');
INSERT INTO admin (pseudo, mdp) VALUES ('superadmin', 'superpass');

-- Données pour la table type_adherent
INSERT INTO type_adherent (type_adherent, quota) VALUES ('Etudiant', 5);
INSERT INTO type_adherent (type_adherent, quota) VALUES ('Enseignant', 10);
INSERT INTO type_adherent (type_adherent, quota) VALUES ('Externe', 2);

-- Données pour la table adherent
INSERT INTO adherent (nom, email, mdp, id_adherent) VALUES ('Jean', 'dupont@email.com', '123', 1);
INSERT INTO adherent (nom, email, mdp, id_adherent) VALUES ('Alice', 'martin@email.com', '456', 2);
INSERT INTO adherent (nom, email, mdp, id_adherent) VALUES ('Paul', 'durand@email.com', '789', 3);

-- Données pour la table bibliothecaire
INSERT INTO bibliothecaire (pseudo, mdp) VALUES ('biblio1', '123');
INSERT INTO bibliothecaire (pseudo, mdp) VALUES ('biblio2', '456');

-- Données pour la table genre
INSERT INTO genre (genre) VALUES ('Roman');
INSERT INTO genre (genre) VALUES ('Science-Fiction');
INSERT INTO genre (genre) VALUES ('Policier');
INSERT INTO genre (genre) VALUES ('Biographie');

-- Données pour la table livre
INSERT INTO livre (titre, auteur, annee_publication, nombre_exemplaires) VALUES ('Le Petit Prince', 'Antoine de Saint-Exupéry', 1943, 5);
INSERT INTO livre (titre, auteur, annee_publication, nombre_exemplaires) VALUES ('1984', 'George Orwell', 1949, 3);
INSERT INTO livre (titre, auteur, annee_publication, nombre_exemplaires) VALUES ('Le Meurtre de Roger Ackroyd', 'Agatha Christie', 1926, 2);
INSERT INTO livre (titre, auteur, annee_publication, nombre_exemplaires) VALUES ('Steve Jobs', 'Walter Isaacson', 2011, 4);

-- Données pour la table livre_genre
INSERT INTO livre_genre (id_livre, id_genre) VALUES (1, 1); -- Le Petit Prince, Roman
INSERT INTO livre_genre (id_livre, id_genre) VALUES (2, 2); -- 1984, Science-Fiction
INSERT INTO livre_genre (id_livre, id_genre) VALUES (3, 3); -- Le Meurtre de Roger Ackroyd, Policier
INSERT INTO livre_genre (id_livre, id_genre) VALUES (4, 4); -- Steve Jobs, Biographie
INSERT INTO livre_genre (id_livre, id_genre) VALUES (2, 1); -- 1984, aussi Roman

-- Données pour la table exemplaire
INSERT INTO exemplaire (daty, id_livre, nb_exemplaires) VALUES ('2025-07-01', 1, 2);
INSERT INTO exemplaire (daty, id_livre, nb_exemplaires) VALUES ('2025-07-01', 2, 1);
INSERT INTO exemplaire (daty, id_livre, nb_exemplaires) VALUES ('2025-07-01', 3, 1);
INSERT INTO exemplaire (daty, id_livre, nb_exemplaires) VALUES ('2025-07-01', 4, 2);

-- Données pour la table livre_typeadherent
INSERT INTO livre_typeadherent (id_livre, id_type_adherent) VALUES (1, 1); -- Le Petit Prince, Etudiant
INSERT INTO livre_typeadherent (id_livre, id_type_adherent) VALUES (1, 2); -- Le Petit Prince, Enseignant
INSERT INTO livre_typeadherent (id_livre, id_type_adherent) VALUES (1, 3); -- Le Petit Prince, Externe
INSERT INTO livre_typeadherent (id_livre, id_type_adherent) VALUES (2, 1); -- 1984, Etudiant
INSERT INTO livre_typeadherent (id_livre, id_type_adherent) VALUES (2, 2); -- 1984, Enseignant
INSERT INTO livre_typeadherent (id_livre, id_type_adherent) VALUES (3, 3); -- Le Meurtre de Roger Ackroyd, Externe
INSERT INTO livre_typeadherent (id_livre, id_type_adherent) VALUES (4, 1); -- Steve Jobs, Etudiant


