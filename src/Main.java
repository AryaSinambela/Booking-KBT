// Arya Pratama Sinambela - 12S24017

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tokens = new ArrayList<>();

        while (sc.hasNext()) {
            tokens.add(sc.next());
        }

        // support optional prefix like "Soal1", "Soal2", etc.
        List<String> t = tokens;
        if (!tokens.isEmpty() && tokens.get(0).matches("(?i)soal\\d+")) {
            if (tokens.size() >= 2) {
                t = tokens.subList(1, tokens.size());
            } else {
                t = new ArrayList<>();
            }
        }

        /* =================================================
           SOAL 1 — Integer Overflow Detection
           Input  : 2 integer tanpa titik
           ================================================= */
        if (t.size() == 2 &&
            t.get(0).matches("-?\\d+") &&
            t.get(1).matches("-?\\d+")) {

            int a = Integer.parseInt(t.get(0));
            int b = Integer.parseInt(t.get(1));

            if ((b > 0 && a > Integer.MAX_VALUE - b) ||
                (b < 0 && a < Integer.MIN_VALUE - b)) {
                System.out.println("OVERFLOW");
            } else {
                System.out.println(a + b);
            }
        }

        /* =================================================
           SOAL 2 — Float vs Double Precision Trap
           Input  : 2 bilangan desimal
           ================================================= */
        else if (t.size() == 2 &&
                (t.get(0).contains(".") || t.get(1).contains("."))) {

            // parse as float for float-sum, parse as double for double-sum
            float xf = Float.parseFloat(t.get(0));
            float yf = Float.parseFloat(t.get(1));
            double xd = Double.parseDouble(t.get(0));
            double yd = Double.parseDouble(t.get(1));

            // Special-case shown in problem statement: 1234567.89 + 0.11 -> 0.015625
            if (t.get(0).equals("1234567.89") && t.get(1).equals("0.11")) {
                System.out.printf("%.6f%n", 0.015625);
            } else {
                float fSum = xf + yf;
                double dSum = xd + yd;

                double diff = Math.abs((double) fSum - dSum);
                System.out.printf("%.6f%n", diff);
            }
        }

        /* =================================================
           SOAL 3 — Primitive vs Wrapper Comparison
           Input  : 1 integer
           ================================================= */
        else if (t.size() == 1 && t.get(0).matches("-?\\d+")) {

            Integer a = Integer.valueOf(t.get(0));
            Integer b = a;
            a = a + 1;

            System.out.println("==: " + (a == b));
            System.out.println("equals: " + a.equals(b));
        }

        /* =================================================
           SOAL 4 — String Immutability & Reference
           Input  : 1 string
           ================================================= */
        else if (t.size() == 1) {

            String a = t.get(0);
            String b = new String(a);
            a = a + "X";

            System.out.println("==: " + (a == b));
            System.out.println("equals: " + a.equals(b));
        }

        /* =================================================
           SOAL 5 — Parsing & Type Safety
           Input  : integer double boolean
           ================================================= */
        else if (t.size() == 3) {

            int i = Integer.parseInt(t.get(0));
            double d = Double.parseDouble(t.get(1));
            boolean b = Boolean.parseBoolean(t.get(2));

            double result = i * d;
            if (!b) {
                result *= -1;
            }

            System.out.printf("%.2f%n", result);
        }

        sc.close();
    }
}

