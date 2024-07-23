package med.voll.api.Paciente;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.Endereco.Endereco;
import org.springframework.data.annotation.Id;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @jakarta.persistence.Id
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    @Embedded
    private Endereco endereco;
     
    public Paciente(DadosCadastroPaciente dados){
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.endereco = new Endereco(dados.endereco());
    }


}
