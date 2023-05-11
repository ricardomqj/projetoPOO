import java.util.List;
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

    public void loadUtilizadores() {
        this.modelUtlizador.loadUtilizadores();
    }

    public boolean userTemArtigo(String codBarras) {
        return this.modelUtlizador.userTemArtigo(codBarras);
    }

    public void criaUtilizador2(Utilizador user) {
        this.modelUtlizador.criaUtilizador2(user);
    }

    public Utilizador criaUtlizadorVazio()
    {
        return this.modelUtlizador.criaUtlizadorSemNada();
    }

    public void registarArtigoNoUtlizador(Utilizador user, String codBarras) {
        this.modelUtlizador.registarArtigoNoUtlizador(user,codBarras);
    }

    public List<String> percorreUsers() {
        List<String> produtos = this.modelUtlizador.percorreUsers();

        return produtos;
    }
    /*
    public Utilizador getUserByArtigo(Artigo art) {
        return modelUtlizador.getUserByArtigoAVenda(art);
    }
     */

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

    public void removeVariosArtigosUser(Utilizador user, List<String> lst) {
        modelUtlizador.removeVariosArtigosUser(user, lst);
    }

    public void addArtigoCarrinho(String email, Artigo art) {
        modelUtlizador.addArtigoCarrinho(email, art);
    }

    public void addCarrinhoToEncomendas(String email, Map<String, Encomenda> lstEnc) {
        modelUtlizador.addCarrinhoToEncomendas(email, lstEnc);
    }

    public String getInfoCarrinho(Utilizador user) {
        return modelUtlizador.getInfoCarrinho(user);
    }

    public void removeArtigoCarrinho(Utilizador user, String codBarras) {
        modelUtlizador.removeArtigoCarrinho(user, codBarras);
    }

    public String infoTodosArtigosAVenda() {
        return this.modelUtlizador.infoTodosArtigosAVenda();
    }
    /*
    public String toStringArtigosVendaUser(String email) {
        return this.modelUtlizador.toStringArtigosVendaUser(email);
    }

    public String toStringArtigoAVendaByType(String type) {
        return this.modelUtlizador.toStringArtigoAVendaByType(type);
    }

     */

    public void addEncomendaUser(Utilizador user, Map<String, Artigo> lstArt) {
        this.modelUtlizador.addEncomendaUser(user, lstArt);
    }

    public void setModelUtlizador(ModelUtlizador modelUtlizador) {
        this.modelUtlizador = modelUtlizador;
    }
}
