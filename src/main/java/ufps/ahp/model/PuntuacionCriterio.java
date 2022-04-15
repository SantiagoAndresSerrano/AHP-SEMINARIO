package ufps.ahp.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name = "puntuacion_criterio")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "PuntuacionCriterio.findAll", query = "SELECT p FROM PuntuacionCriterio p"),
        @NamedQuery(name = "PuntuacionCriterio.findByIdPuntuacionDecisor", query = "SELECT p FROM PuntuacionCriterio p WHERE p.idPuntuacionDecisor = :idPuntuacionDecisor"),
        @NamedQuery(name = "PuntuacionCriterio.findByCriterio1Id", query = "SELECT p FROM PuntuacionCriterio p WHERE p.criterio1Id = :criterio1Id"),
        @NamedQuery(name = "PuntuacionCriterio.findByCriterio2Id", query = "SELECT p FROM PuntuacionCriterio p WHERE p.criterio2Id = :criterio2Id"),
        @NamedQuery(name = "PuntuacionCriterio.findByValor", query = "SELECT p FROM PuntuacionCriterio p WHERE p.valor = :valor")})
public class PuntuacionCriterio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_puntuacion_decisor")
    private Integer idPuntuacionDecisor;
    @JoinColumn(name = "criterio1_id", referencedColumnName = "id_criterio")
    @ManyToOne(optional = false)
    private Criterio criterio1Id;
    @JoinColumn(name = "criterio2_id", referencedColumnName = "id_criterio")
    @ManyToOne(optional = false)
    private Criterio criterio2Id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private int valor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "puntuacionCriterio")
    private Collection<Puntuacion> puntuacionCollection;
    @JoinColumn(name = "problema", referencedColumnName = "id_problema")
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Problema problema;

    public PuntuacionCriterio() {
    }

    public PuntuacionCriterio(Integer idPuntuacionDecisor) {
        this.idPuntuacionDecisor = idPuntuacionDecisor;
    }

    public PuntuacionCriterio(Integer idPuntuacionDecisor, Criterio criterio1Id, Criterio criterio2Id, int valor) {
        this.idPuntuacionDecisor = idPuntuacionDecisor;
        this.criterio1Id = criterio1Id;
        this.criterio2Id = criterio2Id;
        this.valor = valor;
    }

    public Integer getIdPuntuacionDecisor() {
        return idPuntuacionDecisor;
    }

    public void setIdPuntuacionDecisor(Integer idPuntuacionDecisor) {
        this.idPuntuacionDecisor = idPuntuacionDecisor;
    }

    public Criterio getCriterio1Id() {
        return criterio1Id;
    }

    public Criterio getCriterio2Id() {
        return criterio2Id;
    }

    public void setCriterio1Id(Criterio criterio1Id) {
        this.criterio1Id = criterio1Id;
    }

    public void setCriterio2Id(Criterio criterio2Id) {
        this.criterio2Id = criterio2Id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @XmlTransient
    public Collection<Puntuacion> getPuntuacionCollection() {
        return puntuacionCollection;
    }

    public void setPuntuacionCollection(Collection<Puntuacion> puntuacionCollection) {
        this.puntuacionCollection = puntuacionCollection;
    }

    public Problema getProblema() {
        return problema;
    }

    public void setProblema(Problema problema) {
        this.problema = problema;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPuntuacionDecisor != null ? idPuntuacionDecisor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PuntuacionCriterio)) {
            return false;
        }
        PuntuacionCriterio other = (PuntuacionCriterio) object;
        if ((this.idPuntuacionDecisor == null && other.idPuntuacionDecisor != null) || (this.idPuntuacionDecisor != null && !this.idPuntuacionDecisor.equals(other.idPuntuacionDecisor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.rest.PuntuacionCriterio[ idPuntuacionDecisor=" + idPuntuacionDecisor + " ]";
    }

}
