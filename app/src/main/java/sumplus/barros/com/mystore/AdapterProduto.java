package sumplus.barros.com.mystore;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import model.Produto;

import java.net.URI;
import java.net.URL;
import java.util.List;

public class AdapterProduto extends RecyclerView.Adapter implements View.OnClickListener {

    private List<Produto> produtos;
    private Context context;
    Uri dadosFoto;
    Produto produto;
    View view;

    public AdapterProduto(List<Produto> produtos, Context context) {
        this.produtos = produtos;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {

        view = LayoutInflater.from(context)
                .inflate(R.layout.main_line_view, parent, false);

        ProdutoViewHolder holder = new ProdutoViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder,
                                 int position) {

        final ProdutoViewHolder holder = (ProdutoViewHolder) viewHolder;

        produto  = produtos.get(position);

        Picasso.with(context).load(produto.getDownloadUrl()).into(holder.foto);

        holder.nome.setText(produto.getTitulo());
        holder.descricao.setText(produto.getDescricao());
        holder.preco.setText(produto.getPreco());

        holder.btEditar.setOnClickListener(view -> updateItem(position));


    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public Produto getItem(int position) {
        return produtos.get(position);
    }

    private ProdutosActivity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof ProdutosActivity) {
                return (ProdutosActivity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }

    @Override
    public void onClick(View view) {



        Toast.makeText(context, produto.getDownloadUrl(),
                Toast.LENGTH_SHORT).show();


    }

    // Método responsável por atualizar um produto já existente na lista.

    private void updateItem(int position) {


       ProdutosActivity teste = new ProdutosActivity();
        teste.chama(position);

    }

}