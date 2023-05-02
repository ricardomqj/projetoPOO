import java.time.LocalDate;

public class ControllerArtigo {
    private ViewerArtigo viewerArtigo;
    private ModelArtigo modelArtigo;

    public ControllerArtigo(ViewerArtigo viewerArtigo,ModelArtigo modelArtigo)
    {
        this.setModelArtigo(modelArtigo);
        this.setViewerArtigo(viewerArtigo);
    }
    public Sapatilha registarSapatilhaNova(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, int tamanhoSapatilha,
                                      boolean temAtacadores, String cor) {

        Sapatilha sapatilha = this.modelArtigo.registarSapatilhaNova(codBarras, dataop, precoBase, nomeTrans, tamanhoSapatilha, temAtacadores, cor);

        return sapatilha;
    }

    public Sapatilha registarSapatilhaUsada(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, int tamanhoSapatilha,
                                       boolean temAtacadores, String cor, int numDonos, int avalEstado) {

        Sapatilha sapatilhausa = this.modelArtigo.registarSapatilhaUsada(codBarras, dataop, precoBase, nomeTrans, tamanhoSapatilha, temAtacadores, cor, numDonos, avalEstado);

        return sapatilhausa;
    }

    public Mala registarMalaNova(Utilizador utilizador, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String tamanho,
                                 String material, int anoColecao) {

        Mala mala = this.modelArtigo.registarMalaNova(utilizador, codBarras, dataop, precoBase, nomeTrans, tamanho, material, anoColecao);

        return mala;
    }

    public Mala registarMalaUsada(Utilizador utilizador, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String tamanho,
                                  String material, int anoColecao, int numDonos, int avalEstado) {

        Mala malausa = this.modelArtigo.registarMalaUsada(utilizador, codBarras, dataop, precoBase, nomeTrans, tamanho, material, anoColecao, numDonos, avalEstado);

        return malausa;
    }

    public TShirt registarTShirtNova(Utilizador utilizador, String codBarras, LocalDate dataop, double precoBase, String nomeTrans, int tamanho,
                                   int padrao) {

        TShirt tshirt = this.modelArtigo.registarTShirtNova(utilizador, codBarras, dataop, precoBase, nomeTrans, tamanho, padrao);
        return tshirt;
    }

    public TShirt registarTShirtUsada(Utilizador utilizador, String codBarras, LocalDate dataop, String nomeTrans, double precoBase, int tamanho,
                                    int padrao, int numDonos, int avalEstado) {

        TShirt tshirtusa = this.modelArtigo.registarTShirtUsada(utilizador, codBarras, dataop, nomeTrans, precoBase, tamanho, padrao, numDonos, avalEstado);
        return tshirtusa;
    }

    public void setViewerArtigo(ViewerArtigo viewerArtigo) {
        this.viewerArtigo = viewerArtigo;
    }

    public void setModelArtigo(ModelArtigo modelArtigo) {
        this.modelArtigo = modelArtigo;
    }
}
