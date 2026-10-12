# Journal — GareÀVous (ALTN72, 2026-27)

> Supervision d'équipements et suivi d'incidents sur un réseau de transport.
> *Nom de projet provisoire, à valider en équipe (cf. sujet §12).*

**Groupe :** Alexandre GINISTY, Florian

---

## README

- **Prérequis** : Java 21, Maven 3.9+, MariaDB (XAMPP ou équivalent).
- **Initialiser la base** : exécuter `db/create.sql` (crée `Base_ALTN72`, le schéma et les données de démonstration).
- **Configuration** : le mot de passe de la base est lu dans la variable d'environnement `DB_PASSWORD` (vide par défaut sur un poste XAMPP). Aucun secret n'est versionné.
- **Lancer** : `./mvnw spring-boot:run`
- **Application déployée** : _à venir (séance déploiement)_
- **Comptes de test** : _voir section CHOIX TECHNIQUES → Droits par profil (renseignés une fois Spring Security câblé)._
- **Limites connues** : à ce stade, seuls le modèle, la persistance et le calcul de l'indicateur d'urgence sont en place. Authentification, API REST, vues et lot alpha à venir.

## ARCHITECTURE

- **Dépôt** : _à renseigner._
- **Découpage en couches** (séparation des responsabilités, SRP) :
  - `altn72.tpfilrouge.modele` — entités JPA + interfaces `JpaRepository` (accès aux données).
  - `altn72.tpfilrouge.service` — règles métier (ex. calcul de l'indicateur d'urgence RG3).
  - `altn72.tpfilrouge.controleur` — à venir (couche web / API REST).
  - `altn72.tpfilrouge.config` — configuration transverse (ex. horloge injectable).
- **Schéma** : _à ajouter (docs/architecture.png)._

## TESTS

- **Stratégie** : tests unitaires sans contexte Spring pour les règles métier (horloge figée via `java.time.Clock`), tests d'intégration à venir.
- **Commande** : `./mvnw test`
- **Couvert** : seuils de l'indicateur d'urgence (47 h → vert, 48 h → orange, 71 h → orange, 72 h → rouge — RG3).
- **Non couvert (encore)** : cycle de vie des incidents, API REST, sécurité — fonctionnalités pas encore développées.

## CHOIX TECHNIQUES

- **Droits par profil** (§5.6) : _à compléter (profils pressentis : Responsable d'exploitation / Agent)._
- **Exceptions** : `@ControllerAdvice` vs `ResponseStatusException` — _choix et justification à venir (séance gestion des exceptions)._
- **SOLID** : principe(s) appliqué(s) — _à documenter ; une première trace : l'injection par constructeur de l'horloge et des repositories (D — Dependency Inversion)._
- **Requête SQL native** : _voir section dédiée ci-dessous._
- **@Transactional** : _à documenter lorsque les cas d'usage d'écriture seront en place._

## LOT ALPHA

- _Deux fonctionnalités transverses à choisir dès le début du projet (§6)._

## BILAN

- _À compléter en fin de projet (contributions, difficultés, points à retenir)._
