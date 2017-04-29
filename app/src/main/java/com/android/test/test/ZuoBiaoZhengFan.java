package com.android.test.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telecom.StatusHints;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * Created by 11920 on 2017/4/28.
 */

public class ZuoBiaoZhengFan extends AppCompatActivity {
    private ImageView back;
    private Button btn_cacu,btn_back;
   private RadioGroup radioGroup;
    private EditText et_X1,et_Y1,et_X2,et_Y2,et_Angle,et_S;
    private boolean isZheng=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_one);

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
            ZhengFanJs Js=new ZhengFanJs();
         if(isZheng==true)
         {   if(TextUtils.isEmpty(et_X1.getText())||TextUtils.isEmpty(et_Y1.getText())||TextUtils.isEmpty(et_S.getText())||TextUtils.isEmpty(et_Angle.getText())) {
             AlertDialog.Builder builder=new AlertDialog.Builder(ZuoBiaoZhengFan.this);
             builder.setTitle("提示");
             builder.setMessage("请输入待计算的数据！");
             builder.setPositiveButton("确定",null);
             builder.setNegativeButton("取消",null);
             builder.show();
             return;
             }
             else {
             double X1=Double.valueOf(et_X1.getText().toString());
             double Y1=Double.valueOf(et_Y1.getText().toString());
             double s=Double.valueOf(et_S.getText().toString());
             double angle=Double.valueOf(et_Angle.getText().toString());
             Js.isZhengJs(X1,Y1,angle,s);
             String str;
             str=String.format("%.6f",Js.getX());
             et_X2.setText(str);
             str=String.format("%.6f",Js.getY());
             et_Y2.setText(str);
             }
         }
         else {
             if(TextUtils.isEmpty(et_X1.getText())||TextUtils.isEmpty(et_Y1.getText())||TextUtils.isEmpty(et_X2.getText())||TextUtils.isEmpty(et_Y2.getText())) {
                 AlertDialog.Builder builder=new AlertDialog.Builder(ZuoBiaoZhengFan.this);
                 builder.setTitle("提示");
                 builder.setMessage("请输入待计算的数据！");
                 builder.setPositiveButton("确定",null);
                 builder.setNegativeButton("取消",null);
                 builder.show();
                 return;
             }
             else {
                 double X1 = Double.valueOf(et_X1.getText().toString());
                 double Y1 = Double.valueOf(et_Y1.getText().toString());
                 double X2 = Double.valueOf(et_X2.getText().toString());
                 double Y2 = Double.valueOf(et_Y2.getText().toString());
                 Js.isFanJs(X1, Y1, X2, Y2);
                 String str, strbuid;
                 //str=String.format("%d",Js.getDegree());
                 strbuid = Js.getDegree() + ":";
                 //str=String.format("%d",Js.getMin());
                 strbuid += Js.getMin() + ":";
                 str = String.format("%.2f", Js.getSec());
                 strbuid += str;
                 et_Angle.setText(strbuid);
                 str = String.format("%.6f", Js.getS());
                 et_S.setText(str);
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
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId==R.id.rad_fang)
                {
                    isZheng=false;
                    et_S.setEnabled(false);
                    et_Angle.setEnabled(false);
                    et_S.setBackgroundResource(R.drawable.edit_bg_inable);
                    et_Angle.setBackgroundResource(R.drawable.edit_bg_inable);
                    et_X2.setEnabled(true);
                    et_Y2.setEnabled(true);
                    et_X2.setBackgroundResource(R.drawable.edit_bg);
                    et_Y2.setBackgroundResource(R.drawable.edit_bg );
                    clear();
                }
                else
                    {isZheng=true;
                        et_X2.setEnabled(false);
                        et_Y2.setEnabled(false);
                         et_X2.setBackgroundResource(R.drawable.edit_bg_inable);
                        et_Y2.setBackgroundResource(R.drawable.edit_bg_inable);
                        et_S.setEnabled(true);
                        et_Angle.setEnabled(true);
                        et_S.setBackgroundResource(R.drawable.edit_bg);
                        et_Angle.setBackgroundResource(R.drawable.edit_bg);
                        clear();
                    }
            }
        });

}
    private void initEvent()
    {
        btn_cacu=(Button)findViewById(R.id.btn_cacu);
        btn_back=(Button)findViewById(R.id.btn_back);
        back=(ImageView)findViewById(R.id.back);
        radioGroup=(RadioGroup)findViewById(R.id.radGroup);
        et_X1=(EditText)findViewById(R.id.et_X1);
        et_X2=(EditText)findViewById(R.id.et_X2);
        et_Y1=(EditText)findViewById(R.id.et_Y1);
        et_Y2=(EditText)findViewById(R.id.et_Y2);
        et_Angle=(EditText)findViewById(R.id.et_Angle);
        et_S=(EditText)findViewById(R.id.et_S);
        et_X2.setEnabled(false);
        et_Y2.setEnabled(false);
        et_X2.setBackgroundResource(R.drawable.edit_bg_inable);
        et_Y2.setBackgroundResource(R.drawable.edit_bg_inable);
    }
    private void clear()
    {
        et_X1.setText("");
        et_X2.setText("");
        et_Y1.setText("");
        et_Y2.setText("");
        et_Angle.setText("");
        et_S.setText("");
    }
}

