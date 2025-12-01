package org.example.service.equipamento;

import org.example.Repository.EquipamentoRepository;
import org.example.Repository.EquipamentoRepositoryImpl;
import org.example.model.Equipamento;

import java.sql.SQLException;


public class EquipamentoServiceImpl implements EquipamentoService{

    private final EquipamentoRepository equipamentoRepository;

    public EquipamentoServiceImpl(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    public EquipamentoServiceImpl() {
        this.equipamentoRepository = new EquipamentoRepositoryImpl();
    }


    @Override
    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException {
        equipamento.setStatusOperacional("OPERACIONAL");
        return equipamentoRepository.save(equipamento);
    }

    @Override
    public Equipamento buscarEquipamentoPorId(Long id) throws SQLException {
        if(id == null){
            throw new IllegalArgumentException("O Id não pode ser nulo");

        }
        Equipamento equipamento = equipamentoRepository.findById(id);
       if (equipamento == null){
           throw new RuntimeException("Equipamento não encontrado!");
       }
       return equipamento;

    }
}
