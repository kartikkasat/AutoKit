package example.com.auto_kit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Start extends AppCompatActivity {
    Button startb;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        startb=findViewById(R.id.startb);
        startb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences("flagdata",Context.MODE_PRIVATE);
                int flag=sharedPreferences.getInt("flag",1);
                if (flag==2){
                    Intent intent=new Intent(Start.this,Main2Activity.class);
                    startActivity(intent);
                }
                if (flag==1){
                    Intent intent=new Intent(Start.this,TermsAndCons.class);
                    startActivity(intent);
                }
                finish();
            }
        });
    }
}
