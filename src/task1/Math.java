package task1;

import annotation.Test;

public class Math {
    @Test(a = 5, b = 6)
    public int sum(int a, int b) {
        return a + b;
    }

    public int subtraction(int a, int b) {
        return a - b;
    }

    @Test(a = 5, b = 6)
    public int multiply(int a, int b) {
        return a * b;
    }
}
