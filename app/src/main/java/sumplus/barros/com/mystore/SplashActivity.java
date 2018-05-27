package sumplus.barros.com.mystore;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(this,1500);
    }

    @Override
    public void run() {

        Intent telaLogin = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(telaLogin);
        finish();
    }
}
