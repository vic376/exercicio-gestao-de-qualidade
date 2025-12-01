package org.example.Repository;

import org.example.database.Conexao;
import org.example.model.Equipamento;
import java.sql.*;

public class EquipamentoRepositoryImpl extends EquipamentoRepository {

    @Override
    public Equipamento save (Equipamento equipamento) throws SQLException{

        String sql = """
                INSERT INTO Equipamento (nome, numeroDeSerie, areaSetor, statusOperacional)
                VALUES (?,?,?,?)
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

                stmt.setString(1, equipamento.getNome());
                stmt.setString(2, equipamento.getNumeroDeSerie());
                stmt.setString(3, equipamento.getAreaSetor());
                stmt.setString(4, equipamento.getStatusOperacional());
                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()){
                    equipamento.setId(rs.getLong(1));
                }
    }

        return equipamento;
    }

    @Override
    public Equipamento findById(Long id) throws SQLException {
        String sql = "SELECT * FROM Equipamento WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Equipamento equipamento = new Equipamento();
                    equipamento.setId(rs.getLong("id"));
                    equipamento.setNome(rs.getString("nome"));
                    equipamento.setNumeroDeSerie(rs.getString("numeroDeSerie"));
                    equipamento.setAreaSetor(rs.getString("areaSetor"));
                    equipamento.setStatusOperacional(rs.getString("statusOperacional"));

                    return equipamento;
                }

                return null;
            }
        }
    }


    @Override
    public void update(Equipamento equipamento) throws SQLException {
        String sql = """
            UPDATE Equipamento
            SET nome = ?,
                numeroDeSerie = ?,
                areaSetor = ?,
                statusOperacional = ?
            WHERE id = ?
            """;


        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getNumeroDeSerie());
            stmt.setString(3, equipamento.getAreaSetor());
            stmt.setString(4, equipamento.getStatusOperacional());
            stmt.setLong(5, equipamento.getId());
            stmt.executeUpdate();
        }
    }


}
