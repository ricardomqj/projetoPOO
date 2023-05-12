import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ModelTransportadora {
    private Map<String, Transportadora> listaTransportadoras;

    public ModelTransportadora() {
        this.listaTransportadoras = new HashMap<String, Transportadora>();
    }

    public Transportadora criaTransportadora(String nome, double valBase, double margemLucro) {
        Transportadora trans = new Transportadora(nome, valBase, margemLucro);
        this.listaTransportadoras.put(trans.getNome(), trans.clone());
        return trans;
    }

    public String loadTransportadoras() {
        String filePath = ("src/transportadoras.txt"); // VER ISTO DEPOIS
        File file = new File(filePath);

        if(file.exists()) {
            try {
                Scanner scanner = new Scanner(file);

                StringBuilder stringBuilder = new StringBuilder();

                // Loop para ler todas as linhas do arquivo
                while (scanner.hasNextLine()) {

                    String line = scanner.nextLine();
                    stringBuilder.append(line);

                    // Divide a linha em campos usando o separador ":"
                    String[] fields = line.split(":");

                    // Cria um novo objeto Utilizador e preenche suas informações
                    Transportadora transportadora = new Transportadora();

                    transportadora.setNome(fields[0]);
                    transportadora.setValorBase(Double.parseDouble(fields[1]));
                    transportadora.setMargemLucro(Double.parseDouble(fields[2]));
                    //transportadora.setImposto(fields[3]);

                    listaTransportadoras.put(transportadora.getNome(), transportadora);

                    stringBuilder.append("\n");
                }

                return stringBuilder.toString();

            } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
        }
        else System.out.println("File does not exist!");

        return "";
    }

    public boolean loginTransportadora(String nomeTrans) {
        if(getTransportadoraByName(nomeTrans) == null) return false;
        else return true;
    }

    public String getInfoTransportadoraByName(String nomeTrans) {
        return this.listaTransportadoras.get(nomeTrans).toString();
    }

    public void changeValBaseExpTransportadora(String nomeTrans, double newValBase) {
        Transportadora trans = this.listaTransportadoras.get(nomeTrans);
        trans.setValorBase(newValBase);
        this.listaTransportadoras.put(trans.getNome(), trans.clone());
    }

    public void changeMargemLucroTransportadora(String nomeTrans, double newMargemLucro) {
        Transportadora trans = this.listaTransportadoras.get(nomeTrans);
        trans.setMargemLucro(newMargemLucro);
        this.listaTransportadoras.put(trans.getNome(), trans.clone());
    }

    public String infoTodasAsTransportadoras() {
        StringBuilder sb = new StringBuilder();

        for(Transportadora trans : this.listaTransportadoras.values()) {
            sb.append(trans.toString()).append("\n__________________________\n");
        }

        return sb.toString();
    }

    public Transportadora getTransportadoraByName(String name) {
        return this.listaTransportadoras.get(name);
    }
}
