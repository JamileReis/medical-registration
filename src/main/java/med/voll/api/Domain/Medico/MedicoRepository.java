package med.voll.api.Domain.Medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findAllByAtivo(Boolean ativo);


    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}