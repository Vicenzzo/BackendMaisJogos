package expertostech.autenticacao.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    @ElementCollection
    private List<String> genero;
    private String plataforma;
    private String SO;
    private String processador;
    private String placaDeVideo;
    private Integer quantMemoria;
    private String tipoMemoria;
    private Integer quantArmazenamento;
    private String tipoArmazenamento;
    @Column(name = "jogoWin", length = 1000000000)
    private byte[] jogoWin;



    @Column(name = "bannerUm", length = 1000000000)
    private byte[] bannerUm;


    @Column(name = "bannerDois", length = 1000000000)
    private byte[] bannerDois;

    @Column(name = "bannerTres", length = 1000000000)
    private byte[] bannerTres;

    @Column(name = "bannerQuatro", length = 1000000000)
    private byte[] bannerQuatro;

    @Column(name = "bannerCinco", length = 1000000000)
    private byte[] bannerCinco;

    @Column(name = "licenca", length = 1000000000)
    private byte[] licenca;
    private Integer idDev;

    //adicionar classificação, video, colocar genero como array

}
