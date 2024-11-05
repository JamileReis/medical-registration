package med.voll.api.Paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findAllByAtivo(Boolean paginacao);

    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);
}
