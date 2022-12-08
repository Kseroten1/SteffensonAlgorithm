package com.company;

public class Main {

    private static double function(double[] x) {
        return Math.pow(x[0],4)/2 + Math.pow(x[1],2)*x[2] + Math.pow(x[2],4);
    }

    private static double functionForStefanSyn(double k) {
        return Math.pow((xn[0]+ k * gStep[0]),4)/2 + Math.pow((xn[1] + k * gStep[1]),2) * (xn[2] + k * gStep[2]) + Math.pow((xn[2] + k * gStep[2]),4);
    }

    private static double derivativeX0(double[] x) {
        return 2 * Math.pow(x[0],3);
    }

    private static double derivativeX1(double[] x) {
        return 2*x[2]*x[1];
    }

    private static double derivativeX2(double[] x) {
        return (4*Math.pow(x[2],3)) + Math.pow(x[1],2);
    }

    private static double functionEuk(double[] x, double[] xn) {
        return Math.sqrt(Math.pow(xn[0]-x[0], 2) + Math.pow(xn[1]-x[1], 2) + Math.pow(xn[2]-x[2], 2));
    }

    private static double[] gradient(double[] x) {
        return new double[]{-derivativeX0(x), -derivativeX1(x), -derivativeX2(x)};
    }

    private static double stefanSyn(double k) {
        double lam = k;
        double newLam = 0;
        for(int i = 0; i < 1000; i++){
            newLam = lam - (Math.abs(Math.pow(functionForStefanSyn(lam),2)))/(functionForStefanSyn(lam+functionForStefanSyn(lam))-functionForStefanSyn(lam));
            lam = newLam;
            if(Math.abs(newLam-lam) < 0.02){
                break;
            }
        }
        return newLam;
    }

    static double[] x = {1, 1, 1};
    static double[] xn = {1, 1, 1};
    static double[] g;

    public static void main(String[] args) {
        double e = 0.06;
        double k = 1;
        for(int i = 0; i < 1000; i++){
            g = gradient(xn);
            k = stefanSyn(k);
            System.arraycopy(xn, 0, x, 0, x.length);
            for(int j = 0; j < 3; j++){
                xn[j] += k*g[j];
            }
            if(functionEuk(x, xn) < e){
                break;
            }
        }
        for(int i = 0; i < 3; i++){
            System.out.println(xn[i]);
        }
    }
}