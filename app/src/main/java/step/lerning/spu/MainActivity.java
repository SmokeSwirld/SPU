package step.lerning.spu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.main_btn_game).setOnClickListener(this::startGame);
        findViewById(R.id.main_btn_chat).setOnClickListener(this::startChat);
        findViewById(R.id.main_button_calc).setOnClickListener(this::CalcClick);
    }
    private void startGame( View view ) {
        startActivity( new Intent( this.getApplicationContext(), GameActivity.class ) );
    }
    private void startChat( View view ) {
        startActivity( new Intent( this.getApplicationContext(), ChatActivity.class ) );
    }
    private void CalcClick(View view){
        Intent CalcIntent = new Intent(this.getApplicationContext(),CalcActivity.class);
        startActivity(CalcIntent);

    }
}