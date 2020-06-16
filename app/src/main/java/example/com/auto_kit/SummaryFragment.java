package example.com.auto_kit;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SummaryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summary,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int i=0;
        TableLayout tableLayout=view.findViewById(R.id.table_layout);
        DecimalFormat df2=new DecimalFormat(".##");
        final DBAdapter dbAdapter=new DBAdapter(getActivity());
        dbAdapter.open();
        Cursor cursor=dbAdapter.getAllEntry();
        if (cursor.moveToFirst()) {
            do {
                TableRow tableRow = new TableRow(getActivity());
                TableRow.LayoutParams layoutParams=new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
                tableRow.setLayoutParams(layoutParams);
                TextView sno = new TextView(getActivity());
                TextView date = new TextView(getActivity());
                TextView time = new TextView(getActivity());
                TextView meter = new TextView(getActivity());
                TextView cost = new TextView(getActivity());
                TextView litre = new TextView(getActivity());
                TextView average = new TextView(getActivity());
                if (i==1){
                    Double current_meter=Double.parseDouble(cursor.getString(3));
                    cursor.moveToPrevious();
                    Double previous_meter=Double.parseDouble(cursor.getString(3));
                    Double previous_litre=Double.parseDouble(cursor.getString(5));
                    cursor.moveToNext();
                    Double average1=(current_meter-previous_meter)/(previous_litre);
                    String avg=Double.toString(Double.parseDouble(df2.format(average1)));
                    average.setText(avg);
                    average.setGravity(Gravity.CENTER);
                    average.setPadding(5,5,5,5);
                    average.setBackgroundResource(R.drawable.textview_border);
                }
                sno.setText(cursor.getString(0));
                sno.setGravity(Gravity.CENTER);
                sno.setPadding(5,5,5,5);
                sno.setBackgroundResource(R.drawable.textview_border);
                date.setText(cursor.getString(1));
                date.setGravity(Gravity.CENTER);
                date.setPadding(5,5,5,5);
                date.setBackgroundResource(R.drawable.textview_border);
                time.setText(cursor.getString(2));
                time.setGravity(Gravity.CENTER);
                time.setPadding(5,5,5,5);
                time.setBackgroundResource(R.drawable.textview_border);
                meter.setText(cursor.getString(3));
                meter.setGravity(Gravity.CENTER);
                meter.setPadding(5,5,5,5);
                meter.setBackgroundResource(R.drawable.textview_border);
                cost.setText(cursor.getString(4));
                cost.setGravity(Gravity.CENTER);
                cost.setPadding(5,5,5,5);
                cost.setBackgroundResource(R.drawable.textview_border);
                litre.setText(cursor.getString(5));
                litre.setGravity(Gravity.CENTER);
                litre.setPadding(5,5,5,5);
                litre.setBackgroundResource(R.drawable.textview_border);
                if (i==0) {
                    average.setText("0.0");
                    average.setGravity(Gravity.CENTER);
                    average.setPadding(5,5,5,5);
                    average.setBackgroundResource(R.drawable.textview_border);

                }
                tableRow.addView(sno);
                tableRow.addView(date);
                tableRow.addView(time);
                tableRow.addView(meter);
                tableRow.addView(cost);
                tableRow.addView(litre);
                tableRow.addView(average);
                tableLayout.addView(tableRow);
                if (i==0)
                    i++;
            } while (cursor.moveToNext());
        }
    }
}
