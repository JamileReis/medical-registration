package med.voll.api.Domain.Consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.Domain.Medico.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,
        @NotNull Long idPaciente,
        @NotNull @Future LocalDateTime data,
        Especialidade especialidade) {
}
