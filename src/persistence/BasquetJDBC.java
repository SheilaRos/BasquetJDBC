package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Equipo;
import model.Jugador;

public class BasquetJDBC {
    private Connection connection;
    public void conectar() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/baskets";
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
        String delete = "delete from player where nombre = '" + nombre+"';";
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
        String update = "update player set name=?, city=?, creation=? where name = '" + e.getNombre()+"';";
        PreparedStatement ps = connection.prepareStatement(update);
        ps.setString(1, e.getNombre());
        ps.setString(2, e.getLocalidad());
        ps.setDate(3, java.sql.Date.valueOf(e.getFecha()));
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
            j.setPosicion(rs.getNString("position"));
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
            j.setPosicion(rs.getNString("position"));
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
            j.setPosicion(rs.getNString("position"));
            e.setNombre(rs.getString("team"));
            j.setEquipo(e);
            jugadores.add(j);
        }
        return jugadores;
    }
    public List<Jugador> returnPlayersAssists(int assists1, int assists2) throws SQLException{
        List<Jugador> jugadores = new ArrayList<>();
        String query = "select * from player where nassists>"+assists1+" && nassists<"+assists2+";";
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
            j.setPosicion(rs.getNString("position"));
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
            j.setPosicion(rs.getNString("position"));
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
            j.setPosicion(rs.getNString("position"));
            e.setNombre(rs.getString("team"));
            j.setEquipo(e);
            jugadores.add(j);
        }
        return jugadores;
    }
    //Preguntar a Mar
    public List<Jugador> returnPlayersGroupByPosition() throws SQLException{
        List<Jugador> jugadores = new ArrayList<>();
        String query = "select * from player groupBy position;";
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
            j.setPosicion(rs.getNString("position"));
            e.setNombre(rs.getString("team"));
            j.setEquipo(e);
            jugadores.add(j);
        }
        return jugadores;
    }
    public void returnPlayersRanking() throws SQLException{
        Map<int, List<Jugador>> ranking = new 
        
        String query = "select name, nbaskets  from player where orderBy nbaskets;";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        int num = 1;
        while(rs.next()){
            System.out.println(num+" "+rs.getString("name")+" "+rs.getInt("nbaskets"));
            num++;
        }
    }
    
}
