package org.example.Repository;

import org.example.model.Equipamento;
import java.sql.SQLException;

public abstract class EquipamentoRepository {

    public abstract Equipamento save(Equipamento equipamento) throws SQLException;

    public abstract Equipamento findById(Long id) throws SQLException;

    public abstract void update(Equipamento equipamento) throws SQLException;
}