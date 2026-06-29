package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Profesional;

public class ProfesionalDAO {

    public boolean guardar(Profesional profesional) {

        String sql = """
                     INSERT INTO Profesional
                     (nombre, especialidad, matricula, porcentaje_aporte, cuit)
                     VALUES (?, ?, ?, ?, ?)
                     """;

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, profesional.getNombre());
            ps.setString(2, profesional.getEspecialidad());
            ps.setString(3, profesional.getMatricula());
            ps.setDouble(4, profesional.getPorcentajeAporte());
            ps.setString(5, profesional.getCuit());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

    public List<Profesional> listar() {

        List<Profesional> lista = new ArrayList<>();

        String sql = """
                     SELECT *
                     FROM Profesional
                     ORDER BY nombre
                     """;

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Profesional profesional = new Profesional();

                profesional.setIdProfesional(rs.getInt("id_profesional"));
                profesional.setNombre(rs.getString("nombre"));
                profesional.setEspecialidad(rs.getString("especialidad"));
                profesional.setMatricula(rs.getString("matricula"));
                profesional.setPorcentajeAporte(rs.getDouble("porcentaje_aporte"));
                profesional.setCuit(rs.getString("cuit"));

                lista.add(profesional);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return lista;

    }

    public boolean modificar(Profesional profesional) {

        String sql = """
                     UPDATE Profesional
                     SET nombre = ?,
                         especialidad = ?,
                         matricula = ?,
                         porcentaje_aporte = ?,
                         cuit = ?
                     WHERE id_profesional = ?
                     """;

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, profesional.getNombre());
            ps.setString(2, profesional.getEspecialidad());
            ps.setString(3, profesional.getMatricula());
            ps.setDouble(4, profesional.getPorcentajeAporte());
            ps.setString(5, profesional.getCuit());
            ps.setInt(6, profesional.getIdProfesional());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

    public boolean eliminar(int idProfesional) {

        String sql = "DELETE FROM Profesional WHERE id_profesional = ?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProfesional);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

}