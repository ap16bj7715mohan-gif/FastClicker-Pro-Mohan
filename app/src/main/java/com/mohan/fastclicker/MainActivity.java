
package com.mohan.fastclicker;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

    EditText minFare, maxDistance, minRate;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(30, 35, 30, 30);
        layout.setBackgroundColor(Color.WHITE);

        TextView title = new TextView(this);
        title.setText("FastClicker Pro Mohan");
        title.setTextSize(26);
        title.setTextColor(Color.BLACK);
        title.setGravity(Gravity.CENTER);
        layout.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("Ride Filter Settings");
        subtitle.setTextSize(18);
        subtitle.setGravity(Gravity.CENTER);
        layout.addView(subtitle);

        minFare = addInput(layout, "Minimum Fare (₹)");
        maxDistance = addInput(layout, "Maximum Distance (KM)");
        minRate = addInput(layout, "Minimum Rate per KM (₹)");

        Button check = new Button(this);
        check.setText("CHECK RIDE");
        layout.addView(check);

        result = new TextView(this);
        result.setTextSize(19);
        result.setPadding(10, 25, 10, 25);
        layout.addView(result);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double fare = Double.parseDouble(
                        minFare.getText().toString());
                    double distance = Double.parseDouble(
                        maxDistance.getText().toString());
                    double rate = Double.parseDouble(
                        minRate.getText().toString());

                    if (fare < 0 || distance <= 0 || rate < 0) {
                        result.setText("Enter valid filter values");
                        return;
                    }

                    double perKm = fare / distance;

                    if (fare >= rate * distance) {
                        result.setText(
                            "RIDE MATCHED!\nFare: ₹" + fare +
                            "\nDistance: " + distance + " KM" +
                            "\nRate: ₹" + String.format("%.2f", perKm)
                            + " / KM");
                        result.setTextColor(Color.rgb(0, 130, 0));
                    } else {
                        result.setText("RIDE NOT MATCHED");
                        result.setTextColor(Color.RED);
                    }

                } catch (Exception e) {
                    result.setText("Please enter all values");
                }
            }
        });

        setContentView(layout);
    }

    private EditText addInput(LinearLayout layout, String hint) {
        EditText input = new EditText(this);
        input.setHint(hint);
        input.setInputType(8194);
        layout.addView(input);
        return input;
    }
}
