package com.sample;

public class LargeNumber {

    public static void main(String[] args) {
        String s1 = "99999654654654654321567872315676432468432165761324873216574135468432146571324876132";
        String s2 = "9991657845478533512165413246543213254654321234687987654566545";

        int diff = s1.length() > s2.length() ? s1.length() - s2.length() : s2.length() - s1.length();

        if (diff > 0) {
            String tString = "";
            for (int i = 0; i < diff; i++) {
                tString += "0";
            }
            if (s1.length() > s2.length()) {
                s2 = tString + s2;
            } else {
                s1 = tString + s1;
            }
        }

        char[] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();
        int carry = 0;

        StringBuilder sum = new StringBuilder(0);
        for (int i = s1Arr.length - 1; i > -1; i--) {
            int n1 = Character.getNumericValue(s1Arr[i]);
            int n2 = Character.getNumericValue(s2Arr[i]);
            int rem = (carry + n1 + n2) % 10;
            carry = (carry + n1 + n2) / 10;
            sum.append(rem);
        }
        String cary = (carry > 0) ? "1" : "";
        System.out.println(cary + sum.reverse());
    }
}
