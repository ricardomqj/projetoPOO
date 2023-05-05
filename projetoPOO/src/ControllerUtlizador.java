import java.util.Map;

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

    public void criaUtilizador2(Utilizador user) {
        this.modelUtlizador.criaUtilizador2(user);
    }

    public Utilizador criaUtlizadorVazio()
    {
        return this.modelUtlizador.criaUtlizadorSemNada();
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

    public Map<String, Artigo> percorreUsers() {
        Map<String, Artigo> produtos = this.modelUtlizador.percorreUsers();

        return produtos;
    }

    public Utilizador getUserByArtigo(Artigo art) {
        return modelUtlizador.getUserByArtigoAVenda(art);
    }

    public Utilizador loginUtlizador(String email)
    {
        return modelUtlizador.loginUtlizador(email);
    }

    public Utilizador getUserByEmail(String email) {
        return this.modelUtlizador.getUserByEmail(email);
    }

    public String infoTodosUsers() {
        return this.modelUtlizador.infoTodosUsers();
    }

    public String infoUserByEmail(String email) {
        return this.modelUtlizador.infoUserByEmail(email);
    }

    public void setViewerUtlizador(ViewerUtlizador viewerUtlizador) {
        this.viewerUtlizador = viewerUtlizador;
    }

    public void removeVariosArtigosUser(Utilizador user, Map<String, Artigo> lst) {
        modelUtlizador.removeVariosArtigosUser(user, lst);
    }

    public String infoTodosArtigosAVenda() {
        return this.modelUtlizador.infoTodosArtigosAVenda();
    }

    public String toStringArtigosVendaUser(String email) {
        return this.modelUtlizador.toStringArtigosVendaUser(email);
    }

    public String toStringArtigoAVendaByType(String type) {
        return this.modelUtlizador.toStringArtigoAVendaByType(type);
    }

    public void addEncomendaUser(Utilizador user, Map<String, Artigo> lstArt) {
        this.modelUtlizador.addEncomendaUser(user, lstArt);
    }

    public void setModelUtlizador(ModelUtlizador modelUtlizador) {
        this.modelUtlizador = modelUtlizador;
    }
}
