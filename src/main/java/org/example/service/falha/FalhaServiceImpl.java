package org.example.service.falha;

import org.example.Repository.EquipamentoRepository;
import org.example.Repository.FalhaRepository;
import org.example.model.Equipamento;
import org.example.model.Falha;

import java.sql.SQLException;
import java.util.List;

public class FalhaServiceImpl implements FalhaService{

    private final FalhaRepository falhaRepository;
    private final EquipamentoRepository equipamentoRepository;

    public FalhaServiceImpl(FalhaRepository falhaRepository, EquipamentoRepository equipamentoRepository) {
        this.falhaRepository = falhaRepository;
        this.equipamentoRepository = equipamentoRepository;
    }


    @Override
    public Falha registrarNovaFalha(Falha falha) throws SQLException {
        Equipamento equipamento = equipamentoRepository.findById(falha.getEquipamentoId());
        if(equipamento == null){
            throw new IllegalArgumentException("Equipamento não encontrado!");
        }
        falha.setStatus("ABERTA");

        if ("CRITICA".equals(falha.getCriticidade())){
            equipamento.setStatusOperacional("EM_MANUTENCAO");
            equipamentoRepository.updateStatus(equipamento);

        }

        return falhaRepository.registrarNovaFalha(falha);

    }

    @Override
    public List<Falha> buscarFalhasCriticasAbertas() throws SQLException {
        List<Falha> falhas = falhaRepository.buscarFalhasCriticasAbertas();
        return falhaRepository.buscarFalhasCriticasAbertas();
    }
}
