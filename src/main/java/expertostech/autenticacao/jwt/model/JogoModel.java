package expertostech.autenticacao.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
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
    private String so;
    private String processador;
    private String placaDeVideo;
    private Integer quantMemoria;
    private String tipoMemoria;
    private Integer quantArmazenamento;
    private String tipoArmazenamento;
    private String classficacaoIndicativa;
    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDate dataCriacao;
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
    private double valorJogo;
    //adicionar classificação, video, colocar genero como array

}
