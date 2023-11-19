package expertostech.autenticacao.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="jogo")
public class JogoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String descricao;
    private String genero;
    private String plataforma;
    private String SO;
    private String processador;
    private String placaDeVideo;
    private Integer quantMemoria;
    private String tipoMemoria;
    private Integer quantArmazenamento;
    private String tipoArmazenamento;
    @Column(name = "jogo", length = 1000000000)
    private byte[] jogo;
    private Integer idDev;

}
