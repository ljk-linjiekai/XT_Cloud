package com.xt.lambda.test;

public class LambdaTest {
    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public static void main(String[] args) throws Exception {
        LambdaTest test = new LambdaTest();
        //声明类型
        MathOperation mathOperation = (int a, int b) -> a+b;
        //不需要声明类型
        MathOperation mathOperation2 = (a, b) -> a-b;



    }

}
