package med.voll.api.Medico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findAllByAtivo(Boolean ativo);

}