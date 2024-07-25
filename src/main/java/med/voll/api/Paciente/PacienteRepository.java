package med.voll.api.Paciente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findAllByAtivo(Boolean paginacao);
}
