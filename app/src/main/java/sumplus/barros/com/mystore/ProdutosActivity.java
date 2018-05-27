package sumplus.barros.com.mystore;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import model.Produto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProdutosActivity extends AppCompatActivity implements View.OnClickListener{

    private  FloatingActionButton flbAdicionar;
    private Button btEditar;
    AdapterProduto adapter;
    RecyclerView recyclerView;
    //List<Produto> produtos;

    RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        listaProdutos();

        flbAdicionar = findViewById(R.id.flbAdicionar);

        flbAdicionar.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        Intent telaProduto = new Intent(getApplicationContext(),CadastrarProdutoActivity.class);
        startActivity(telaProduto);
    }


    public void listaProdutos() {

        final List<Produto> lista = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("produtos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){

                    Produto produto = objSnapshot.getValue(Produto.class);

                    lista.add(produto);
                }


                recyclerView = findViewById(R.id.recycler);

                recyclerView.setAdapter(new AdapterProduto(lista, ProdutosActivity.this));

                recyclerView.setLayoutManager(layout);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    public void chama(int position){

        Intent telaProduto = new Intent(this, EditarProdutoActivity.class);
        startActivity(telaProduto);

        EditarProdutoActivity editarProduto = new EditarProdutoActivity();

        editarProduto.carregaDados("5c7bd291-fb9f-4dd9-839b-ee44ed93ce15");

    }
}
