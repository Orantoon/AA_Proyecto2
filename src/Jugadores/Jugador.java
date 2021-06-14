package Jugadores;

import java.time.LocalTime;

public class Jugador {
    public String nickname;
    public String inscripcion;
    public float creditos;
    public int totalPartidas;
    public LocalTime timezone;
    public Ticket ticket;

    public Jugador(){
        nickname = " ";
        inscripcion = " ";
        creditos = (float) 1000.00;
        totalPartidas = 0;
        timezone = null;
        ticket = null;
    }
}
