package ro.pub.cs.systems.eim.Colocviu1_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Colocviu1_2SecondaryActivity extends AppCompatActivity {

    public int calC(String input) {
        String operators[]=input.split("[0-9]+");
        String operands[]=input.split("[+-]");
        int agregate = Integer.parseInt(operands[0]);
        for(int i=1;i<operands.length;i++){
            if(operators[i].equals("+"))
                agregate += Integer.parseInt(operands[i]);
            else
                agregate -= Integer.parseInt(operands[i]);
        }
        return agregate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if(intent != null && intent.getExtras().containsKey(Constants.CALCUL)) {
            String calcul = intent.getStringExtra(Constants.CALCUL);
            int result = calC(calcul);
            setResult(result, intent);
        }
        finish();
    }
}