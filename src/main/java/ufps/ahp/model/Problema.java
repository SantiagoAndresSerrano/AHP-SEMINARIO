package ufps.ahp.model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ufps.ahp.security.model.Usuario;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author santi
 */
@Entity
@Table(name = "problema")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Problema.findAll", query = "SELECT p FROM Problema p"),
        @NamedQuery(name = "Problema.findByIdProblema", query = "SELECT p FROM Problema p WHERE p.idProblema = :idProblema"),
        @NamedQuery(name = "Problema.findByUsuario", query = "SELECT p FROM Problema p WHERE p.usuario = :usuario")})
public class Problema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_problema")
    private String idProblema;
    @JoinColumn(name = "usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @Lob
    @Size(max = 16777215)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "problema")
    private Collection<Criterio> criterioCollection;
    @OneToMany(mappedBy = "problema")
    private Collection<Alternativa> alternativaCollection;
    public Problema() {
    }

    public Problema(String idProblema) {
        this.idProblema = idProblema;
    }

    public String getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(String idProblema) {
        this.idProblema = idProblema;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Criterio> criterio() {
        return criterioCollection;
    }

    public void setCriterioCollection(Collection<Criterio> criterioCollection) {
        this.criterioCollection = criterioCollection;
    }

    public Collection<Alternativa> alternativa() {
        return alternativaCollection;
    }

    public void setAlternativaCollection(Collection<Alternativa> alternativaCollection) {
        this.alternativaCollection = alternativaCollection;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProblema != null ? idProblema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Problema)) {
            return false;
        }
        Problema other = (Problema) object;
        if ((this.idProblema == null && other.idProblema != null) || (this.idProblema != null && !this.idProblema.equals(other.idProblema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.rest.Problema[ idProblema=" + idProblema + " ]";
    }

}
