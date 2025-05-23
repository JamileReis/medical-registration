package med.voll.api.Domain.Consulta.Validacoes;

import med.voll.api.Domain.Consulta.DadosAgendamentoConsulta;
import med.voll.api.Domain.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component

public class ValidadorHorarioFuncionamentoClinica extends ValidadorAgendamentosDeConsulta {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;
        if (domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica){
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica!");
        }
    }

}
