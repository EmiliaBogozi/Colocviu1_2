package ro.pub.cs.systems.eim.Colocviu1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Colocviu1_2MainActivity extends AppCompatActivity {

    private Button addButton, computeButton;
    private TextView allTerms;
    private EditText nextTerm;

    private int result;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private IntentFilter intentFilter = new IntentFilter();

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String text = allTerms.getText().toString();
            String next_term = nextTerm.getText().toString();

            if(v.getId() == R.id.add_button && next_term != "") {
                if(text != "" && next_term != "")
                    text = text + "+";
                text = text + nextTerm.getText().toString();
                allTerms.setText(text);
            }
            if(v.getId() == R.id.compute_button && text != "") {
                String calcul = allTerms.getText().toString();
                Intent intent = new Intent(getApplicationContext(), Colocviu1_2SecondaryActivity.class);
                intent.putExtra(Constants.CALCUL, calcul);
                startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
            }
            //Log.d(Constants.TAG, "Result: " + String.valueOf(result));

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_2_main);

        Log.d(Constants.TAG, "onCreate() method was invoked");

        addButton = (Button)findViewById(R.id.add_button);
        addButton.setOnClickListener(buttonClickListener);

        allTerms = (TextView)findViewById(R.id.terms);
        allTerms.setOnClickListener(buttonClickListener);

        nextTerm = (EditText)findViewById(R.id.next_term);
        nextTerm.setOnClickListener(buttonClickListener);

        computeButton = (Button)findViewById(R.id.compute_button);
        computeButton.setOnClickListener(buttonClickListener);

        intentFilter.addAction(Constants.INTENT_ACTION);
    }

    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(Constants.TAG, intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(Constants.TAG, "onRestart() method was invoked");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Constants.TAG, "onStart() method was invoked");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Constants.TAG, "onStop() method was invoked");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Constants.TAG, "onResume() method was invoked");
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
        Log.d(Constants.TAG, "onPause() method was invoked");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Constants.TAG, "onDestroy() method was invoked");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(Constants.TAG, "onSaveInstanceState() method was invoked");
        savedInstanceState.putInt(Constants.SUM, result);
        savedInstanceState.putString(Constants.CALCUL, allTerms.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(Constants.TAG, "onRestoreInstanceState() method was invoked");
        if(savedInstanceState.containsKey(Constants.SUM))
        {
            result = savedInstanceState.getInt(Constants.SUM);
            Log.d(Constants.TAG, String.valueOf(result));
        }
        if(savedInstanceState.containsKey(Constants.CALCUL))
        {
            allTerms.setText(savedInstanceState.getString(Constants.CALCUL));
            Log.d(Constants.TAG, String.valueOf(result));
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            result = resultCode;
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();

            if(result > 10)
            {
                Intent intent2 = new Intent(getApplicationContext(), Colocviu1_2Service.class);
                intent2.putExtra(Constants.SUM, result);
                getApplicationContext().startService(intent2);
            }
        }
    }
}