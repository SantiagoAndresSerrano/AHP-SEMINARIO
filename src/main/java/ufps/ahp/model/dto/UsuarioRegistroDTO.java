package ufps.ahp.model.dto;

public class UsuarioRegistroDTO {

    private String email;
    private String celular;
    private String nombre;
    private String empresa;
    private String profesion;
    private String password;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getProfesion() {
            return profesion;
        }

        public void setProfesion(String profesion) {
            this.profesion = profesion;
        }

        public String getEmpresa() {
            return empresa;
        }

        public void setEmpresa(String empresa) {
            this.empresa = empresa;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCelular() {
            return celular;
        }

        public void setCelular(String celular) {
            this.celular = celular;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
}
