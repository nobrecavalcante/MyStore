package sumplus.barros.com.mystore;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import model.Produto;

public class ProdutoViewHolder extends RecyclerView.ViewHolder {

    final TextView nome;
    final TextView descricao;
    final TextView preco;
    final ImageView foto;
    final Button btEditar;
    final RecyclerView recyclerView;


    public ProdutoViewHolder(View view) {
        super(view);


        nome = (TextView)
                view.findViewById(R.id.tvNomeProduto);
        descricao = (TextView)
                view.findViewById(R.id.tvDescricaoProduto);
        preco = (TextView)
                view.findViewById(R.id.tvPrecoProduto);
        foto  = view.findViewById(R.id.imagemProduto);
        btEditar = view.findViewById(R.id.btEditar);
        recyclerView = view.findViewById(R.id.recycler);





        // restante das buscas
    }

}