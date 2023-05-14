import java.util.List;
import java.util.Map;

public class ViewerQueries {

    public String encsVendedorToString(Map<String, Encomenda> encsVendedor) {

        StringBuilder ultimateString = new StringBuilder();

        for(Encomenda enc : encsVendedor.values()) {

            ultimateString.append("\n---- Encomenda ----\n");
            String temp = enc.toString();
            ultimateString.append(temp);
            ultimateString.append("\n");
        }

        return ultimateString.toString();
    }
}
