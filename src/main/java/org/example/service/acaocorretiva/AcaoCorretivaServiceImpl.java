package org.example.service.acaocorretiva;
import org.example.Repository.*;
import org.example.model.AcaoCorretiva;
import org.example.model.Equipamento;
import org.example.model.Falha;
import java.time.temporal.ChronoUnit; //aparentemente importante pra cálculo de tempo

import java.sql.SQLException;

public class AcaoCorretivaServiceImpl implements AcaoCorretivaService{
    private final AcaoCorretivaRepository acaoCorretivaRepository;
    private final FalhaRepository falhaRepository;
    private final EquipamentoRepository equipamentoRepository;

    public AcaoCorretivaServiceImpl(AcaoCorretivaRepository acaoCorretivaRepository, FalhaRepository falhaRepository, EquipamentoRepository equipamentoRepository) {
        this.falhaRepository = falhaRepository;
        this.acaoCorretivaRepository = acaoCorretivaRepository;
        this.equipamentoRepository = equipamentoRepository;
    }

    public AcaoCorretivaServiceImpl() {
        this.acaoCorretivaRepository = new AcaoCorretivaRepositoryImpl();
        this.falhaRepository = new FalhaRepositoryImpl();
        this.equipamentoRepository = new EquipamentoRepositoryImpl();
    }

    @Override
    public AcaoCorretiva registrarConclusaoDeAcao(AcaoCorretiva acao) throws SQLException {
        Falha falha = falhaRepository.findById(acao.getFalhaId());
        if(falha == null){
            throw new IllegalArgumentException("Falha não encontrada!");
        }

        AcaoCorretiva acaoSalva = acaoCorretivaRepository.save(acao);

        falha.setStatus("RESOLVIDA");
        falhaRepository.update(falha);

        //Pra cima tá certo
        if ("CRITICA".equals(falha.getCriticidade())) {
            Long equipamentoId = falha.getEquipamentoId();
            Equipamento equipamento = equipamentoRepository.findById(equipamentoId);


            if (equipamento != null) {
                equipamento.setStatusOperacional("OPERACIONAL");
                equipamentoRepository.update(equipamento);
            }
        }


        return acaoSalva;
    }
}


