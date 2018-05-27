package model;

import android.media.Image;
import android.net.Uri;
import android.widget.ImageView;

import java.net.URI;

public class Produto {

    private String titulo;
    private String descricao;
    private String preco;
    private Image imagem;
    private String downloadUrl;

    public Produto() {
    }

    public Produto(String titulo, String descricao, String preco, String downloadUrl) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.imagem = imagem;
        this.downloadUrl = downloadUrl;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public Image getUrlFoto() {
        return imagem;
    }

    public void setUrlFoto(Image urlFoto) {
        this.imagem = urlFoto;
    }
    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
