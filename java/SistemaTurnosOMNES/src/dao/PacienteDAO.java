package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Paciente;

public class PacienteDAO {

    public boolean guardar(Paciente paciente) {

        String sql = """
                     INSERT INTO Paciente
                     (nombre, dni, obra_social)
                     VALUES (?, ?, ?)
                     """;

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, paciente.getNombre());
            ps.setInt(2, paciente.getDni());
            ps.setString(3, paciente.getObraSocial());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

    public List<Paciente> listar() {

        List<Paciente> lista = new ArrayList<>();

        String sql = """
                     SELECT *
                     FROM Paciente
                     ORDER BY nombre
                     """;

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Paciente paciente = new Paciente();

                paciente.setIdPaciente(rs.getInt("id_paciente"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setDni(rs.getInt("dni"));
                paciente.setObraSocial(rs.getString("obra_social"));

                lista.add(paciente);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return lista;

    }

    public boolean modificar(Paciente paciente) {

        String sql = """
                     UPDATE Paciente
                     SET nombre = ?, dni = ?, obra_social = ?
                     WHERE id_paciente = ?
                     """;

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, paciente.getNombre());
            ps.setInt(2, paciente.getDni());
            ps.setString(3, paciente.getObraSocial());
            ps.setInt(4, paciente.getIdPaciente());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

    public boolean eliminar(int idPaciente) {

        String sql = "DELETE FROM Paciente WHERE id_paciente = ?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPaciente);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

}