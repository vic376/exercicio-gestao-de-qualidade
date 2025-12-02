package org.example.Repository;

import org.example.model.Falha;

import java.sql.SQLException;
import java.util.List;



    public abstract class FalhaRepository {
        public Falha findById(Long falhaId) throws SQLException {
            return null;
        }

        public abstract Falha save(Falha falha) throws SQLException;
        public abstract List<Falha> buscarFalhasCriticasAbertas() throws SQLException;
        public abstract void update(Falha falha) throws SQLException;  // ← ADICIONA ISSO
    }
}
