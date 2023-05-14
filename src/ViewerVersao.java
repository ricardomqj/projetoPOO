import java.time.LocalTime;
import java.util.Map;

public class ViewerVersao {
    public String getListaVersoesToString(Map<LocalTime, Versao> listaVersoes) {

        StringBuilder ultimateString = new StringBuilder();

        for(Versao ver : listaVersoes.values()) {

            ultimateString.append("Versão - ");
            String temp = ver.toString();
            ultimateString.append(temp);
            ultimateString.append("\n");
        }

        return ultimateString.toString();
    }
}
