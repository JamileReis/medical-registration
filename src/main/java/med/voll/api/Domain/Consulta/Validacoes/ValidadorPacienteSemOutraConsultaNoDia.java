package med.voll.api.Domain.Consulta.Validacoes;

import med.voll.api.Domain.Consulta.ConsultaRepository;
import med.voll.api.Domain.Consulta.DadosAgendamentoConsulta;
import med.voll.api.Domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia extends ValidadorAgendamentosDeConsulta {
   @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
        if (pacientePossuiOutraConsultaNoDia){
            throw new ValidacaoException("Paciente possui consulta neste dia");
        }
    }
}
