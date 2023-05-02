import java.time.LocalDate;

public class ControllerArtigo {
    private ViewerArtigo viewerArtigo;
    private ModelArtigo modelArtigo;

    public ControllerArtigo(ViewerArtigo viewerArtigo,ModelArtigo modelArtigo)
    {
        this.setModelArtigo(modelArtigo);
        this.setViewerArtigo(viewerArtigo);
    }
    public void registarSapatilhaNova(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, int tamanhoSapatilha,
                                      boolean temAtacadores, String cor) {

        this.modelArtigo.registarSapatilhaNova(codBarras, dataop, precoBase, nomeTrans, tamanhoSapatilha, temAtacadores, cor);
    }

    public void registarSapatilhaUsada(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, int tamanhoSapatilha,
                                       boolean temAtacadores, String cor, int numDonos, int avalEstado, int desconto) {

        this.modelArtigo.registarSapatilhaUsada(codBarras, dataop, precoBase, nomeTrans, tamanhoSapatilha, temAtacadores, cor, numDonos, avalEstado, desconto);
    }

    public void registarMalaNova(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String tamanho,
                                 String material, int anoColecao, int desconto) {

        this.modelArtigo.registarMalaNova(codBarras, dataop, precoBase, nomeTrans, tamanho, material, anoColecao, desconto);
    }

    public void registarMalaUsada(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, String tamanho,
                                  String material, int anoColecao, int desconto, int numDonos, int avalEstado) {

        this.modelArtigo.registarMalaUsada(codBarras, dataop, precoBase, nomeTrans, tamanho, material, anoColecao, desconto, numDonos, avalEstado);
    }

    public void registarTShirtNova(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, int tamanho,
                                   int padrao) {

        this.modelArtigo.registarTShirtNova(codBarras, dataop, precoBase, nomeTrans, tamanho, padrao);
    }

    public void registarTShirtUsada(String codBarras, LocalDate dataop, double precoBase, String nomeTrans, int tamanho,
                                    int padrao, int numDonos, int avalEstado, int desconto) {

        this.modelArtigo.registarTShirtUsada(codBarras, dataop, precoBase, nomeTrans, tamanho, padrao, numDonos, avalEstado, desconto);
    }

    public void setViewerArtigo(ViewerArtigo viewerArtigo) {
        this.viewerArtigo = viewerArtigo;
    }

    public void setModelArtigo(ModelArtigo modelArtigo) {
        this.modelArtigo = modelArtigo;
    }
}
