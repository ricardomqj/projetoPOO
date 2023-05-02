import java.time.LocalDate;
import java.util.Map;

public class ModelArtigo {
    private Map<String, Artigo> listaArtigos;
    //private Map<String, Sapatilha> listaSapatilhas;
    //private Map<String, Mala> listaMalas;
    //private Map<String, TShirt> listaTShirts;

    public Sapatilha registarSapatilhaNova(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, int tamanhoSapatilha,
                                      boolean temAtacadores, String cor) {

        Sapatilha sapatilha = new Sapatilha(codBarras,1, dataop, nomeTrans, precoBase,
                 tamanhoSapatilha, temAtacadores, cor);

        // STOCK A 1
        return sapatilha;
    }

    public Sapatilha registarSapatilhaUsada(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, int tamanhoSapatilha,
                                       boolean temAtacadores, String cor, int numDonos, int avalEstado) {

        Sapatilha sapatilhaUsa = new Sapatilha(codBarras,1, dataop, nomeTrans, precoBase, tamanhoSapatilha,
                temAtacadores, cor, numDonos, avalEstado);
        // falta stock
        return sapatilhaUsa;
    }

    public Mala registarMalaNova(Utilizador utilizador, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String tamanho,
                                 String material, int anoColecao) {

        Mala mala = new Mala(codBarras,1, dataop, nomeTrans, precoBase, tamanho, material, anoColecao);
        // falta stock
        return mala;
    }

    public Mala registarMalaUsada(Utilizador utilizador, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String tamanho,
                                  String material, int anoColecao, int numDonos, int avalEstado) {

        Mala malausa = new Mala(codBarras,1, dataop,  nomeTrans, precoBase,tamanho,
                material, anoColecao, numDonos, avalEstado);
        // falta stock
        return malausa;
    }

    public TShirt registarTShirtNova(Utilizador utilizador, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, int tamanho,
                                   int padrao) {

        TShirt tshirt = new TShirt(codBarras, 1, dataop, nomeTrans, precoBase, tamanho, padrao);

        return tshirt;
    }

    public TShirt registarTShirtUsada(Utilizador utilizador, String codBarras, LocalDate dataop, String nomeTrans, double precoBase, int tamanho,
                                    int padrao, int numDonos, int avalEstado) {

        TShirt tshirtUsa = new TShirt(codBarras,1, dataop, nomeTrans, precoBase, tamanho, padrao, numDonos, avalEstado);

        return tshirtUsa;
    }
}
