package com.android.test.test;
public class BLHtoXYZ {
    double Pi=3.141592653589793;
    public double X,Y,Z;
    public BLHtoXYZ(double B, double L, double H, double a, double e1){
        double _b=DmstoRad(B);double _l=DmstoRad(L);

        double W=Math.sqrt(1-e1*Math.pow(Math.sin(_b),2));
        double N=a/W;

        double X=(N+H)*Math.cos(_b)*Math.cos(_l);
        double Y=(N+H)*Math.cos(_b)*Math.sin(_l);
        double Z=(N*(1-e1)+H)*Math.sin(_b);
        this.X=X;
        this.Y=Y;
        this.Z=Z;



    }
    public double DmstoRad(double dms)//度分秒转化为弧度函数代码
    { double d=(int)dms;
        double m=(dms-d)*100.0;
        double M=(int)m;
        double s=((dms-d)*100.0-M)*100.0;
        return (d+M/60.0+s/3600.0)*Pi/180;}

    public double  getX(){
        return X;
    }
    public double  getY(){
        return Y;
    }
    public double  getZ(){
        return Z;
    }
}
