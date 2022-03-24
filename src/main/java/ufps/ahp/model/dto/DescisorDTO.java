package ufps.ahp.model.dto;

public class DescisorDTO {

    String email;
    String nombre;

    public DescisorDTO(String email, String nombre) {
        this.email = email;
        this.nombre = nombre;
    }
    public DescisorDTO(){}
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
