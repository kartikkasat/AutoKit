package example.com.auto_kit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TermsAndCons extends AppCompatActivity {
    RadioGroup radiogroup;
    RadioButton radioButton;
    String confirmation="";
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_cons);
        radiogroup=findViewById(R.id.checkb);
        submit=findViewById(R.id.submit);
        TextView textView=findViewById(R.id.terms);
        String string1="By installing this application you agree to the End User License Agreement and Privacy Policy. You can remove this application easily at any time.";
        String string2="Trinity  Fuel-I Terms of Usage:";
        String string3="This legal Software End User License Agreement (Hereinafter referred to as Agreement) is entered into between Trinity  The Licensed companies of FUEL-I and End users (Hereinafter referred to as User) of FUEL-I.";
        String string4="We hereby declare that this is an attempt to analyse your fuel usage over a specific duration of time and it is not exact.You understand the risk in using this service and are liable yourself if any damages occur.You cannot claim any damages to company or developer and they are not liable to pay for the same.";
        String string5="Ensure that fuel meter is blinking or near to empty when entering reading";
        textView.append(String.format(string1+"\n"));
        textView.append(String.format(string2+"\n"));
        textView.append(String.format(string3+"\n"));
        textView.append(String.format(string4+"\n"));
        textView.append(String.format(string5+"\n"));
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButton=radioGroup.findViewById(i);
                boolean isChecked = radioButton.isChecked();
                if (isChecked)
                {
                    confirmation=radioButton.getText().toString();
                    Toast.makeText(getBaseContext(),confirmation,Toast.LENGTH_SHORT).show();
                }

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (confirmation.equals("I AGREE")){
                    Intent intent=new Intent(TermsAndCons.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if (confirmation.equals("I DISAGREE")){
                    finish();
                }
                else if (!confirmation.equals("I AGREE")&&!confirmation.equals("I DISAGREE")){
                    Toast.makeText(getBaseContext(),"Select any of the above options",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
