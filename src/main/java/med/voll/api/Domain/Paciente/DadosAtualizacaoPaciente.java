package med.voll.api.Domain.Paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.Domain.Endereco.DadosEndereco;


public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {

}
