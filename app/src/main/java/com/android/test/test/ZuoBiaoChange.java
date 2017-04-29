package com.android.test.test;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by 11920 on 2017/4/28.
 */

public class ZuoBiaoChange extends AppCompatActivity {
    private ImageView back;
    private Button btn_cacu,btn_back;
    private Spinner spinner;
    private int SpinnerPosition=0;
    private RadioGroup radioGroup;
    private boolean xyz2blh=true;
    private TextView tv_in1,tv_in2,tv_in3,tv_out1,tv_out2,tv_out3;
    private EditText et_in1,et_in2,et_in3,et_out1,et_out2,et_out3;
    //椭球参数数组
    public double[] a={6378245.0000,6378140.0000,6378137.0000,6378137.0000};
    public double[] e1={0.006693421622966,0.00669438499959,0.00669437999013,0.00669438002290};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_two);

        initEvent();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_cacu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(TextUtils.isEmpty(et_in1.getText())||TextUtils.isEmpty(et_in2.getText())||TextUtils.isEmpty(et_in3.getText()))
            {
                AlertDialog.Builder builder=new AlertDialog.Builder(ZuoBiaoChange.this);
                builder.setTitle("提示");
                builder.setMessage("请输入要转换的坐标数据！");
                builder.setPositiveButton("确定",null);
                builder.setNegativeButton("取消",null);
                builder.show();
                return;
            }
            else {
                if(xyz2blh==true){
                    double x = Double.valueOf(et_in1.getText().toString());
                    double y = Double.valueOf(et_in2.getText().toString());
                    double z = Double.valueOf(et_in3.getText().toString());
                    XYZtoBLH xyz2blh = new XYZtoBLH(x, y, z, a[SpinnerPosition], e1[SpinnerPosition]);
                    String str,strbuid;
                    strbuid=xyz2blh.dB+":"+xyz2blh.mB+":";
                    str=String.format("%.2f",xyz2blh.sB);
                    strbuid+=str;
                    et_out1.setText(strbuid);
                    strbuid=xyz2blh.dL+":"+xyz2blh.mL+":";
                    str=String.format("%.2f",xyz2blh.sL);
                    strbuid+=str;
                    et_out2.setText(strbuid);
                    str= String.format("%.6f",xyz2blh.H);
                    et_out3.setText(str);
                }
                else {
                    double B = Double.valueOf(et_in1.getText().toString());
                    double L = Double.valueOf(et_in2.getText().toString());
                    double H = Double.valueOf(et_in3.getText().toString());
                    BLHtoXYZ blh2xyz = new BLHtoXYZ(B, L, H, a[SpinnerPosition], e1[SpinnerPosition]);
                    String str;
                    str=String.format("%.6f",blh2xyz.getX());
                    et_out1.setText(str);
                    str= String.format("%.6f",blh2xyz.getY());
                    et_out2.setText(str);
                    str= String.format("%.6f",blh2xyz.getZ());
                    et_out3.setText(str);
                }
            }
        }
    });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    SpinnerPosition=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                clear();
                if(checkedId==R.id.rad_blh2xyz){
                    xyz2blh=false;
                    tv_in1.setText("B");
                    tv_in2.setText("L");
                    tv_in3.setText("H");
                    tv_out1.setText("X");
                    tv_out2.setText("Y");
                    tv_out3.setText("Z");

                }
                else {
                    xyz2blh=true;
                    tv_in1.setText("X");
                    tv_in2.setText("Y");
                    tv_in3.setText("Z");
                    tv_out1.setText("B");
                    tv_out2.setText("L");
                    tv_out3.setText("H");
                }
            }
        });
    }

   private void initEvent()
   {
       btn_cacu=(Button)findViewById(R.id.btn_cacu);
       btn_back=(Button)findViewById(R.id.btn_back);
       back=(ImageView)findViewById(R.id.back);
       spinner=(Spinner)findViewById(R.id.Spinner);
       radioGroup=(RadioGroup)findViewById(R.id.radGroup);
       et_in1=(EditText)findViewById(R.id.et_intput1);
       et_in2=(EditText)findViewById(R.id.et_intput2);
       et_in3=(EditText)findViewById(R.id.et_intput3);
       et_out1=(EditText)findViewById(R.id.et_output1);
       et_out2=(EditText)findViewById(R.id.et_output2);
       et_out3=(EditText)findViewById(R.id.et_output3);
       tv_in1=(TextView)findViewById(R.id.tv_input1);
       tv_in2=(TextView)findViewById(R.id.tv_input2);
       tv_in3=(TextView)findViewById(R.id.tv_input3);
       tv_out1=(TextView)findViewById(R.id.tv_output1);
       tv_out2=(TextView)findViewById(R.id.tv_output2);
       tv_out3=(TextView)findViewById(R.id.tv_output3);
   }
   private void clear()
   {
       et_in1.setText("");
       et_in2.setText("");
       et_in3.setText("");
       et_out1.setText("");
       et_out2.setText("");
       et_out3.setText("");
   }
}
