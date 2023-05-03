import java.util.Map;

public class ViewerArtigo {
    public String artigosToString(Map<String, Artigo> artigos) {

        StringBuilder ultimateString = new StringBuilder();

        for(Map.Entry<String, Artigo> entry : artigos.entrySet()) {
            Artigo artigo = entry.getValue();

            System.out.println(artigo.toString() + "\n^^^Artigo^^^");

            ultimateString.append(artigo.toString());
        }

        return ultimateString.toString();
    }
}
