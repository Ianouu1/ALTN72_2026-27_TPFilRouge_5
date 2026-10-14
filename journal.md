# Journal — GareÀVous (ALTN72, 2026-27)

> Supervision d'équipements et suivi d'incidents sur un réseau de transport.
> *Nom de projet provisoire, à valider en équipe (cf. sujet §12).*

**Groupe :** Alexandre GINISTY, Florian

---

## README

- **Prérequis** : Java 21, Maven 3.9+, MariaDB (XAMPP ou équivalent).
- **Initialiser la base** : exécuter `db/create.sql` (crée `Base_ALTN72`, le schéma et les données de démonstration).
- **Configuration** : le mot de passe de la base est lu dans la variable d'environnement `DB_PASSWORD` (vide par défaut sur un poste XAMPP). Aucun secret n'est versionné.
- **Lancer** : `./mvnw spring-boot:run` (après avoir initialisé la base).
- **Lancer les tests** : `./mvnw test`
- **Application déployée** : _à venir (séance déploiement)_
- **Comptes de test** (mot de passe commun de démo : `password`, haché bcrypt en base) :
  - `resp` — Chloé, profil **Responsable d'exploitation**.
  - `agent` — Karim, profil **Agent**.

  _L'écran de connexion (F1) sera actif une fois Spring Security câblé (séance sécurité) ; les hachés de démo seront alors revalidés._
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
- **Requête SQL native** : une seule dans tout le projet, `IncidentRepository.compterIncidentsActifsParStation()` — comptage des incidents actifs regroupés par station (jointure `incident → equipement → station`).
  - *Analyse critique* : une agrégation multi-tables se lit bien en SQL et reste proche de ce qu'exécute la base, mais ce choix a un coût : la requête est liée au dialecte et aux noms de colonnes physiques (elle casse si le schéma évolue, sans que le compilateur n'alerte), elle renvoie un `Object[]` non typé, et elle échappe au cache et au mapping JPA. Une projection JPQL ou une `interface`-projection serait plus sûre ; on garde ici une native pour illustrer le procédé, comme demandé (§7.6.2).
- **@Transactional** : _à documenter lorsque les cas d'usage d'écriture seront en place._

## LOT ALPHA

- _Deux fonctionnalités transverses à choisir dès le début du projet (§6)._

## BILAN

- _À compléter en fin de projet (contributions, difficultés, points à retenir)._
