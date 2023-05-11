import java.util.List;
import java.util.Map;

public class ViewerArtigo {
    private String artigosToString(Artigo artigo,StringBuilder ultimateString) {

        ultimateString.append("Código de barras: ");
        ultimateString.append(artigo.getCodBarras().toString());
        ultimateString.append("\nPreço Base: ");
        ultimateString.append(artigo.getPrecoBase());
        return ultimateString.toString();
    }

    public String listaDeArtigosParaVenda(List<Artigo> list)
    {
        StringBuilder ultimateString = new StringBuilder();

        for(Artigo artigo : list)
        {
            ultimateString.append("\n---- Artigo ----\n");
            artigosToString(artigo,ultimateString);
        }

        return ultimateString.toString();
    }
}
