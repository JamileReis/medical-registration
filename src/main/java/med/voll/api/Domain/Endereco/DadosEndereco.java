package med.voll.api.Domain.Endereco;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @JsonProperty("logradouro")
        @NotBlank(message = "O logradouro não deve estar em branco")
        String logradouro,

        @JsonProperty("bairro")
        @NotBlank(message = "O bairro não deve estar em branco")
        String bairro,

        @JsonProperty("cep")
        @NotBlank(message = "O CEP não deve estar em branco")
        @Pattern(regexp = "\\d{8}", message = "O CEP deve conter exatamente 8 dígitos")
        String cep,

        @JsonProperty("cidade")
        @NotBlank(message = "A cidade não deve estar em branco")
        String cidade,

        @JsonProperty("uf")
        @NotBlank(message = "O UF não deve estar em branco")
        String uf,

        @JsonProperty("complemento")
        String complemento,

        @JsonProperty("numero")
        String numero) {
}