import java.time.LocalDate;

public class Usado extends Estado{
    private int numDonos;
    private int avalEstado;

    public Usado(){
        this.numDonos = 0;
        this.avalEstado = 0;
    }

    /*
    public Usado(Usado usado)
    {
        super(usado);
        this.numDonos = usado.getNumDonos();
        this.avalEstado = usado.getAvalEstado();
    }
    */

    //public Usado clone() { return new Usado(this);}

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("Número de donos anteriores: ").append(this.numDonos).append("\n");
        sb.append("Avaliação do estado: ").append(this.avalEstado).append("\n");

        return sb.toString();
    }

    // GETTERS E SETTERS

    public int getAvalEstado() {
        return avalEstado;
    }

    public void setAvalEstado(int avalEstado) {
        this.avalEstado = avalEstado;
    }

    public int getNumDonos() {
        return numDonos;
    }

    public void setNumDonos(int numDonos) {
        this.numDonos = numDonos;
    }
}