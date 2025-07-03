CREATE TABLE type_adherent (
    id SERIAL PRIMARY KEY,
    type_adherent VARCHAR(255),
    quota INT
);

CREATE TABLE adherent (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255),
    email VARCHAR(255),
    mdp VARCHAR(255),
    id_adherent INT REFERENCES type_adherent(id)
);

CREATE TABLE genre (
    id SERIAL PRIMARY KEY,
    genre VARCHAR(255)
);

CREATE TABLE livre (
    id SERIAL PRIMARY KEY,
    titre VARCHAR(255),
    auteur VARCHAR(255),
    annee_publication INT,
    nombre_exemplaires INT
);

CREATE TABLE exemplaire (
    id SERIAL PRIMARY KEY,
    daty DATE,
    id_livre INT REFERENCES livre(id),
    nb_exemplaires INT
);

CREATE TABLE inscription (
    id SERIAL PRIMARY KEY,
    id_adherent INT REFERENCES adherent(id),
    date_inscription DATE,
    date_expiration DATE
);

CREATE TABLE pret (
    id SERIAL PRIMARY KEY,
    id_adherent INT REFERENCES adherent(id),
    id_exemplaire INT REFERENCES exemplaire(id),
    date_pret DATE,
    date_retour_prevu DATE
);

CREATE TABLE prolongement (
    id SERIAL PRIMARY KEY,
    id_pret INT REFERENCES pret(id),
    daty DATE,
    jours_supplementaires INT
);

CREATE TABLE reservation (
    id SERIAL PRIMARY KEY,
    id_adherent INT REFERENCES adherent(id),
    id_exemplaire INT REFERENCES exemplaire(id),
    date_reservation DATE
);

CREATE TABLE retour_livre (
    id SERIAL PRIMARY KEY,
    id_pret INT REFERENCES pret(id),
    date_retour DATE
);

CREATE TABLE statut_pret (
    id SERIAL PRIMARY KEY,
    id_pret INT REFERENCES pret(id),
    daty DATE,
    statut INT
);

CREATE TABLE statut_quota (
    id SERIAL PRIMARY KEY,
    daty DATE,
    id_adherent INT REFERENCES adherent(id),
    quota INT
);

CREATE TABLE historiques_penalisation (
    id SERIAL PRIMARY KEY,
    id_adherent INT REFERENCES adherent(id),
    date_debut DATE,
    date_fin DATE
);

CREATE TABLE livre_genre (
    id_livre INT REFERENCES livre(id),
    id_genre INT REFERENCES genre(id),
    PRIMARY KEY (id_livre, id_genre)
);

CREATE TABLE livre_typeadherent (
    id SERIAL PRIMARY KEY,
    id_livre INT REFERENCES livre(id),
    id_type_adherent INT REFERENCES type_adherent(id)
);

CREATE TABLE admin (
      id SERIAL PRIMARY KEY,
      pseudo VARCHAR(255),
      mdp VARCHAR(255)
);

CREATE TABLE bibliothecaire (
      id SERIAL PRIMARY KEY,
      pseudo VARCHAR(255),
      mdp VARCHAR(255)
);