package model;

import java.time.LocalDate;

public class Jugador {
     private String nombre;
    private LocalDate birthday;
    private int canastas;
    private int asistencias;
    private String rebotes;
    private String posicion;
    private Equipo equipo;
    public Jugador(String nombre, LocalDate birthday, int canastas, int asistencias, String rebotes, String posicion, Equipo equipo) {
        this.nombre = nombre;
        this.birthday = birthday;
        this.canastas = canastas;
        this.asistencias = asistencias;
        this.rebotes = rebotes;
        this.posicion = posicion;
        this.equipo = equipo;
    }
    public Equipo getEquipo() {return equipo;}
    public void setEquipo(Equipo equipo) {this.equipo = equipo;}
    public String getPosicion() {return posicion;}
    public void setPosicion(String posicion) {this.posicion = posicion;}
    public String getRebotes() {return rebotes;}
    public void setRebotes(String rebotes) {this.rebotes = rebotes;}
    public int getAsistencias() {return asistencias;}
    public void setAsistencias(int asistencias) {this.asistencias = asistencias;}
    public int getCanastas() {return canastas;}
    public void setCanastas(int canastas) {this.canastas = canastas;}
    public LocalDate getBirthday() {return birthday;}
    public void setBirthday(LocalDate birthday) {this.birthday = birthday;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
}
