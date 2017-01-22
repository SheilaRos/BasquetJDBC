package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Equipo;
import model.Estadisticas;
import model.Jugador;

public class BasquetJDBC {
    private Connection connection;
    public void conectar() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/basket";
        String usr = "root";
        String pass = "";
        connection = DriverManager.getConnection(url, usr, pass);
    }
    public void desconectar() throws SQLException{
        if(connection != null){
            connection.close();
        }
    }
    public void insertTeam(Equipo e) throws SQLException{
        String insert = "insert into team values (?, ?, ?);";
        PreparedStatement ps = connection.prepareStatement(insert);
        ps.setString(1, e.getNombre());
        ps.setString(2, e.getLocalidad());
        ps.setDate(3, java.sql.Date.valueOf(e.getFecha()));
        ps.executeUpdate();
        ps.close();
    }
    public void insertPlayer(Jugador j) throws SQLException{
        String insert = "insert into player values (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = connection.prepareStatement(insert);
        ps.setString(1, j.getNombre());
        ps.setDate(2, java.sql.Date.valueOf(j.getBirthday()));
        ps.setInt(3, j.getCanastas());
        ps.setInt(4, j.getAsistencias());
        ps.setInt(5, j.getRebotes());
        ps.setString(6, j.getPosicion());
        ps.setString(7, j.getEquipo().getNombre());
        ps.executeUpdate();
        ps.close();
    }
    public void deletePlayer(String nombre) throws SQLException{
        String delete = "delete from player where name = '" + nombre+"';";
        Statement st = connection.createStatement();
        st.executeUpdate(delete);
        st.close();
    }
    public void updatePlayer(Jugador j) throws SQLException{
        String update = "update player set name=?, birth=?, nbaskets=?, nassists=?, nrebounds=?, position=?, team=? where name = '" + j.getNombre()+"';";
        PreparedStatement ps = connection.prepareStatement(update);
        ps.setString(1, j.getNombre());
        ps.setDate(2, java.sql.Date.valueOf(j.getBirthday()));
        ps.setInt(3, j.getCanastas());
        ps.setInt(4, j.getAsistencias());
        ps.setInt(5, j.getRebotes());
        ps.setString(6, j.getPosicion());
        ps.setString(7, j.getEquipo().getNombre());
        ps.executeUpdate();
        ps.close();
    }
    public void updateTeam(Equipo e) throws SQLException{
        String update = "update team set name=?, city=?, creation=? where name = '" + e.getNombre()+"';";
        PreparedStatement ps = connection.prepareStatement(update);
        ps.setString(1, e.getNombre());
        ps.setString(2, e.getLocalidad());
        ps.setDate(3, java.sql.Date.valueOf(e.getFecha()));
        ps.executeUpdate();
        ps.close();
    }
    public void updatePlayerTeam(String nombre, Equipo e) throws SQLException{
        String update = "update player set  team=? where name = '" + nombre+"';";
        PreparedStatement ps = connection.prepareStatement(update);
        ps.setString(1, e.getNombre());
        ps.executeUpdate();
        ps.close();
    }
    public Jugador returnPlayerName(String nombre) throws SQLException{
        Jugador j = new Jugador();
        String query = "select * from player where name ='"+nombre+"';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            j.setNombre(rs.getString("name"));
            j.setBirthday(rs.getDate("birth").toLocalDate());
            j.setCanastas(rs.getInt("nbaskets"));
            j.setAsistencias(rs.getInt("nassists"));
            j.setRebotes(rs.getInt("nrebounds"));
            j.setPosicion(rs.getString("position"));
            Equipo e = new Equipo(rs.getString("team"));
            j.setEquipo(e);
        }
        rs.close();
        st.close();
        return j;
    }  
    public List<Jugador> returnPlayersIncompleteName(String name) throws SQLException{
        List<Jugador> jugadores = new ArrayList<>();
        String query = "select * from player where name LIKE '%"+name+"%';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            Jugador j = new Jugador();
            Equipo e = new Equipo();
            j.setNombre(rs.getString("name"));
            j.setBirthday(rs.getDate("birth").toLocalDate());
            j.setCanastas(rs.getInt("nbaskets"));
            j.setAsistencias(rs.getInt("nassists"));
            j.setRebotes(rs.getInt("nrebounds"));
            j.setPosicion(rs.getString("position"));
            e.setNombre(rs.getString("team"));
            j.setEquipo(e);
            jugadores.add(j);
        }
        return jugadores;
    }
    public List<Jugador> returnPlayersBaskets(int baskets) throws SQLException{
        List<Jugador> jugadores = new ArrayList<>();
        String query = "select * from player where nbaskets>="+baskets+";";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            Jugador j = new Jugador();
            Equipo e = new Equipo();
            j.setNombre(rs.getString("name"));
            j.setBirthday(rs.getDate("birth").toLocalDate());
            j.setCanastas(rs.getInt("nbaskets"));
            j.setAsistencias(rs.getInt("nassists"));
            j.setRebotes(rs.getInt("nrebounds"));
            j.setPosicion(rs.getString("position"));
            e.setNombre(rs.getString("team"));
            j.setEquipo(e);
            jugadores.add(j);
        }
        return jugadores;
    }
    public List<Jugador> returnPlayersAssists(int assists1, int assists2) throws SQLException{
        List<Jugador> jugadores = new ArrayList<>();
        String query = "select * from player where nassists>="+assists1+" && nassists<="+assists2+";";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            Jugador j = new Jugador();
            Equipo e = new Equipo();
            j.setNombre(rs.getString("name"));
            j.setBirthday(rs.getDate("birth").toLocalDate());
            j.setCanastas(rs.getInt("nbaskets"));
            j.setAsistencias(rs.getInt("nassists"));
            j.setRebotes(rs.getInt("nrebounds"));
            j.setPosicion(rs.getString("position"));
            e.setNombre(rs.getString("team"));
            j.setEquipo(e);
            jugadores.add(j);
        }
        return jugadores;
    }
    public List<Jugador> returnPlayersPosition(String position) throws SQLException{
        List<Jugador> jugadores = new ArrayList<>();
        String query = "select * from player where position='"+position+"';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            Jugador j = new Jugador();
            Equipo e = new Equipo();
            j.setNombre(rs.getString("name"));
            j.setBirthday(rs.getDate("birth").toLocalDate());
            j.setCanastas(rs.getInt("nbaskets"));
            j.setAsistencias(rs.getInt("nassists"));
            j.setRebotes(rs.getInt("nrebounds"));
            j.setPosicion(rs.getString("position"));
            e.setNombre(rs.getString("team"));
            j.setEquipo(e);
            jugadores.add(j);
        }
        return jugadores;
    }
    public List<Jugador> returnPlayersBirthBefore(LocalDate fecha) throws SQLException{
        List<Jugador> jugadores = new ArrayList<>();
        String query = "select * from player where birth<'"+java.sql.Date.valueOf(fecha)+"';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            Jugador j = new Jugador();
            Equipo e = new Equipo();
            j.setNombre(rs.getString("name"));
            j.setBirthday(rs.getDate("birth").toLocalDate());
            j.setCanastas(rs.getInt("nbaskets"));
            j.setAsistencias(rs.getInt("nassists"));
            j.setRebotes(rs.getInt("nrebounds"));
            j.setPosicion(rs.getString("position"));
            e.setNombre(rs.getString("team"));
            j.setEquipo(e);
            jugadores.add(j);
        }
        return jugadores;
    }
    public List<Estadisticas> returnPlayersGroupByPosition() throws SQLException{
        List<Estadisticas> resultado = new ArrayList<>();
        String query = "select position, max(nbaskets) as 'max1', min(nbaskets) as 'min1', avg(nbaskets) as 'avg1', max(nassists) as 'max2', min(nassists) as 'min2', avg(nassists) as 'avg2', max(nrebounds) as 'max3', min(nrebounds) as 'min3', avg(nrebounds) as 'avg3' from player group by position;";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
           Estadisticas x = new Estadisticas();
           x.setPosition(rs.getString("position"));
           x.setMaxCanastas(rs.getInt("max1"));
           x.setMinCanastas(rs.getInt("min1"));
           x.setAvgCanastas(rs.getDouble("avg1"));
           x.setMaxAsistencias(rs.getInt("max2"));
           x.setMinAsistencias(rs.getInt("min2"));
           x.setAvgAsistencias(rs.getDouble("avg2"));
           x.setMaxRebotes(rs.getInt("max3"));
           x.setMinRebotes(rs.getInt("min3"));
           x.setAvgRebotes(rs.getDouble("avg3"));
           resultado.add(x);
           
        }
        return resultado;
    }
    public List<String> returnPlayersRanking() throws SQLException{
        List<String> ranking = new ArrayList<>();
        String query = "select name, nbaskets  from player order by nbaskets desc;";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        int num = 1;
        while(rs.next()){
            ranking.add(num+" "+rs.getString("name")+" "+rs.getInt("nbaskets"));
            num++;
        }
        return ranking;
    } 
    public int returnPlayersRankingPosition(String nombre) throws SQLException{
        int posicion = 0;
        List<String> ranking = returnPlayersRanking();
        Map<String, String> jugadores = new HashMap<>();
        for(int i=0; i<ranking.size(); i++){
            String[] j = ranking.get(i).split(" ");
            jugadores.put(j[1], j[0]);
        }
        if(jugadores.containsKey(nombre)){
            posicion = Integer.parseInt(jugadores.get(nombre));
        }
        return posicion;
    }
    public List<Equipo> returnTeamCity(String ciudad) throws SQLException{
        List<Equipo> equipos = new ArrayList<>();
        String query = "select * from team where city='"+ciudad+"';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            Equipo e = new Equipo();
            e.setNombre(rs.getString("name"));
            e.setLocalidad(rs.getString("city"));
            e.setFecha(rs.getDate("creation").toLocalDate());
            equipos.add(e);
        }
        return equipos;
    }
    public List<Jugador> returnPlayersTeam(String equipo) throws SQLException{
        List<Jugador> jugadores = new ArrayList<>();
        String query = "select * from player where team='"+equipo+"';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
           Jugador j = new Jugador();
           Equipo e = new Equipo();
           j.setNombre(rs.getString("name"));
           j.setBirthday(rs.getDate("birth").toLocalDate());
           j.setCanastas(rs.getInt("nbaskets"));
           j.setAsistencias(rs.getInt("nassists"));
           j.setRebotes(rs.getInt("nrebounds"));
           j.setPosicion(rs.getString("position"));
           e.setNombre(rs.getString("team"));
           j.setEquipo(e);
           jugadores.add(j);
        }
        return jugadores;
    }
    public List<Jugador> returnPlayersTeamPosition(String equipo, String posicion) throws SQLException{
        List<Jugador> jugadores = new ArrayList<>();
        String query = "select * from player where team='"+equipo+"' and position='"+posicion+"';";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
           Jugador j = new Jugador();
           Equipo e = new Equipo();
           j.setNombre(rs.getString("name"));
           j.setBirthday(rs.getDate("birth").toLocalDate());
           j.setCanastas(rs.getInt("nbaskets"));
           j.setAsistencias(rs.getInt("nassists"));
           j.setRebotes(rs.getInt("nrebounds"));
           j.setPosicion(rs.getString("position"));
           e.setNombre(rs.getString("team"));
           j.setEquipo(e);
           jugadores.add(j);
        }
        return jugadores;
    }
    public Jugador returnPlayersTeamMaxBaskets(String equipo) throws SQLException{
        Jugador j = new Jugador();
        String query = "select * from player where team='"+equipo+"' and nbaskets=(select max(nbaskets) from player where team='"+equipo+"');";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
           Equipo e = new Equipo();
           j.setNombre(rs.getString("name"));
           j.setBirthday(rs.getDate("birth").toLocalDate());
           j.setCanastas(rs.getInt("nbaskets"));
           j.setAsistencias(rs.getInt("nassists"));
           j.setRebotes(rs.getInt("nrebounds"));
           j.setPosicion(rs.getString("position"));
           e.setNombre(rs.getString("team"));
           j.setEquipo(e);
        }
        return j;
    }
    public void eliminarJugadores() throws SQLException{
        String delete = "delete from player;";
        Statement st = connection.createStatement();
        st.executeUpdate(delete);
        delete = "delete from team;";
        st = connection.createStatement();
        st.executeUpdate(delete);
        st.close();
    }
}
