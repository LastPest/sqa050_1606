package org.vtb;

public enum CalcSumData {

    dataset1(5, 8, 40),
    dataset2(2, 7, 14),
    dataset3(0, 10, 0);

    //fields
    int a;
    int b;
    int result;

    //constructor
    CalcSumData(int a, int b, int result) {
        this.a = a;
        this.b = b;
        this.result = result;
    }

    //getters
    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getResult() {
        return result;
    }
}
