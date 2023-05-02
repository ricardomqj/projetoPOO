import java.time.LocalDate;
import java.util.Map;

public class ModelArtigo {
    private Map<String, Artigo> listaArtigos;
    //private Map<String, Sapatilha> listaSapatilhas;
    //private Map<String, Mala> listaMalas;
    //private Map<String, TShirt> listaTShirts;

    public void registarSapatilhaNova(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, int tamanhoSapatilha,
                                      boolean temAtacadores, String cor) {

        Sapatilha sapatilha = new Sapatilha(codBarras,1, dataop,nomeTrans, precoBase,
                 tamanhoSapatilha, temAtacadores, cor);
        // STOCK A 1
    }

    public void registarSapatilhaUsada(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, int tamanhoSapatilha,
                                       boolean temAtacadores, String cor, int numDonos, int avalEstado, int desconto) {

        SapatilhaUsada sapatilhaUsa = new SapatilhaUsada(codBarras,1, dataop, nomeTrans, precoBase, tamanhoSapatilha,
                temAtacadores, cor, numDonos, avalEstado, desconto);
        // falta stock
    }

    public void registarMalaNova(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String tamanho,
                                 String material, int anoColecao, int desconto) {

        Mala mala = new Mala(codBarras,1, dataop, nomeTrans, precoBase,
                 tamanho, material, anoColecao, desconto);
        // falta stock
    }

    public void registarMalaUsada(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String tamanho,
                                  String material, int anoColecao, int desconto, int numDonos, int avalEstado) {

        MalaUsada malaUsa = new MalaUsada(codBarras,1, dataop,  nomeTrans, precoBase,tamanho,
                material, anoColecao, desconto, numDonos, avalEstado);
        // falta stock
    }

    public void registarTShirtNova(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String tamanho,
                                   String padrao) {

        TShirt tshirt = new TShirt(codBarras,1, dataop, nomeTrans, precoBase,
                 tamanho, padrao);
        // falta stock
    }

    public void registarTShirtUsada(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, int tamanho,
                                    int padrao, int numDonos, int avalEstado, int desconto) {

        TShirtUsada tshirtUsa = new TShirtUsada(codBarras,1, dataop, nomeTrans, precoBase,tamanho,
                padrao, numDonos, avalEstado, desconto);
    }
}
