package med.voll.api.Domain.Consulta.Validacoes;

import med.voll.api.Domain.Consulta.ConsultaRepository;
import med.voll.api.Domain.Consulta.DadosAgendamentoConsulta;
import med.voll.api.Domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ValidadorMedicoComOutraConsulta extends ValidadorAgendamentosDeConsulta {
@Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var medicoPossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idMedico(), primeiroHorario, ultimoHorario);
        if (medicoPossuiOutraConsultaNoDia){
            throw new ValidacaoException("Medico possui consulta neste dia");
        }
    }
}
