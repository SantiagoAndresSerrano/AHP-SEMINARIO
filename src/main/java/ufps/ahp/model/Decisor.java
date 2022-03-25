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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author santi
 */
@Entity
@Table(name = "decisor")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Decisor.findAll", query = "SELECT d FROM Decisor d"),
        @NamedQuery(name = "Decisor.findByIdDecisor", query = "SELECT d FROM Decisor d WHERE d.idDecisor = :idDecisor"),
        @NamedQuery(name = "Decisor.findByNombre", query = "SELECT d FROM Decisor d WHERE d.nombre = :nombre"),
        @NamedQuery(name = "Decisor.findByEmail", query = "SELECT d FROM Decisor d WHERE d.email = :email")})
public class Decisor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_decisor")
    private Integer idDecisor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nombre")
    private String nombre;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @JoinColumn(name = "usuario", referencedColumnName = "id_usuario")
    @OneToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "decisor")
    private Collection<Puntuacion> puntuacionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "decisor")
    private Collection<PuntuacionAlternativa> puntuacionAlternativaCollection;

    public Decisor() {
    }

    public Decisor(Integer idDecisor) {
        this.idDecisor = idDecisor;
    }

    public Decisor(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public Integer getIdDecisor() {
        return idDecisor;
    }

    public void setIdDecisor(Integer idDecisor) {
        this.idDecisor = idDecisor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public Collection<Puntuacion> getPuntuacionCollection() {
        return puntuacionCollection;
    }

    public void setPuntuacionCollection(Collection<Puntuacion> puntuacionCollection) {
        this.puntuacionCollection = puntuacionCollection;
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
        hash += (idDecisor != null ? idDecisor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Decisor)) {
            return false;
        }
        Decisor other = (Decisor) object;
        if ((this.idDecisor == null && other.idDecisor != null) || (this.idDecisor != null && !this.idDecisor.equals(other.idDecisor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.rest.Decisor[ idDecisor=" + idDecisor + " ]";
    }

}
