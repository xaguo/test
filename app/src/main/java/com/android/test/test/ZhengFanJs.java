package com.android.test.test;

/**
 * Created by 11920 on 2017/4/29.
 */

public class ZhengFanJs {
    public double x,y,angle,s;
    public int degree,min;
    public double sec;
    static double  Pi=3.141592653589793;
    public void isZhengJs(double x,double y,double angle,double s){
     angle=DmstoRad(angle);
        this.x=x+s*Math.cos(angle);
        this.y=y+s*Math.sin(angle);
    }
    public void isFanJs(double x,double y,double X,double Y){
        this.s=Math.sqrt(Math.pow((X-x),2)+Math.pow((Y-y),2));
        if(X!=x) {
            double A = (Y - y) / (X - x);
            if (A >= 0) {
                if (X - x > 0 ) {
                    this.angle = Math.atan(A);
                }
                if (X-x<0){
                    this.angle=Pi+Math.atan(A);
                }
            }
            if (A<0){
                if (X-x<0){
                    this.angle=Pi+Math.atan(A);
                }
                if (X-x>0){
                    this.angle=2*Pi+Math.atan(A);
                }
            }
        }
        if (X==x){
            if(Y-y>0){
                this.angle=Pi/2;
            }
            else if (Y-y<0){
                this.angle=1.5*Pi;
            }
            else {
                this.angle=2*Pi;
            }
        }
        double d=180/Pi*this.angle;
        this.degree=(int)(d);
        double m=(d-this.degree)*60;
        this.min=(int)(m);
        this.sec=(m-this.min)*60;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public int getDegree(){
        return this.degree;
    }
    public int getMin(){
        return this.min;
    }
    public double getSec(){
        return this.sec;
    }
    public double getS(){
        return this.s;
    }
    private double DmstoRad(double dms)//度分秒转化为弧度函数代码
    { double d=(int)dms;
        double m=(dms-d)*100.0;
        double M=(int)m;
        double s=((dms-d)*100.0-M)*100.0;
        return (d+M/60.0+s/3600.0)*Pi/180;}
}
