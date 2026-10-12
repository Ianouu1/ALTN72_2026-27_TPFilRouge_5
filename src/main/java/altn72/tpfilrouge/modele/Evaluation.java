package altn72.tpfilrouge.modele;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

/**
 * Évaluation de l'intervention d'un prestataire sur un incident résolu (RG8).
 * Un incident ne peut porter qu'une seule évaluation ; la note est un entier de 1 à 5.
 */
@Entity
@Table(name = "evaluation")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluation")
    private Integer idEvaluation;

    /** Un incident évalué une seule fois (RG8) : relation un-à-un avec contrainte d'unicité. */
    @OneToOne(optional = false)
    @JoinColumn(name = "id_incident", nullable = false, unique = true)
    private Incident incident;

    @Column(name = "note", nullable = false)
    private int note;

    @Column(name = "commentaire", length = 1000)
    private String commentaire;

    @Column(name = "date_evaluation", nullable = false)
    private LocalDateTime dateEvaluation;

    protected Evaluation() {
        // Requis par JPA.
    }

    public Evaluation(Incident incident, int note, String commentaire, LocalDateTime dateEvaluation) {
        this.incident = incident;
        this.note = note;
        this.commentaire = commentaire;
        this.dateEvaluation = dateEvaluation;
    }

    public Integer getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(Integer idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public Incident getIncident() {
        return incident;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public LocalDateTime getDateEvaluation() {
        return dateEvaluation;
    }

    public void setDateEvaluation(LocalDateTime dateEvaluation) {
        this.dateEvaluation = dateEvaluation;
    }

    @Override
    public String toString() {
        return "Evaluation{idEvaluation=" + idEvaluation + ", note=" + note + "}";
    }
}
