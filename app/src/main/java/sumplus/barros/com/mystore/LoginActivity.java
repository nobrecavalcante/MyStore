package sumplus.barros.com.mystore;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    //1 passo -- declarar os componentes visuais
    private EditText etMail;
    private EditText etSenha;
    private Button btLogar;
    private Button btRegistre;
    private Button btLembrar;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //2 passo -- linkar com o xml
        etMail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        btLogar = findViewById(R.id.btLogar);
        btRegistre = findViewById(R.id.btRegistre);
        btLembrar = findViewById(R.id.btLembrar);
        //3 passo - tratar os eventos dos componentes visuais
        btLogar.setOnClickListener(this);
        btRegistre.setOnClickListener(this);
        btLembrar.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

            }
        };



    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }



    @Override
    protected void onStop() {
        super.onStop();
   if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btLogar:
                String email = etMail.getText().toString().trim();
                String senha = etSenha.getText().toString().trim();

               //Toast.makeText(getApplicationContext(),"Clicado em logar",Toast.LENGTH_LONG).show();
               mAuth.signInWithEmailAndPassword(email, senha)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                  //  Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Toast.makeText(LoginActivity.this, "Sucess! ."+ user.getDisplayName(),
                                            Toast.LENGTH_SHORT).show();
                                    Intent telaProdutos = new Intent(getApplicationContext(),ProdutosActivity.class);
                                    startActivity(telaProdutos);
                                    finish();
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                   // Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                  //  updateUI(null);
                                }

                                // ...
                            }
                        });
                break;
            case R.id.btRegistre:
               // Toast.makeText(getApplicationContext(),"Clicado em registrar",Toast.LENGTH_LONG).show();
                Intent telaRegistrar = new Intent(LoginActivity.this,RegistroActivity.class);
                startActivity(telaRegistrar);
                finish();
                break;
            case R.id.btLembrar:
                String emailD = etMail.getText().toString().trim();
                if(TextUtils.isEmpty(emailD)) {
                    Toast.makeText(getApplicationContext(), "Digite seu e-mail!", Toast.LENGTH_LONG).show();
                    return;
                }else{
                    mAuth.sendPasswordResetEmail(emailD).addOnCompleteListener(new OnCompleteListener<Void>() {
                        public static final String TAG = "Email enviado";
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(),"E-mail enviado.",Toast.LENGTH_LONG).show();
                                return;
                            }
                        }
                    });
                }


                Toast.makeText(getApplicationContext(),"Clicado em lembrar",Toast.LENGTH_LONG).show();
                break;
        }

    }
}
