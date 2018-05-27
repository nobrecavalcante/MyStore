package model;

public class Usuario {
    private String nome;
    private String uid;
    private String dataCadastro;
    private int perfil;//1 cliente, 2, administrador

    public Usuario() {
    }

    public Usuario(String nome, String uid, String dataCadastro, int perfil) {
        this.nome = nome;
        this.uid = uid;
        this.dataCadastro = dataCadastro;
        this.perfil = perfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }
}
