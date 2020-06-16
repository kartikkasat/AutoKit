package example.com.auto_kit;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CostFragment extends Fragment {
    Double totalCost=0.0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cost,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText month=view.findViewById(R.id.month);
        Button submit=view.findViewById(R.id.submit);
        final TableLayout tableLayout=view.findViewById(R.id.cost_table);
        final DBAdapter dbAdapter=new DBAdapter(getActivity());
        dbAdapter.open();
        final Cursor cursor=dbAdapter.getAllEntry();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cursor.moveToFirst()) {
                    do {
                        String mydate=cursor.getString(1);
                        String myMonth[]=mydate.split("/");
                        if (myMonth[1].equals(month.getText().toString())) {
                            TableRow tableRow = new TableRow(getActivity());
                            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
                            tableRow.setLayoutParams(layoutParams);
                            TextView sno = new TextView(getActivity());
                            TextView date = new TextView(getActivity());
                            TextView cost = new TextView(getActivity());
                            sno.setText(cursor.getString(0));
                            sno.setGravity(Gravity.CENTER);
                            sno.setPadding(5, 5, 5, 5);
                            date.setText(cursor.getString(1));
                            date.setGravity(Gravity.CENTER);
                            date.setPadding(5, 5, 5, 5);
                            cost.setText(cursor.getString(4));
                            cost.setGravity(Gravity.CENTER);
                            cost.setPadding(5, 5, 5, 5);
                            totalCost = totalCost + Double.parseDouble(cursor.getString(4));
                            tableRow.addView(sno);
                            tableRow.addView(date);
                            tableRow.addView(cost);
                            tableLayout.addView(tableRow);
                        }
                    } while (cursor.moveToNext());
                    String total=String.valueOf(totalCost);
                    TableRow tableRow = new TableRow(getActivity());
                    TableRow.LayoutParams layoutParams=new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
                    tableRow.setLayoutParams(layoutParams);
                    TextView blank=new TextView(getActivity());
                    TextView total1=new TextView(getActivity());
                    TextView totalCost1=new TextView(getActivity());
                    blank.setText("");
                    blank.setGravity(Gravity.CENTER);
                    blank.setPadding(5,5,5,5);
                    total1.setText("Total");
                    total1.setGravity(Gravity.CENTER);
                    total1.setPadding(5,5,5,5);
                    totalCost1.setText(total);
                    totalCost1.setGravity(Gravity.CENTER);
                    totalCost1.setPadding(5,5,5,5);
                    tableRow.addView(blank);
                    tableRow.addView(total1);
                    tableRow.addView(totalCost1);
                    tableLayout.addView(tableRow);
                }
            }
        });

    }
}
