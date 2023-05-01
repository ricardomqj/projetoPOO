import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ModelUtlizador {
    private Map<String,Utilizador> listaUtilizadores;

    public ModelUtlizador()
    {
        this.listaUtilizadores = new HashMap<String,Utilizador>();
    }
    public void criaUtlizador(String email,String nome,String morada,String nif)
    {
        String codigoSistema = gerarCodigoSistema(this.listaUtilizadores);

        Utilizador utilizador = new Utilizador(codigoSistema,email,nome,morada,nif);

        this.listaUtilizadores.put(utilizador.getCodigoSistema(),utilizador.clone());
    }

    private String gerarCodigoSistema(Map<String, Utilizador> listaUtilizadores)
    {
        String codigoSistema = UUID.randomUUID().toString();
        while (listaUtilizadores.containsKey(codigoSistema))
        {
            codigoSistema = UUID.randomUUID().toString();
        }
        return codigoSistema;
    }

    public boolean loginUtlizador(String email)
    {
        if(getUserByEmail(email) == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Utilizador getUserByEmail(String email) {
        Utilizador ret = null;
        for(Utilizador user : this.listaUtilizadores.values()) {
            if(user.getEmail().equals(email)) {
                ret = user;
                break;
            }
        }
        return ret;
    }
}
