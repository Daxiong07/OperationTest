package com.example.administrator.arithmetictest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.administrator.arithmetictest.ArithmeticUtils.add;
import static com.example.administrator.arithmetictest.ArithmeticUtils.div;
import static com.example.administrator.arithmetictest.ArithmeticUtils.mul;
import static com.example.administrator.arithmetictest.ArithmeticUtils.sub;

public class MainActivity extends AppCompatActivity {
    private EditText mFirstNumber;
    private EditText mSecondNumber;
    private TextView mResult;
    private Button mAdd;
    private Button mSub;
    private Button mMul;
    private Button mDiv;
    private Button mClear;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirstNumber = (EditText) findViewById(R.id.first_integer);
        mSecondNumber = (EditText) findViewById(R.id.second_integer);
        mResult = (TextView) findViewById(R.id.result);
        mAdd = (Button) findViewById(R.id.add);
        mSub = (Button) findViewById(R.id.sub);
        mMul = (Button) findViewById(R.id.mul);
        mDiv = (Button) findViewById(R.id.div);
        mClear = (Button) findViewById(R.id.clear);
        mResult.setMovementMethod(new ScrollingMovementMethod());
        resultCompare();
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNum = mFirstNumber.getText().toString();
                String secondNum = mSecondNumber.getText().toString();
                String addResult = add(firstNum, secondNum);
                mResult.setText(addResult);
            }
        });
        mSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNum = mFirstNumber.getText().toString();
                String secondNum = mSecondNumber.getText().toString();
                String subResult = sub(firstNum, secondNum);
                mResult.setText(subResult);

            }
        });
        mMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNum = mFirstNumber.getText().toString();
                String secondNum = mSecondNumber.getText().toString();
                String mulResult = mul(firstNum, secondNum);
                mResult.setText(mulResult);
            }
        });
        mDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNum = mFirstNumber.getText().toString();
                String secondNum = mSecondNumber.getText().toString();
                String divResult = div(firstNum, secondNum);
                mResult.setText(divResult);
            }
        });
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirstNumber.setText("");
                mSecondNumber.setText("");
            }
        });

    }

    /**
     * 测试
     */
    private void resultCompare() {
        if (add("11223344556677889900", "234567890")
                .equals("11223344556912457790")) {
            Log.d(TAG, "resultCompare: " + "true");
        } else {
            Log.d(TAG, "resultCompare: " + "false");
        }
        if (add("-346758418573485734545451243455", "437584937589435")
                .equals("-346758418573485296960513654020")) {
            Log.d(TAG, "resultCompare: " + "true");
        } else {
            Log.d(TAG, "resultCompare: " + "false");
        }
        if (sub("-55555", "-55555").equals("0")) {
            Log.d(TAG, "resultCompare: " + "true");
        } else {
            Log.d(TAG, "resultCompare: " + "false");
        }
        if (sub("234567", "123456").equals("111111")) {
            Log.d(TAG, "resultCompare: " + "true");
        } else {
            Log.d(TAG, "resultCompare: " + "false");
        }
        if (sub("0050", "070").equals("-20")) {
            Log.d(TAG, "resultCompare: " + "true");
        } else {
            Log.d(TAG, "resultCompare: " + "false");
        }
        if (sub("-40", "20").equals("-60")) {
            Log.d(TAG, "resultCompare: " + "true");
        } else {
            Log.d(TAG, "resultCompare: " + "false");
        }
        if (mul("3344", "-5566").equals("-18612704")) {
            Log.d(TAG, "resultCompare: " + "true");
        } else {
            Log.d(TAG, "resultCompare: " + "false");
        }
        if (add("34254543", "0").equals("34254543")) {
            Log.d(TAG, "resultCompare: " + "true");
        } else {
            Log.d(TAG, "resultCompare: " + "false");
        }
        if (div("56464", "0").equals("除数不能为0")) {
            Log.d(TAG, "resultCompare: " + "true");
        } else {
            Log.d(TAG, "resultCompare: " + "false");
        }
        if (div("50", "3")
                .equals("16")) {
            Log.d(TAG, "resultCompare: " + "true");
        } else {
            Log.d(TAG, "resultCompare: " + "false");
        }
        if (div("500", "50")
                .equals("10")) {
            Log.d(TAG, "resultCompare: " + "true");
        } else {
            Log.d(TAG, "resultCompare: " + "false");
        }
    }
}
