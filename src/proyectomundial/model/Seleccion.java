/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomundial.model;

/**
 *
 * @author miguelropero
 */
public class Seleccion {

    String nombre;
    String continente;
    String dt;
    String nacionalidad;

    String grupo;
    String local;
    String visitante;
    String continente_l;
    String continente_v;
    String goles_l;
    String goles_v;

    String pagina;
    String contador;

    String equipo;
    int goles_totales;

    public Seleccion() {
    }

    public Seleccion(String nombre, String continente, String dt, String nacionalidad) {
        this.nombre = nombre;
        this.continente = continente;
        this.dt = dt;
        this.nacionalidad = nacionalidad;
    }

    public Seleccion(String dt, String nacionalidad, String xd) {
        this.nacionalidad = nacionalidad;
        this.dt = dt;
    }

    public Seleccion(String pagina, String contador) {
        this.pagina = pagina;
        this.contador = contador;

    }

    public Seleccion(String equipo, int goles_totales) {
        this.equipo = equipo;
        this.goles_totales = goles_totales;
    }

    public Seleccion(String grupo, String local, String visitante, String continente_l, String continente_v, String goles_l, String goles_v) {
        this.grupo = grupo;
        this.local = local;
        this.visitante = visitante;
        this.continente_l = continente_l;
        this.continente_v = continente_v;
        this.goles_l = goles_l;
        this.goles_v = goles_v;
    }

    public void setGoles(int goles_totales) {
        this.goles_totales = goles_totales;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    

    public int getGoles() {
        return goles_totales;
    }

    public String getPagina() {
        return pagina;
    }

    public String getContador() {
        return contador;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public void setContador(String contador) {
        this.contador = contador;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public void setContinente_l(String continente_l) {
        this.continente_l = continente_l;
    }

    public void setContinente_v(String continente_v) {
        this.continente_v = continente_v;
    }

    public void setGoles_l(String goles_l) {
        this.goles_l = goles_l;
    }

    public void setGoles_v(String goles_v) {
        this.goles_v = goles_v;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getLocal() {
        return local;
    }

    public String getVisitante() {
        return visitante;
    }

    public String getContinente_l() {
        return continente_l;
    }

    public String getContinente_v() {
        return continente_v;
    }

    public String getGoles_l() {
        return goles_l;
    }

    public String getGoles_v() {
        return goles_v;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
