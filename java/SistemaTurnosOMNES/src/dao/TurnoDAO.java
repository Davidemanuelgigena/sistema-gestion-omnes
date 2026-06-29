package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Paciente;
import model.Profesional;
import model.Turno;

public class TurnoDAO {

    public boolean guardar(Turno turno) {

        String sql = """
                INSERT INTO Turno
                (fecha_hora, duracion, estado, id_paciente, id_profesional)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(turno.getFechaHora()));
            ps.setInt(2, turno.getDuracion());
            ps.setString(3, turno.getEstado());
            ps.setInt(4, turno.getPaciente().getIdPaciente());
            ps.setInt(5, turno.getProfesional().getIdProfesional());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

    public List<Turno> listar() {

        List<Turno> lista = new ArrayList<>();

        String sql = """
            SELECT
                t.id_turno,
                t.fecha_hora,
                t.duracion,
                t.estado,

                p.id_paciente,
                p.nombre AS paciente,

                pr.id_profesional,
                pr.nombre AS profesional,
                pr.especialidad

            FROM Turno t

            INNER JOIN Paciente p
                ON t.id_paciente = p.id_paciente

            INNER JOIN Profesional pr
                ON t.id_profesional = pr.id_profesional

            ORDER BY t.fecha_hora
            """;

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Paciente paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("id_paciente"));
                paciente.setNombre(rs.getString("paciente"));

                Profesional profesional = new Profesional();
                profesional.setIdProfesional(rs.getInt("id_profesional"));
                profesional.setNombre(rs.getString("profesional"));
                profesional.setEspecialidad(rs.getString("especialidad"));

                Turno turno = new Turno();

                turno.setIdTurno(rs.getInt("id_turno"));
                turno.setFechaHora(
                        rs.getTimestamp("fecha_hora").toLocalDateTime());
                turno.setDuracion(rs.getInt("duracion"));
                turno.setEstado(rs.getString("estado"));
                turno.setPaciente(paciente);
                turno.setProfesional(profesional);

                lista.add(turno);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return lista;

    }

    public boolean modificar(Turno turno) {

        String sql = """
                UPDATE Turno
                SET fecha_hora = ?,
                    duracion = ?,
                    estado = ?,
                    id_paciente = ?,
                    id_profesional = ?
                WHERE id_turno = ?
                """;

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(turno.getFechaHora()));
            ps.setInt(2, turno.getDuracion());
            ps.setString(3, turno.getEstado());
            ps.setInt(4, turno.getPaciente().getIdPaciente());
            ps.setInt(5, turno.getProfesional().getIdProfesional());
            ps.setInt(6, turno.getIdTurno());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

    public boolean eliminar(int idTurno) {

        String sql = """
                DELETE FROM Turno
                WHERE id_turno = ?
                """;

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idTurno);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }
    public Turno buscarPorId(int idTurno) {

    String sql = """
        SELECT
            t.id_turno,
            t.fecha_hora,
            t.duracion,
            t.estado,

            p.id_paciente,
            p.nombre AS paciente,

            pr.id_profesional,
            pr.nombre AS profesional

        FROM Turno t

        INNER JOIN Paciente p
            ON t.id_paciente = p.id_paciente

        INNER JOIN Profesional pr
            ON t.id_profesional = pr.id_profesional

        WHERE t.id_turno = ?
        """;

    try (Connection con = ConexionDB.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idTurno);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            Paciente paciente = new Paciente();
            paciente.setIdPaciente(rs.getInt("id_paciente"));
            paciente.setNombre(rs.getString("paciente"));

            Profesional profesional = new Profesional();
            profesional.setIdProfesional(rs.getInt("id_profesional"));
            profesional.setNombre(rs.getString("profesional"));

            Turno turno = new Turno();

            turno.setIdTurno(rs.getInt("id_turno"));
            turno.setFechaHora(
                    rs.getTimestamp("fecha_hora").toLocalDateTime());
            turno.setDuracion(rs.getInt("duracion"));
            turno.setEstado(rs.getString("estado"));
            turno.setPaciente(paciente);
            turno.setProfesional(profesional);

            return turno;

        }

    } catch (SQLException e) {

        e.printStackTrace();

    }

    return null;

    }
    public boolean cancelar(int idTurno) {

        String sql = """
                UPDATE Turno
                SET estado='Cancelado'
                WHERE id_turno=?
                """;

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idTurno);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;

        }

    }

}