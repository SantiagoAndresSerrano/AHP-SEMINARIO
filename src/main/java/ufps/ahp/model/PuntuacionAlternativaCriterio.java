package ufps.ahp.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author santi
 */
@Entity
@Table(name = "puntuacion_alternativa_criterio")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "PuntuacionAlternativaCriterio.findAll", query = "SELECT p FROM PuntuacionAlternativaCriterio p"),
        @NamedQuery(name = "PuntuacionAlternativaCriterio.findByIdPuntuacionAltCrit", query = "SELECT p FROM PuntuacionAlternativaCriterio p WHERE p.idPuntuacionAltCrit = :idPuntuacionAltCrit"),
        @NamedQuery(name = "PuntuacionAlternativaCriterio.findByAlternativa1", query = "SELECT p FROM PuntuacionAlternativaCriterio p WHERE p.alternativa1 = :alternativa1"),
        @NamedQuery(name = "PuntuacionAlternativaCriterio.findByAlternativa2", query = "SELECT p FROM PuntuacionAlternativaCriterio p WHERE p.alternativa2 = :alternativa2"),
        @NamedQuery(name = "PuntuacionAlternativaCriterio.findByValor", query = "SELECT p FROM PuntuacionAlternativaCriterio p WHERE p.valor = :valor")})
public class PuntuacionAlternativaCriterio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_puntuacion_alt_crit")
    private Integer idPuntuacionAltCrit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "alternativa_1")
    private int alternativa1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "alternativa_2")
    private int alternativa2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private int valor;
    @JoinColumn(name = "problema", referencedColumnName = "id_problema")
    @ManyToOne(optional = false)
    private Problema problema;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "puntuacionAlternativaCriterio")
    private Collection<PuntuacionAlternativa> puntuacionAlternativaCollection;

    public PuntuacionAlternativaCriterio() {
    }

    public PuntuacionAlternativaCriterio(Integer idPuntuacionAltCrit) {
        this.idPuntuacionAltCrit = idPuntuacionAltCrit;
    }

    public PuntuacionAlternativaCriterio(Integer idPuntuacionAltCrit, int alternativa1, int alternativa2, int valor) {
        this.idPuntuacionAltCrit = idPuntuacionAltCrit;
        this.alternativa1 = alternativa1;
        this.alternativa2 = alternativa2;
        this.valor = valor;
    }

    public Integer getIdPuntuacionAltCrit() {
        return idPuntuacionAltCrit;
    }

    public void setIdPuntuacionAltCrit(Integer idPuntuacionAltCrit) {
        this.idPuntuacionAltCrit = idPuntuacionAltCrit;
    }

    public int getAlternativa1() {
        return alternativa1;
    }

    public void setAlternativa1(int alternativa1) {
        this.alternativa1 = alternativa1;
    }

    public int getAlternativa2() {
        return alternativa2;
    }

    public void setAlternativa2(int alternativa2) {
        this.alternativa2 = alternativa2;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Problema getProblema() {
        return problema;
    }

    public void setProblema(Problema problema) {
        this.problema = problema;
    }

    @XmlTransient
    public Collection<PuntuacionAlternativa> getPuntuacionAlternativaCollection() {
        return puntuacionAlternativaCollection;
    }

    public void setPuntuacionAlternativaCollection(Collection<PuntuacionAlternativa> puntuacionAlternativaCollection) {
        this.puntuacionAlternativaCollection = puntuacionAlternativaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPuntuacionAltCrit != null ? idPuntuacionAltCrit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PuntuacionAlternativaCriterio)) {
            return false;
        }
        PuntuacionAlternativaCriterio other = (PuntuacionAlternativaCriterio) object;
        if ((this.idPuntuacionAltCrit == null && other.idPuntuacionAltCrit != null) || (this.idPuntuacionAltCrit != null && !this.idPuntuacionAltCrit.equals(other.idPuntuacionAltCrit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.rest.PuntuacionAlternativaCriterio[ idPuntuacionAltCrit=" + idPuntuacionAltCrit + " ]";
    }

}
