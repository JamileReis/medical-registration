package med.voll.api.Domain.Medico;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.Domain.Endereco.DadosEndereco;


public record DadosCadastroMedico(

        @NotBlank(message = "O nome não deve estar em branco")
        String nome,

        @NotBlank(message = "O telefone não deve estar em branco")
        String telefone,

        @NotBlank(message = "O email não deve estar em branco")
        @Email(message = "Formato de email inválido")
        String email,

        @NotBlank(message = "O CRM não deve estar em branco")
        @Pattern(regexp = "\\d{4,6}", message = "O CRM deve conter entre 4 e 6 dígitos")
        String crm,

        @NotNull(message = "A especialidade não deve ser nula")
        Especialidade especialidade,

        @NotNull(message = "O endereço não deve ser nulo")
        @Valid
        DadosEndereco endereco) {
}
