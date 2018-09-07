package app.rstone.com.myapp2;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        class Calc{
            int num, res;
            String op;
            public void setNum(int num){this.num = num;}
            public int getNum(){return num;}
            public void setOp(String op){this.op = op;}
            public String getOp(){return this.op;}
            public void setCalc(){
                switch (op){
                    case "+" : res += num; break;
                    case "-" : res -= num; break;
                    case "*" : res *= num; break;
                    case "/" : res /= num; break;
                }
            }
            public int getCalc(){return res;}
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context ctx = MainActivity.this;
        final EditText calcEx = findViewById(R.id.calc_expression);
        final Calc calc = new Calc();
        findViewById(R.id.plus_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Button op = findViewById(R.id.plus_btn);
               calc.setNum(Integer.parseInt(calcEx.getText().toString()));
               if(calc.getOp() != null){
                   calc.setCalc();
                   calc.setOp(op.getText().toString());
               }else{
                   calc.setOp(op.getText().toString());
                   calc.setCalc();
               }
               calcEx.setText("");
                //Toast.makeText(ctx, ":D", Toast.LENGTH_LONG).show();
            }
        });
        findViewById(R.id.minus_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button op = findViewById(R.id.minus_btn);
                calc.setNum(Integer.parseInt(calcEx.getText().toString()));
                if(calc.getOp() != null){
                    calc.setCalc();
                    calc.setOp(op.getText().toString());
                }else{
                    calc.setOp(op.getText().toString());
                    calc.setCalc();
                }
                calcEx.setText("");
            }
        });
        findViewById(R.id.multi_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button op = findViewById(R.id.multi_btn);
                calc.setNum(Integer.parseInt(calcEx.getText().toString()));
                if(calc.getOp() != null){
                    calc.setCalc();
                    calc.setOp(op.getText().toString());
                }else{
                    calc.setOp(op.getText().toString());
                    calc.setCalc();
                }
                calcEx.setText("");
            }
        });
        findViewById(R.id.division_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button op = findViewById(R.id.division_btn);
                calc.setNum(Integer.parseInt(calcEx.getText().toString()));
                if(calc.getOp() != null){
                    calc.setCalc();
                    calc.setOp(op.getText().toString());
                }else{
                    calc.setOp(op.getText().toString());
                    calc.setCalc();
                }
                calcEx.setText("");
            }
        });
        findViewById(R.id.equal_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.setNum(Integer.parseInt(calcEx.getText().toString()));
                calc.setCalc();
                calc.setOp(null);
                calcEx.setText(calc.getCalc()+"");
            }
        });
        findViewById(R.id.ac_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calc.setNum(0);
                calc.setOp("*");
                calc.setCalc();
                calcEx.setText("");
            }
        });

    }
}
