package med.voll.api.Controller;

import jakarta.validation.Valid;
import med.voll.api.Medico.Medico;
import med.voll.api.Paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public Paciente cadastrar(@RequestBody @Valid DadosCadastroPaciente dados) {
       return repository.save(new Paciente(dados));
    }
    @GetMapping
    public List<Paciente> listar(@PageableDefault(size = 10, sort = {"nome"}) Boolean paginacao){
        return repository.findAllByAtivo(true);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void remover(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.inativar();
    }
}