package com.madlymad.fibonaccidroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements MainActivity.MyFabAction {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    private TextView result;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText number_1 = (EditText) view.findViewById(R.id.etFirstNumber);
        final EditText number_2 = (EditText) view.findViewById(R.id.etSecondNumber);
        final TextView tvNumber_1 = (TextView) view.findViewById(R.id.tvFirstNumber);
        final TextView tvNumber_2 = (TextView) view.findViewById(R.id.tvSecondNumber);
        final Button load = (Button) view.findViewById(R.id.buttonLoad);
        result = (TextView) view.findViewById(R.id.tvFibonacci);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvNumber_1.setText(number_1.getText());
                tvNumber_2.setText(number_2.getText());
                result.setText("Fibonacci series:\n"
                        + number_1.getText() + ", " + number_2.getText());
            }
        });

        final Button calculate = (Button) view.findViewById(R.id.buttonCalculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(tvNumber_1.getText().toString());
                int num2 = Integer.parseInt(tvNumber_2.getText().toString());
                int fibonacci = num1 + num2;
                tvNumber_1.setText(String.valueOf(num2));
                tvNumber_2.setText(String.valueOf(fibonacci));
                if (result.length() > 0) {
                    result.append(", ");
                }
                result.append(String.valueOf(fibonacci));
            }
        });

    }

    @Override
    public void onFabActionClicked() {
        Uri sms_uri = Uri.parse("smsto:");
        Intent sms_intent = new Intent(Intent.ACTION_SENDTO, sms_uri);
        sms_intent.putExtra("sms_body", result.getText().toString());
        startActivity(sms_intent);
    }
}
