package rubberduckies.fims;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    /**Contains the database authentication reference*/
    private FirebaseAuth firebaseAuth;
    /**The time in millisecond this activity should stay active*/
    private static int SPLASH_TIME_OUT = 2000; //2 seconds

    public SplashScreen(){

    }
    /**Creates and inflate the visual layout for the activity.*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(() -> {
            firebaseAuth = FirebaseAuth.getInstance();

            if(firebaseAuth.getCurrentUser() != null) {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0); //0 for no animation

            }else {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0); //0 for no animation

            }
            finish();
        }, SPLASH_TIME_OUT);
    }

}
