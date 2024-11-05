package med.voll.api.Paciente;

import med.voll.api.Endereco.Endereco;

public record DadosDetalhamentoPaciente(String nome, String cpf, String email, Long id, String telefone, Boolean ativo, Endereco endereco)  {
    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getNome(), paciente.getCpf(), paciente.getEmail(), paciente.getId(), paciente.getTelefone(), paciente.getAtivo(), paciente.getEndereco());

    }


}
