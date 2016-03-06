package pl.edu.amu.bawsj.junit;

public class FizzBuzz {
    public static void main(String[] args) {
        for (int i = 0; i<=100;i++) {
            System.out.println(new FizzBuzz().go(i));
        }
    }

    public String go(int i) {
        String result = "";
        if (i % 3 == 0) {
            result += "Fizz";
        }
        if (i % 5 == 0) {
            result += "Buzz";
        }
        if (result == "") {
            result += i;
        }
        return result;
    }
}
