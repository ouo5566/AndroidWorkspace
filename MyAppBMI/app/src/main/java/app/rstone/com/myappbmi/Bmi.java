package app.rstone.com.myappbmi;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Bmi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi);
        final Context ctx = Bmi.this;
        final EditText height = findViewById(R.id.height);
        final EditText weight = findViewById(R.id.weight);
        final TextView bmiTv = findViewById(R.id.bmi_tv);
        final TextView levelTv = findViewById(R.id.level_tv);
        final class Calc{
            double h, w, bmi;
            String level;
            public void execute(){
                bmi = Math.round((w/(h*h))*100)*0.01;
                level = "NORMAL";
                if(bmi>=35.0){
                    level = "LEVEL 3 OVERWEIGHT";
                }else if(bmi>=30.0){
                    level = "LEVEL 2 OVERWEIGHT";
                }else if(bmi>=25.0){
                    level = "LEVEL 1 OVERWEIGHT";
                }else if(bmi>=23.0){
                    level = "BEFORE LEVEL OF OVERWEIGHT";
                }else if(bmi<18.5){
                    level = "LOW BODY WEIGHT";
                }
            }
        }
        findViewById(R.id.bmi_btn).setOnClickListener(
                (View v)->{
                    Calc c = new Calc();
                    c.h = Double.parseDouble(height.getText().toString()) * 0.01;
                    c.w = Double.parseDouble(weight.getText().toString());
                    c.execute();
                    height.setText("");
                    weight.setText("");
                    bmiTv.setText(c.bmi+"");
                    levelTv.setText(c.level+"");
                }
        );
    }
}
