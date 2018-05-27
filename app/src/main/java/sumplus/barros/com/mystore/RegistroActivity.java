package sumplus.barros.com.mystore;

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
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etNome;
    private EditText etEmail;
    private EditText etSenha;
    private Button btRegistar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etREmail);
        etSenha = findViewById(R.id.etRSenha);
        btRegistar = findViewById(R.id.btRRegistrar);
        btRegistar.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View view) {

        String nome = etNome.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String senha = etSenha.getText().toString().trim();

        if(TextUtils.isEmpty(nome)) {
            Toast.makeText(getApplicationContext(), "Digite seu nome!", Toast.LENGTH_LONG).show();
            return;
        }else if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Digite seu e-mail!", Toast.LENGTH_LONG).show();
            return;
        }
        else if(TextUtils.isEmpty(senha)){
            Toast.makeText(getApplicationContext(), "Digite sua senha!", Toast.LENGTH_LONG).show();
            return;
        }else {
            mAuth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();

                                String nomeD = etNome.getText().toString().trim();

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(nomeD)
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    // Log.d(TAG, "User profile updated.");
                                                }
                                            }
                                        });

                            } else {
                                // If sign in fails, display a message to the user.

                            }

                            // ...
                        }
                    });


        }
        }


    }
