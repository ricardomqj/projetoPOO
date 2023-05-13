import jdk.jshell.execution.Util;

import java.time.LocalTime;
import java.util.Map;

public class ControllerVersao {
    private ViewerVersao viewerVersao;
    private ModelVersao modelVersao;

    public ControllerVersao(ViewerVersao viewerVersao,ModelVersao modelVersao)
    {
        this.setModelVersao(modelVersao);
        this.setViewerVersao(viewerVersao);
    }

    //methods

    public Map<LocalTime, Versao> getListaVersoes() {
        return this.modelVersao.getListaVersoes();
    }

    // ADD TXT

    public void addUserToTxt(Utilizador user, String versaoUserTxt) {
        this.modelVersao.addUserToTxt(user, versaoUserTxt);
    }

    public void addTransportadoraTxt(Transportadora trans, String versaoTransportadorasTxt) {
        this.modelVersao.addTransportadoraTxt(trans, versaoTransportadorasTxt);
    }

    public void addSapatilhaTxt(Sapatilha tilha, String versaoArtigosTxt) {
        this.modelVersao.addSapatilhaTxt(tilha, versaoArtigosTxt);
    }
    public void addSapatilhaUsadaTxt(Sapatilha tilha, String versaoArtigosTxt) {
        this.modelVersao.addSapatilhaUsadaTxt(tilha, versaoArtigosTxt);
    }

    public void addMalaTxt(Mala mala, String versaoArtigosTxt) {
        this.modelVersao.addMalaTxt(mala, versaoArtigosTxt);
    }
    public void addMalaUsadaTxt(Mala mala, String versaoArtigosTxt) {
        this.modelVersao.addMalaUsadaTxt(mala, versaoArtigosTxt);
    }

    public void addTShirtTxt(TShirt tshirt, String versaoArtigosTxt) {
        this.modelVersao.addTShirtTxt(tshirt, versaoArtigosTxt);
    }
    public void addTShirtUsadaTxt(TShirt tshirt, String versaoArtigosTxt) {
        this.modelVersao.addTShirtUsadaTxt(tshirt, versaoArtigosTxt);
    }

    // ATUALIZA TXT

    public void atualizaUserTxt(Utilizador userComprador, String versaoUsersTxt) {
        this.modelVersao.atualizaUserTxt(userComprador, versaoUsersTxt);
    }

    public void atualizaTransportadoraTxt(Transportadora trans, String versaoTransportadorasTxt) {
        this.modelVersao.atualizaTransportadoraTxt(trans, versaoTransportadorasTxt);
    }

    /*
    public void atualizaEncomendaTxt(Utilizador user, , String versaoUsersTxt) {
        this.modelVersao.atualizaTransportadoraTxt(trans, versaoUsersTxt);
    }
    */


    public void atualizaArtigoTxt(Artigo artigo, String versaoArtigosTxt) {
        this.modelVersao.atualizaArtigoTxt(artigo, versaoArtigosTxt);
    }


    public void saveVersao(Versao versaoatual) {
        this.modelVersao.saveVersao(versaoatual);
    }

    // GETTERS E SETTERS

    public String getListaVersoesToString(Map<LocalTime, Versao> listaVersoes) {
        return this.viewerVersao.getListaVersoesToString(listaVersoes);
    }

    public ModelVersao getModelVersao() {
        return modelVersao;
    }
    public void setModelVersao(ModelVersao modelVersao) {
        this.modelVersao = modelVersao;
    }

    public ViewerVersao getViewerVersao() {
        return viewerVersao;
    }
    public void setViewerVersao(ViewerVersao viewerVersao) {
        this.viewerVersao = viewerVersao;
    }
}
