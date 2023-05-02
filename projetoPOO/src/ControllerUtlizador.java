import java.util.Scanner;

public class ControllerUtlizador {
    private ViewerUtlizador viewerUtlizador;
    private ModelUtlizador modelUtlizador;

    public ControllerUtlizador(ViewerUtlizador viewerUtlizador,ModelUtlizador modelUtlizador)
    {
        this.setModelUtlizador(modelUtlizador);
        this.setViewerUtlizador(viewerUtlizador);
    }

    public void criaUtlizador(String email,String nome,String morada,String nif)
    {
        this.modelUtlizador.criaUtlizador(email,nome,morada,nif);
    }

    public void registarSapatilhaUser(Utilizador user, Sapatilha sapatilha) {
        this.modelUtlizador.registarSapatilhaUser(user, sapatilha);
    }

    public void registarSapatilhaUsadaUser (Utilizador user, Sapatilha sapatilha) {
        this.modelUtlizador.registarSapatilhaUsadaUser(user, sapatilha);
    }

    public void registarTShirtUser(Utilizador user, TShirt tshirt) {
        this.modelUtlizador.registarTShirtUser(user, tshirt);
    }

    public void registarTShirtUsadaUser (Utilizador user, TShirt tshirt) {
        this.modelUtlizador.registarTShirtUsadaUser(user, tshirt);
    }

    public void registarMalaUser(Utilizador user, Mala mala) {
        this.modelUtlizador.registarMalaUser(user, mala);
    }

    public void registarMalaUsadaUser (Utilizador user, Mala mala) {
        this.modelUtlizador.registarMalaUsadaUser(user, mala);
    }

    public Utilizador criaUtlizadorVazio()
    {
        return this.modelUtlizador.criaUtlizadorSemNada();
    }

    public Utilizador loginUtlizador(String email)
    {
        return modelUtlizador.loginUtlizador(email);
    }

    public void setViewerUtlizador(ViewerUtlizador viewerUtlizador) {
        this.viewerUtlizador = viewerUtlizador;
    }

    public void setModelUtlizador(ModelUtlizador modelUtlizador) {
        this.modelUtlizador = modelUtlizador;
    }
}
