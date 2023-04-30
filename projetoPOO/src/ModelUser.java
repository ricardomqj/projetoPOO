import java.util.Map;
import java.util.stream.Collectors;

public class ModelUser {
    private Map<String, Utilizador> listaUsers;

    public Utilizador getUserByEmail(String email) {
        Utilizador ret = null;

        for(Utilizador user : this.listaUsers.values()) {
            if(user.getEmail().equals(email)) {
                ret = user;
                break;
            }
        }

        return ret;
    }

    public Map<String, Utilizador> getListaUsers() {
        return this.listaUsers.values().stream().collect(Collectors.toMap(Utilizador::getCodigoSistema, Utilizador::clone));
    }

    public void setListaUsers(Map<String, Utilizador> listaUsers) {
        this.listaUsers = listaUsers.values().stream().collect(Collectors.toMap(Utilizador::getCodigoSistema, Utilizador::clone));
    }
}
