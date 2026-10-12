package altn72.tpfilrouge.modele;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Accès aux équipements.
 */
public interface EquipementRepository extends JpaRepository<Equipement, Integer> {

    /** Équipements d'une station, triés par libellé (F2). */
    List<Equipement> findByStation_IdStationOrderByLibelleAsc(Integer idStation);

    /** Recherche par référence unique (RG9). */
    Optional<Equipement> findByReference(String reference);

    /** Vérifie l'unicité de la référence avant création (RG9). */
    boolean existsByReference(String reference);
}
