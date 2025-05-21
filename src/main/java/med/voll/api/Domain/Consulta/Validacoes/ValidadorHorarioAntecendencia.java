package med.voll.api.Domain.Consulta.Validacoes;

import med.voll.api.Domain.Consulta.DadosAgendamentoConsulta;
import med.voll.api.Domain.ValidacaoException;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorHorarioAntecendencia extends ValidadorAgendamentosDeConsulta {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecendencia minima de 30 minutos");
        }
    }
}
