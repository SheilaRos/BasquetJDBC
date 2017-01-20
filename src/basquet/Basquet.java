package basquet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import model.Equipo;
import model.Estadisticas;
import model.Jugador;
import persistence.BasquetJDBC;

public class Basquet {
    public static void main(String[] args) throws SQLException {
        BasquetJDBC gestor = new BasquetJDBC();
        try{
        gestor.conectar();
        System.out.println("Estableciendo conexión");
        gestor.eliminarJugadores();
        System.out.println("");
        Equipo e = new Equipo("Fagrecios", "Rivendel", LocalDate.of(2015, 5 , 10));
        Equipo e2 = new Equipo("Fulgencios", "Minas Moria", LocalDate.of(2015, 1, 14));
        System.out.println("Insertando equipos...");
        gestor.insertTeam(e);
        gestor.insertTeam(e2);
        System.out.println("Equipos dados de alta.");
        System.out.println("");
        Jugador j = new Jugador("Yuna114", LocalDate.of(1993,5, 14), 150, 45, 25, "Base", e2);
        Jugador j2 = new Jugador("Bimu", LocalDate.of(1994,10, 25), 75, 20, 10, "Alero", e);
        Jugador j3 = new Jugador("Lolrol1", LocalDate.of(1996,8,19), 80, 105, 60, "Base", e2);
        Jugador j4 = new Jugador("Kalibord", LocalDate.of(1994, 7, 19), 220, 50, 10, "Alero", e2);
        Jugador j5 = new Jugador("Shertock", LocalDate.of(1994, 9, 21), 30, 40, 10, "Pivot", e);
        Jugador j6 = new Jugador("Adobis", LocalDate.of(1997, 3, 12), 135, 100, 25, "Alero", e2);
        Jugador j7 = new Jugador("Mulf", LocalDate.of(1997, 4, 12), 15, 10, 5, "Alero", e2);
        System.out.println("Insertar jugadores...");
        gestor.insertPlayer(j);
        gestor.insertPlayer(j2);
        gestor.insertPlayer(j3);
        gestor.insertPlayer(j4);
        gestor.insertPlayer(j5);
        gestor.insertPlayer(j6);
        gestor.insertPlayer(j7);
        System.out.println("Jugadores dados de alta.");
        System.out.println("");
        System.out.println("Modificar jugador Bimu:");
        j2 = new Jugador("Bimu", LocalDate.of(1994,10, 25), 175, 120, 10, "Alero", e);
        gestor.updatePlayer(j2);
        System.out.println("Jugador modificado");
        System.out.println("");
        System.out.println("Modificar equipo Rivendel:");
        e = new Equipo("Fagrecios", "Rivendel", LocalDate.of(2015,5,21));
        gestor.updateTeam(e);
        System.out.println("Equipo modificado");
        System.out.println("");
        System.out.println("Modificar jugador Yuna de equipo Fulgencios a equipo Rivendel:");
        gestor.updatePlayerTeam("Yuna", e);
        System.out.println("Jugador modificado de equipo");
        System.out.println("");
        System.out.println("Borrar jugador 'Mulf'");
        gestor.deletePlayer("Mulf");
        System.out.println("Jugador eliminado");
        System.out.println("");
        System.out.println("Devolver jugador Lolrol1:");
        j = gestor.returnPlayerName("Lolrol1");
        System.out.println(j);
        System.out.println("");
        System.out.println("Devolver los jugadores que tengan una 'u' :");
        List<Jugador>  jugadores = gestor.returnPlayersIncompleteName("u");
        for(Jugador x: jugadores){
            System.out.println(x);
        }
        System.out.println("");
        System.out.println("Devolver los jugadores con más de 80 canastas:");
        jugadores = gestor.returnPlayersBaskets(80);
        for(Jugador x: jugadores){
            System.out.println(x);
        }
        System.out.println("");
        System.out.println("Devolver los jugadores con asistencias entre 20 y 50:");
        jugadores = gestor.returnPlayersAssists(20, 50);
        for(Jugador x: jugadores){
            System.out.println(x);
        }
        System.out.println("");
        System.out.println("Devolver los jugadores que tienen la posición de alero:");
        jugadores = gestor.returnPlayersPosition("Alero");
        for(Jugador x: jugadores){
            System.out.println(x);
        }
        System.out.println("");
        System.out.println("Devolver los jugadores que hayan nacido antes del 15/01/1996:");
        jugadores = gestor.returnPlayersBirthBefore(LocalDate.of(1996,1,15));
        for(Jugador x: jugadores){
            System.out.println(x);
        }
        System.out.println("");
        System.out.println("Devolver max, min y avg de canastas, asistencias y rebotes agrupados por posición:");
        List<Estadisticas> estadistica = gestor.returnPlayersGroupByPosition();
        for(Estadisticas x: estadistica){
            System.out.println(x);
        }
        System.out.println("");
        System.out.println("Devolver ranking por número de canastas:");
        List<String> strings = gestor.returnPlayersRanking();
        for(String x: strings){
            System.out.println(x);
        }
        System.out.println("");
        System.out.println("Devolver la posición en el ranking de Adobis");
        int posicion = gestor.returnPlayersRankingPosition("Adobis");
        System.out.println("Adobis posicion:  "+posicion);
        System.out.println("");
        System.out.println("Listados de los equipos de Rivendel:");
        List<Equipo> equipos = gestor.returnTeamCity("Rivendel");
        for(Equipo x: equipos){
            System.out.println(x);
        }
        System.out.println("");
        System.out.println("Listado de jugadores del equipo Fulgencios:");
        jugadores = gestor.returnPlayersTeam("Fulgencios");
        for(Jugador x: jugadores){
            System.out.println(x);
        }
        System.out.println("");
        System.out.println("Listado de jugadores del equipo Fagrecios que sean Pivot:");
        jugadores = gestor.returnPlayersTeamPosition("Fagrecios", "Pivot");
        for(Jugador x: jugadores){
            System.out.println(x);
        }
        System.out.println("");
        System.out.println("Jugador del equipo Fulgencios con más canástas:");
        j = gestor.returnPlayersTeamMaxBaskets("Fulgencios");
        System.out.println(j);
        System.out.println("");
        }catch(SQLException ex){
            System.out.println("Error con la BBDD: "+ex.getMessage());
        }finally{
            try{
                gestor.desconectar();
                System.out.println("Conexión cerrada.");
            }catch (SQLException ex){
                System.out.println("Error al desconectar"+ex.getMessage());
            }
        }
        
        System.out.println("");
        
    }
    
}
