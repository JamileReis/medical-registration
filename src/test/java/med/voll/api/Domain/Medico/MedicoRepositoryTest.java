package med.voll.api.Domain.Medico;

import med.voll.api.Domain.Consulta.Consulta;
import med.voll.api.Domain.Endereco.DadosEndereco;
import med.voll.api.Domain.Paciente.DadosCadastroPaciente;
import med.voll.api.Domain.Paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando único médico cadastrado não está disponível na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        // Cria um médico e um paciente
        var medico = cadastrarMedico("Dr. João", "joao@example.com", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente Teste", "paciente@example.com", "12345678900");

        // Cria uma consulta para o médico na data especificada
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        // Tenta escolher um médico disponível na mesma data
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        // Verifica se não há médicos disponíveis
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver um médico quando há um médico disponível na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
        // Cria um médico e um paciente
        var medico = cadastrarMedico("Dr. Maria", "maria@example.com", "654321", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente Teste", "paciente@example.com", "12345678900");

        // Cria uma consulta para o médico em uma data diferente
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var outraData = proximaSegundaAs10.plusDays(1);
        cadastrarConsulta(medico, paciente, outraData);

        // Tenta escolher um médico disponível na data especificada
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        // Verifica se o médico disponível foi retornado
        assertThat(medicoLivre).isNotNull();
        assertThat(medicoLivre.getNome()).isEqualTo("Dr. Maria");
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPaciente(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}