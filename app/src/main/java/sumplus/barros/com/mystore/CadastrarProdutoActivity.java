package sumplus.barros.com.mystore;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import model.Produto;
import utilidades.MoneyTextWatcher;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URI;
import java.util.Locale;
import java.util.UUID;

public class CadastrarProdutoActivity extends AppCompatActivity implements View.OnClickListener{

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
        setContentView(R.layout.activity_cadastrar_produto);

        etNomeProduto = findViewById(R.id.etNomeProduto);
        etDescricao = findViewById(R.id.etDescricaoProduto);
        etPreco = findViewById(R.id.etPrecoProduto);
        imageView = findViewById(R.id.imagemProduto);
        btCadastrarProduto = findViewById(R.id.btSalvar);
        btGaleria = findViewById(R.id.btGaleria);
        progresso = findViewById(R.id.progresso);

        //Locale mLocale = new Locale("pt");
        etPreco.addTextChangedListener(new MoneyTextWatcher(etPreco));

        progresso.setProgress(0);

        btGaleria.setOnClickListener(this);
        btCadastrarProduto.setOnClickListener(this);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        storageRef = storage.getInstance().getReference();
        // Create a reference to "mountains.jpg"
        mountainsRef = storageRef.child("mountains.jpg");

// Create a reference to 'images/mountains.jpg'
        mountainImagesRef = storageRef.child("images/produto.jpg");

// While the file names are the same, the references point to different files
        mountainsRef.getName().equals(mountainImagesRef.getName());    // true
        mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false


    }

    @Override
    public void onClick (View v) {

        if (v.getId() == btCadastrarProduto.getId()) {
            validaProduto();

        }

        if (v.getId() == btGaleria.getId()) {

            //Abrindo galeria e pegando a foto
            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);

            startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), 123);

        }
    }


    private void validaProduto() {
        final String nomeProduto = etNomeProduto.getText().toString();
        final String descricao = etDescricao.getText().toString();
        final String preco = etPreco.getText().toString();

        // Title is required
        if (TextUtils.isEmpty(nomeProduto)) {
            etNomeProduto.setError(REQUIRED);
            return;
        }

        // Body is required
        if (TextUtils.isEmpty(descricao)) {
            etDescricao.setError(REQUIRED);
            return;
        }

        if (TextUtils.isEmpty(preco)) {
            etPreco.setError(REQUIRED);
            return;
        }

        id = UUID.randomUUID().toString();

        //file = Uri.fromFile(new File("/storage/emulated/0/DCIM/Camera/IMG_20180516_165120.jpg"));
        StorageReference riversRef = storageRef.child("images/produtos/"+id);
        uploadTask = riversRef.putFile(file);
        progresso.setBackgroundColor(6);
        progresso.setProgress(5);

        // Observe state change events such as progress, pause, and resume
        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                progresso.setProgress((int) progress);
                System.out.println("Upload is " + progress + "% done");
            }
        }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                System.out.println("Upload is paused");
            }
        });

// Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads

                Toast.makeText(CadastrarProdutoActivity.this, exception.getCause().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                downloadUrl = taskSnapshot.getDownloadUrl();
                urlFoto = downloadUrl.toString();

                Produto produtos = new Produto(nomeProduto, descricao, preco, urlFoto);

                mDatabase.child("produtos").child(id).setValue(produtos);

                Toast.makeText(CadastrarProdutoActivity.this, "Cadastro concluído com sucesso!",
                        Toast.LENGTH_SHORT).show();

                Intent telaListaProduto = new Intent(getApplicationContext(), ProdutosActivity.class);
                startActivity(telaListaProduto);
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 123){
                file = data.getData();
                imageView.setImageURI(file);

                Toast.makeText(CadastrarProdutoActivity.this, file.toString(),
                        Toast.LENGTH_SHORT).show();

            }
        }
    }


}



