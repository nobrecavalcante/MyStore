package sumplus.barros.com.mystore;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import model.Produto;

public class EditarProdutoActivity extends AppCompatActivity implements View.OnClickListener{

    public void EditarProdutoActivity(){

    }


    private static final String TAG = "NewPostActivity";
    private static final String REQUIRED = "Required";

    private EditText etNomeProduto;
    private EditText etDescricao;
    private EditText etPreco;
    private ImageView imageView;
    private Button btCadastrarProduto;
    private ProgressBar progresso;
    private Uri downloadUrl;
    private Uri file;
    private Button btGaleria;
    private String urlFoto = "teste";
    private String id;

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]

    FirebaseStorage storage;
    StorageReference storageRef;
    StorageReference mountainsRef;
    StorageReference mountainImagesRef;

    UploadTask uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produto);

        etNomeProduto = findViewById(R.id.etNomeProduto);
        etDescricao = findViewById(R.id.etDescricaoProduto);
        etPreco = findViewById(R.id.etPrecoProduto);
        imageView = findViewById(R.id.imagemProduto);
        btCadastrarProduto = findViewById(R.id.btSalvar);
        btGaleria = findViewById(R.id.btGaleria);
        progresso = findViewById(R.id.progresso);

    }


    @Override
    public void onClick(View view) {

    }

    public void carregaDados(String id){

        final List<Produto> lista = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("produtos").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                Produto produto = dataSnapshot.getValue(Produto.class);

                etNomeProduto.setText("teste");

                lista.add(produto);


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }
}
