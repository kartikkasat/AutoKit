package example.com.auto_kit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText reading=view.findViewById(R.id.reading);
        final EditText cost=view.findViewById(R.id.cost);
        final EditText ltr=view.findViewById(R.id.ltr);
        final DBAdapter dbAdapter=new DBAdapter(getActivity());
        dbAdapter.open();
        final TextView date=view.findViewById(R.id.date);
        TextView time=view.findViewById(R.id.time);
        Calendar calendar=Calendar.getInstance();
        Date date1=calendar.getTime();
        DateFormat dateFormat=new SimpleDateFormat("HH:mm");
        final String formatedDate=dateFormat.format(date1);
        DateFormat dateFormat1=new SimpleDateFormat("dd/MM/yyyy");
        final String formatedDate1=dateFormat1.format(date1);
        time.setText(formatedDate);
        date.setText(formatedDate1);
        Button submit=view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String meter=reading.getText().toString();
                String cost1=cost.getText().toString();
                String litre1=ltr.getText().toString();
                if (meter.equals("")||cost1.equals("")||litre1.equals(""))
                    Toast.makeText(getActivity(),"Enter All required Fields",Toast.LENGTH_SHORT).show();
                else {
                    long id = dbAdapter.insertEntry(formatedDate1, formatedDate, meter, cost1, litre1);
                    Toast.makeText(getActivity(), "Submitted", Toast.LENGTH_SHORT).show();
                    reading.setText("");
                    cost.setText("");
                    ltr.setText("");
                }

            }
        });
    }
}
