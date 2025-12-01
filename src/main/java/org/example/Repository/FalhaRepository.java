package org.example.Repository;

import org.example.model.Falha;

import java.sql.SQLException;
import java.util.List;

public abstract class FalhaRepository {


    public abstract List<Falha> buscarFalhasCriticasAbertas() throws SQLException;


    public abstract Falha save(Falha falha) throws SQLException;
}
