package med.voll.api.Domain.Medico;


import med.voll.api.Domain.Consulta.AgendaDeConsultas;
import med.voll.api.Domain.Consulta.Consulta;
import med.voll.api.Domain.Consulta.DadosAgendamentoConsulta;
import med.voll.api.Domain.Consulta.DadosDetalhamentoConsulta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson;

    @MockBean
    private AgendaDeConsultas agendaDeConsultas;
    @Test
    @DisplayName("Deveria devolver http 400 quando informacoes estao invalidas")
    @WithMockUser
    void agendar_cenario() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value()); // Correto!
    }

    @Test
    @DisplayName("Deveria devolver código http 200 quando informações estão válidas")
    @WithMockUser
    void agendar_cenario2() throws Exception {
        // Define a data e a especialidade para o teste
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;

        // Cria o objeto de retorno simulado
        var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 2L, 5L, data);

        // Configura o mock para retornar o objeto simulado
        when(agendaDeConsultas.agendar(any())).thenReturn(dadosDetalhamento);

        // Realiza a requisição POST para o endpoint /consultas
        var response = mvc
                .perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosAgendamentoConsultaJson.write(
                                        new DadosAgendamentoConsulta(2L, 5L, data, especialidade)
                                ).getJson())
                )
                .andReturn().getResponse();

        // Verifica se o status da resposta é 200 (OK)
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        // Cria o JSON esperado para comparação
        var jsonEsperado = dadosDetalhamentoConsultaJson.write(
                dadosDetalhamento
        ).getJson();

        // Verifica se o conteúdo da resposta é igual ao JSON esperado
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}