package med.voll.api.Domain.Consulta.Validacoes;

import med.voll.api.Domain.Consulta.DadosAgendamentoConsulta;
import med.voll.api.Domain.Medico.MedicoRepository;
import med.voll.api.Domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ValidadorMedicoAtivo extends ValidadorAgendamentosDeConsulta {

@Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        if (dados.idMedico() == null){
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if ((medicoEstaAtivo != null && Boolean.FALSE.equals(medicoEstaAtivo))){
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluído");
        }
    }
}
