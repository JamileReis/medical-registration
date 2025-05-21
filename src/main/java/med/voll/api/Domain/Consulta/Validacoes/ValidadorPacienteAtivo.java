package med.voll.api.Domain.Consulta.Validacoes;

import med.voll.api.Domain.Consulta.DadosAgendamentoConsulta;
import med.voll.api.Domain.Medico.MedicoRepository;
import med.voll.api.Domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ValidadorPacienteAtivo extends ValidadorAgendamentosDeConsulta {
@Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var pacienteEstaAtivo = repository.findAtivoById(dados.idMedico());
        if ((pacienteEstaAtivo != null && Boolean.FALSE.equals(pacienteEstaAtivo))){
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
        }
    }
}
