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

    public void addUserToTxt(Utilizador user, Versao versaoatual) {
        this.modelVersao.addUserToTxt(user, versaoatual);
    }

    public void addTransportadoraTxt(Transportadora trans, Versao versaoatual) {
        this.modelVersao.addTransportadoraTxt(trans, versaoatual);
    }

    public void addSapatilhaTxt(Sapatilha tilha, Versao versaoatual) {
        this.modelVersao.addSapatilhaTxt(tilha, versaoatual);
    }

    public void addMalaTxt(Mala mala, Versao versaoatual) {
        this.modelVersao.addMalaTxt(mala, versaoatual);
    }

    public void addTShirtTxt(TShirt tshirt, Versao versaoatual) {
        this.modelVersao.addTShirtTxt(tshirt, versaoatual);
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
