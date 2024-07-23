package med.voll.api.Endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank(message = "O logradouro não deve estar em branco")
         String logradouro,

        @NotBlank(message = "O bairro não deve estar em branco")
        String bairro,

        @NotBlank(message = "O CEP não deve estar em branco")
        @Pattern(regexp = "\\d{8}", message = "O CEP deve conter exatamente 8 dígitos")
        String cep,

        @NotBlank(message = "A cidade não deve estar em branco")
        String cidade,

        @NotBlank(message = "O UF não deve estar em branco")
        String uf,

        String complemento,

        String numero) {
}