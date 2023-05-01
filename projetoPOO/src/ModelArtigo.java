import java.time.LocalDate;
import java.util.Map;

public class ModelArtigo {

    private Map<String, Artigo> listaArtigos;
    //private Map<String, Sapatilha> listaSapatilhas;
    //private Map<String, Mala> listaMalas;
    //private Map<String, TShirt> listaTShirts;

    public void registarSapatilhaNova(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, int tamanhoSapatilha,
                                  boolean temAtacadores, String cor) {

        Sapatilha sapatilha = new Sapatilha(codBarras, dataop, precoBase,
                                            nomeTrans, tamanhoSapatilha, temAtacadores, cor);
        // falta stock
    }

    public void registarSapatilhaUsada(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, int tamanhoSapatilha,
                                       boolean temAtacadores, String cor, int numDonos, int avalEstado, int desconto) {

        SapatilhaUsada sapatilhaUsa = new SapatilhaUsada(codBarras, dataop, precoBase, nomeTrans,tamanhoSapatilha,
                                                        temAtacadores, cor, numDonos, avalEstado, desconto);
        // falta stock
    }

    public void registarMalaNova(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String tamanho,
                                 String material, int anoColecao, int desconto) {

        Mala mala = new Mala(codBarras, dataop, precoBase,
                nomeTrans, tamanho, material, anoColecao, desconto);
        // falta stock
    }

    public void registarMalaUsada(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String tamanho,
                                  String material, int anoColecao, int desconto, int numDonos, int avalEstado) {

        MalaUsada malaUsa = new MalaUsada(codBarras, dataop, precoBase, nomeTrans,tamanho,
                material, anoColecao, desconto, numDonos, avalEstado);
        // falta stock
    }

    public void registarTShirtNova(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String tamanho,
                                   String padrao) {

        TShirt tshirt = new Mala(codBarras, dataop, precoBase,
                nomeTrans, tamanho, padrao);
        // falta stock
    }

    public void registarTShirtUsada(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String tamanho,
                                    String padrao, int numDonos, int avalEstado, int desconto) {

        TShirtUsada tshirtUsa = new MalaUsada(codBarras, dataop, precoBase, nomeTrans,tamanho,
                padrao, numDonos, avalEstado, desconto);
        // falta stock
    }
}
