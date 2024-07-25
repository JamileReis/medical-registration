package med.voll.api.Medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.Endereco.DadosEndereco;


public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
