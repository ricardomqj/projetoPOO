import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ModelEncomenda {

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
}
