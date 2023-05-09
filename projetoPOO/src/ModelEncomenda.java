import java.util.*;
import java.util.stream.Collectors;

public class ModelEncomenda {

    private Map<String, Encomenda> listaEncomendas;

    //methods
    public ModelEncomenda() {
        this.listaEncomendas = new HashMap<String, Encomenda>();
    }

    public void criaEncomenda(Utilizador user, Map<String, Artigo> lstArtigos) {

        String codigoEncomenda = gerarCodigoSistema(this.listaEncomendas);

        Encomenda enc = new Encomenda(codigoEncomenda, user, lstArtigos);

        this.listaEncomendas.put(enc.getCodSistema(), enc.clone());
    }

    public void addArtigoEncomenda (Encomenda enc, Artigo artigo) {
        enc.insereUmArtigo(artigo);
    }

    /*
    public Artigo getArtigoByCod(String codBarras) {

        Artigo art = null;

        for (Artigo artigo: this.listaArtigos.values()) {
            if(artigo.getCodBarras().equals(codBarras)) art = artigo;
        }

        return art;
    }
     */

    public String addEncomenda(List<Artigo> lstArt, Utilizador user) {
        Map<String, Artigo> lstArtigos = new HashMap<String, Artigo>();
        lstArtigos = lstArt.stream().collect(Collectors.toMap(Artigo::getCodBarras, Artigo::clone));
        String codEncomenda = gerarCodigoSistema(listaEncomendas);
        Encomenda enc = new Encomenda(codEncomenda, user, lstArtigos);
        this.listaEncomendas.put(enc.getCodSistema(), enc.clone());
        return codEncomenda;
    }

    public Map<String, Encomenda> getListaTodasEncomendas() {
        return this.listaEncomendas.values().stream().collect(Collectors.toMap(Encomenda::getCodSistema, Encomenda::clone));
    }

    public String infoTodasAsEncomendas() {
        StringBuilder sb = new StringBuilder();

        for(Encomenda enc : this.listaEncomendas.values()) {
            sb.append(enc.toString()).append("\n__________________________________\n");
        }

        return sb.toString();
    }

    private String gerarCodigoSistema(Map<String, Encomenda> listaEncomendas)
    {
        String codigoSistema = UUID.randomUUID().toString();
        while (listaEncomendas.containsKey(codigoSistema))
        {
            codigoSistema = UUID.randomUUID().toString();
        }
        return codigoSistema;
    }

    /*
    public void criaEncomendaModel(String emailUser) {
        Encomenda enc = new Encomenda();

        Utilizador user = ModelUser.getUserByEmail(emailUser);
        //Map<String, Artigo> artigos = new HashMap<>();

        enc.setUser(user);

    }

    public void addArtigoEncomendaModel(Map<String, Artigo> artigos, String codBarras) {

        Scanner scanner = new Scanner(System.in);

        Map<String, Artigo> listaArtigos = Model.getListaArtigos();

        Artigo artigo = listaArtigos.get(codBarras);

        artigos.put(codBarras, artigo);

        // codigo: Barras fazer hash
        // stock: if getStock == null dizer que nao tem stock
        // datalanc: pedir
        // transp: PEDIR / se nao existir, "transportadora nao encontrada"
        // precobase: pedir

        // PEDIR APENAS CODIGO DE BARRAS, e ir buscar tudo com ele
        // Se nao existir retornar "codigo nao encontrado tente novamente".

        //System.out.println("Digite o código de barras do Artigo que deseja adicionar: ");
        //String codBarras = scanner.next();

        //Model.addArtigoEncomenda(codBarras, enc);

        //System.out.println("Deseja adicionar mais algum artigo? (S/N)");
        //String simnao = scanner.next();

        //arrayCodBarras["sdasd", "sdas", "dsadsa123"];

        //if (simnao.equals("S")) addArtigoEncomendaModel();
    }

     */
}
