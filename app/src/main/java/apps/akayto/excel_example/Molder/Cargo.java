package apps.akayto.excel_example.Molder;

public class Cargo {
    private int id;
    private String nome;
    private String descricao;
    private Setor setor;
    private Jornada jornada;
    private int quantiFuncionario;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public int getQuantiFuncionario() {
        return quantiFuncionario;
    }

    public void setQuantiFuncionario(int quantiFuncionario) {
        this.quantiFuncionario = quantiFuncionario;
    }
}
