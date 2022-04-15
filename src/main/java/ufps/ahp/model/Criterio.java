package ufps.ahp.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author santi
 */
@Entity
@Table(name = "criterio")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Criterio.findAll", query = "SELECT c FROM Criterio c"),
        @NamedQuery(name = "Criterio.findByIdCriterio", query = "SELECT c FROM Criterio c WHERE c.idCriterio = :idCriterio")})
public class Criterio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_criterio")
    private Integer idCriterio;
    @Lob
    @Size(max = 16777215)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "problema", referencedColumnName = "id_problema")
    @ManyToOne
    private Problema problema;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "criterio1Id")
    private Collection<PuntuacionCriterio> puntuacionCriterio1;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "criterio2Id")
    private Collection<PuntuacionCriterio> puntuacionCriterio2;

    public Criterio() {
    }

    public Collection<PuntuacionCriterio> puntuacionCriterio1() {
        return puntuacionCriterio1;
    }

    public void setPuntuacionCriterio1(Collection<PuntuacionCriterio> puntuacionCriterio1) {
        this.puntuacionCriterio1 = puntuacionCriterio1;
    }

    public Collection<PuntuacionCriterio> puntuacionCriterio2() {
        return puntuacionCriterio2;
    }

    public void setPuntuacionCriterio2(Collection<PuntuacionCriterio> puntuacionCriterio2) {
        this.puntuacionCriterio2 = puntuacionCriterio2;
    }

    public Criterio(Integer idCriterio) {
        this.idCriterio = idCriterio;
    }

    public Integer getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(Integer idCriterio) {
        this.idCriterio = idCriterio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Problema problema() {
        return problema;
    }

    public void setProblema(Problema problema) {
        this.problema = problema;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCriterio != null ? idCriterio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Criterio)) {
            return false;
        }
        Criterio other = (Criterio) object;
        if ((this.idCriterio == null && other.idCriterio != null) || (this.idCriterio != null && !this.idCriterio.equals(other.idCriterio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.rest.Criterio[ idCriterio=" + idCriterio + " ]";
    }

}
