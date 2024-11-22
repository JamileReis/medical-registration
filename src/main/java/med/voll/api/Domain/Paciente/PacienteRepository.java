package med.voll.api.Domain.Paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findAllByAtivo(Boolean paginacao);

    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);
}
