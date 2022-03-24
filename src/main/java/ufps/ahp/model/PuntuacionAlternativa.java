package ufps.ahp.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author santi
 */
@Entity
@Table(name = "puntuacion_alternativa")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "PuntuacionAlternativa.findAll", query = "SELECT p FROM PuntuacionAlternativa p"),
        @NamedQuery(name = "PuntuacionAlternativa.findByIdPuntuacionAlternativa", query = "SELECT p FROM PuntuacionAlternativa p WHERE p.idPuntuacionAlternativa = :idPuntuacionAlternativa"),
        @NamedQuery(name = "PuntuacionAlternativa.findByValor", query = "SELECT p FROM PuntuacionAlternativa p WHERE p.valor = :valor")})
public class PuntuacionAlternativa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_puntuacion_alternativa")
    private Integer idPuntuacionAlternativa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private int valor;
    @JoinColumn(name = "puntuacion_alternativa_criterio", referencedColumnName = "id_puntuacion_alt_crit")
    @ManyToOne(optional = false)
    private PuntuacionAlternativaCriterio puntuacionAlternativaCriterio;
    @JoinColumn(name = "decisor", referencedColumnName = "id_decisor")
    @ManyToOne(optional = false)
    private Decisor decisor;

    public PuntuacionAlternativa() {
    }

    public PuntuacionAlternativa(Integer idPuntuacionAlternativa) {
        this.idPuntuacionAlternativa = idPuntuacionAlternativa;
    }

    public PuntuacionAlternativa(Integer idPuntuacionAlternativa, int valor) {
        this.idPuntuacionAlternativa = idPuntuacionAlternativa;
        this.valor = valor;
    }

    public Integer getIdPuntuacionAlternativa() {
        return idPuntuacionAlternativa;
    }

    public void setIdPuntuacionAlternativa(Integer idPuntuacionAlternativa) {
        this.idPuntuacionAlternativa = idPuntuacionAlternativa;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public PuntuacionAlternativaCriterio getPuntuacionAlternativaCriterio() {
        return puntuacionAlternativaCriterio;
    }

    public void setPuntuacionAlternativaCriterio(PuntuacionAlternativaCriterio puntuacionAlternativaCriterio) {
        this.puntuacionAlternativaCriterio = puntuacionAlternativaCriterio;
    }

    public Decisor getDecisor() {
        return decisor;
    }

    public void setDecisor(Decisor decisor) {
        this.decisor = decisor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPuntuacionAlternativa != null ? idPuntuacionAlternativa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PuntuacionAlternativa)) {
            return false;
        }
        PuntuacionAlternativa other = (PuntuacionAlternativa) object;
        if ((this.idPuntuacionAlternativa == null && other.idPuntuacionAlternativa != null) || (this.idPuntuacionAlternativa != null && !this.idPuntuacionAlternativa.equals(other.idPuntuacionAlternativa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.rest.PuntuacionAlternativa[ idPuntuacionAlternativa=" + idPuntuacionAlternativa + " ]";
    }

}
