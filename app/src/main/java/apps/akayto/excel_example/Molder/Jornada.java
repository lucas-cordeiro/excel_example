package apps.akayto.excel_example.Molder;

public class Jornada {
    private int id;
    private String nome;
    private boolean diarista;
    private boolean folgaFeriado;
    private String escala;
    private int horas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isDiarista() {
        return diarista;
    }

    public void setDiarista(boolean diarista) {
        this.diarista = diarista;
    }

    public boolean isFolgaFeriado() {
        return folgaFeriado;
    }

    public void setFolgaFeriado(boolean folgaFeriado) {
        this.folgaFeriado = folgaFeriado;
    }

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
}
