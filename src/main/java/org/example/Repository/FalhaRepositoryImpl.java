package org.example.Repository;

import org.example.database.Conexao;
import org.example.model.Falha;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FalhaRepositoryImpl extends FalhaRepository {

    @Override
    public Falha save(Falha falha) throws SQLException {
        String sql = """
            INSERT INTO Falha (equipamentoId, dataHoraOcorrencia, 
                               descricao, criticidade, status, tempoParadaHoras)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, falha.getEquipamentoId());
            stmt.setTimestamp(2, Timestamp.valueOf(falha.getDataHoraOcorrencia()));
            stmt.setString(3, falha.getDescricao());
            stmt.setString(4, falha.getCriticidade());
            stmt.setString(5, falha.getStatus());
            stmt.setBigDecimal(6, falha.getTempoParadaHoras());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    falha.setId(rs.getLong(1));
                }
            }
        }

        return falha;
    }

    @Override
    public List<Falha> buscarFalhasCriticasAbertas() throws SQLException {
        String sql = """
            SELECT * FROM Falha 
            WHERE status = 'ABERTA' 
            AND criticidade = 'CRITICA'
            """;

        List<Falha> falhas = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Falha falha = new Falha();
                falha.setId(rs.getLong("id"));
                falha.setEquipamentoId(rs.getLong("equipamentoId"));  // ← ADICIONEI
                falha.setDataHoraOcorrencia(rs.getTimestamp("dataHoraOcorrencia").toLocalDateTime());
                falha.setDescricao(rs.getString("descricao"));
                falha.setCriticidade(rs.getString("criticidade"));
                falha.setStatus(rs.getString("status"));
                falha.setTempoParadaHoras(rs.getBigDecimal("tempoParadaHoras"));

                falhas.add(falha);
            }
        }

        return falhas;
    }
}