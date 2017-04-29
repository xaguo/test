package com.android.test.test;
public class XYZtoBLH {
    double Pi=3.141592653589793;
    public double H,sB,sL;
    public int dB,mB,dL,mL;
    public XYZtoBLH(double X, double Y, double Z, double a, double e1){

        double h,t;
        double eps=1E-10;//设定精度
        double L,B,H,N;//经度，纬度,高程
        L=((Math.abs(X)>=1E-5)?Math.atan(Y/X):0);//判别点落在赤道平面上三四象限上的情况并加以纠正
        if(X==0&&Y!=0)
            L=Pi/2;

        if(X<0)
        {
            L=(Y>=0?L+Pi:L-Pi);
        }
        if((Math.abs(Y)>=1E-5)||(Math.abs(X)>=1E-5))//计算纬度和高程
        {
            B=Math.atan(Z/Math.sqrt(X*X+Y*Y));//设定纬度初始值进行迭代
            H=0;
            do
            {
                N=a/Math.sqrt(1-e1*Math.sin(B)*Math.sin(B));
                h=Math.sqrt(X*X+Y*Y)/Math.cos(B)-N;
                B=Math.atan(Z/(Math.sqrt(X*X+Y*Y)*(1-e1*N/(N+h))));
                t=h-H;
                H=h;
            }while (Math.abs(t)>=eps);//判断精度是否符合要求
        }
        else
        {
            B=0;
            N=a/Math.sqrt(1-e1*Math.sin(B)*Math.sin(B));
            H=Math.sqrt(X*X+Y*Y)/Math.cos(B)-N;
        }

        B+=1E-10;//必要修正，防止出现“秒”情况
        L+=1E-10;//必要修正，防止出现“秒”情况

        this.H=H;
        RadtoDms_B(B);//弧度转化为度分秒形式
        RadtoDms_L(L);

    }
    public void RadtoDms_B(double dRad){
        double dsign=dRad/Math.abs(dRad);
        double dtemp=Math.abs(dRad*180.0/Pi);
        int dDeg=(int)dtemp;
        this.dB=(int)(dsign*dDeg);
        dtemp=dtemp-dDeg;
        dtemp*=60.0;
        int dMinute=(int)dtemp;
        this.mB=dMinute;
        dtemp=dtemp-dMinute;
        double second=dtemp*60.0;
        this.sB=second;

     }
    public void RadtoDms_L(double dRad){
        double dsign=dRad/Math.abs(dRad);
        double dtemp=Math.abs(dRad*180.0/Pi);
        int dDeg=(int)dtemp;
        this.dL=(int)(dsign*dDeg);
        dtemp=dtemp-dDeg;
        dtemp*=60.0;
        int dMinute=(int)dtemp;
        this.mL=dMinute;
        dtemp=dtemp-dMinute;
        double second=dtemp*60.0;
        this.sL=second;
    }

}
