package step.lerning.spu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CalcActivity extends AppCompatActivity {

    private TextView tvExpression ;
    private TextView tvResult ;

    private String currentExpression = "";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_calc );

        tvExpression = findViewById( R.id.calc_tv_expression ) ;
        tvResult = findViewById( R.id.calc_tv_result ) ;


        findViewById( R.id.calc_btn_c ).setOnClickListener( this::clearClick ) ;

        for( int i = 0; i < 10; i++ ) {
            findViewById(
                    getResources()  // R.
                            .getIdentifier(    // .id (R.id.calc_btn_[i])
                                    "calc_btn_" + i,
                                    "id",
                                    getPackageName()
                            )
            ).setOnClickListener( this::digitClick );
        }


        findViewById(R.id.calc_btn_backspace).setOnClickListener(this::deleteClick);
        findViewById(R.id.calc_btn_plus_minus).setOnClickListener(this::minusplusClick);
        findViewById(R.id.calc_btn_plus).setOnClickListener(this::plusClick);
        findViewById(R.id.calc_btn_equal).setOnClickListener(this::equalsClick);
        findViewById(R.id.calc_btn_minus).setOnClickListener(this::minusClick);
        findViewById(R.id.calc_btn_multiplication).setOnClickListener(this::multiplicationClick);
        findViewById(R.id.calc_btn_divide).setOnClickListener(this::divideClick);
        findViewById(R.id.calc_btn_square).setOnClickListener(this::squareClick);
        findViewById(R.id.calc_btn_sqrt).setOnClickListener(this::sqrtClick);
        findViewById(R.id.calc_btn_percent).setOnClickListener(this::percentClick);
        findViewById(R.id.calc_btn_inverse).setOnClickListener(this::inverseClick);
        findViewById(R.id.calc_btn_comma).setOnClickListener(this::commaClick);


    }
    private void digitClick( View view ) {  // [ numbers ]
        String result = tvResult.getText().toString() ;

        result += ( (Button) view ).getText() ;

        tvResult.setText( result ) ;
    }

    private void commaClick(View view) { // [ , ]
        String result = tvResult.getText().toString();
        String comma = getString(R.string.calc_btn_comma);
        if (!result.contains(comma)) {
            result += comma;
            tvResult.setText(result);
        }
    }

    private void deleteClick(View view) { // [ backspace ]
        String result = tvResult.getText().toString();
        if( !result.equals( "" ) ){
            result = result.substring(0, result.length() - 1);
            tvResult.setText(result);
        }

    }
    private void minusplusClick(View view) {  // [ +\- ]
        String result = tvResult.getText().toString();

        if( !result.equals( "" ) ) {
            char firstChar = result.charAt(0);
            if (firstChar == '-') {
                result = result.substring(1);
            } else {
                result = "-" + result;
            }
            tvResult.setText(result);
        }
    }
    private void clearClick( View view ) {   // [ C ]
        tvExpression.setText( "" ) ;
        tvResult.setText("");
    }

    private void plusClick(View view) {
        currentExpression="+";
        String result = tvResult.getText().toString();
        tvExpression.setText( result ) ;
        tvResult.setText("");
    }
    private void minusClick(View view) {
        currentExpression="-";
        String result = tvResult.getText().toString();
        tvExpression.setText( result ) ;
        tvResult.setText("");
    }
    private void multiplicationClick(View view) {
        currentExpression="\u2715";
        String result = tvResult.getText().toString();
        tvExpression.setText( result ) ;
        tvResult.setText("");
    }
    private void divideClick(View view) {
        currentExpression="\u00F7";
        String result = tvResult.getText().toString();
        tvExpression.setText( result ) ;
        tvResult.setText("");
    }
    private void squareClick(View view) {
        currentExpression="x\u00B2";
        String result = tvResult.getText().toString();
        tvExpression.setText( result ) ;
        tvResult.setText("");
    }
    private void sqrtClick(View view) {
        currentExpression="\u221A\u2093\u0304";
        String result = tvResult.getText().toString();
        tvExpression.setText( result ) ;
        tvResult.setText("");
    }
    private void percentClick(View view) {
        currentExpression="%";
        String result = tvResult.getText().toString();
        tvExpression.setText( result ) ;
        tvResult.setText("");
    }
    private void inverseClick(View view) {
        currentExpression="\u00B9/\u2093";
        String result = tvResult.getText().toString();
        tvExpression.setText( result ) ;
        tvResult.setText("");
    }

    private void equalsClick(View view) {
        String expression = tvExpression.getText().toString();
        String result = tvResult.getText().toString();
        result = result.replace(",", ".");
        expression = expression.replace(",", ".");

        if( !expression.equals( "" ) ) {

            double num1 = Double.parseDouble(expression);
            double num2 = Double.parseDouble(result);
            double calculatedResult = 0.0;

            switch (currentExpression) {
                case "+":
                    calculatedResult = num1 + num2;
                    break;
                case "-":
                    calculatedResult = num1 - num2;
                    break;
                case "\u2715":
                    calculatedResult = num1 * num2;
                    break;
                case "\u00F7":
                    if (num2 != 0) {
                        calculatedResult = num1 / num2;
                    } else {
                        tvResult.setText("Cannot by zero");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                tvResult.setText("");
                                tvExpression.setText( "" ) ;
                            }
                        }, 1500);
                        return;
                    }
                    break;
                case "x\u00B2":
                    calculatedResult = Math.pow(num1, num2);
                    break;
                case "\u221A\u2093\u0304":
                    if (num1 >= 0) {
                        calculatedResult = Math.pow(num1,1.0/ num2);
                    } else {
                        tvResult.setText("Cannot by minus");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                tvResult.setText("");
                                tvExpression.setText( "" ) ;
                            }
                        }, 1500);
                        return;
                    }
                    break;
                case "%":
                    calculatedResult = (num1 * num2) / 100;
                    break;
                case "\u00B9/\u2093":
                    if (num1 != 0) {
                        calculatedResult = 1 / num1;
                    } else {
                        tvResult.setText("Cannot by zero");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                tvResult.setText("");
                                tvExpression.setText( "" ) ;
                            }
                        }, 1500);
                        return;
                    }
                    break;
            }
            tvResult.setText(String.valueOf(calculatedResult));
            tvExpression.setText("");

        }
    }




    @Override
    protected void onSaveInstanceState( @NonNull Bundle outState ) {
        super.onSaveInstanceState( outState );
        outState.putCharSequence( "expression", tvExpression.getText() );
        outState.putCharSequence( "result", tvResult.getText() );
    }

    @Override
    protected void onRestoreInstanceState( @NonNull Bundle savedInstanceState ) {
        super.onRestoreInstanceState( savedInstanceState );
        tvExpression.setText( savedInstanceState.getCharSequence( "expression" ) );
        tvResult.setText( savedInstanceState.getCharSequence( "result" ) );
    }
}
