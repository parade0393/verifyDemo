package foundation.array;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        String[] arr = new String[]{"AA","BB","CC","DD","EE","FF"};
       /* for (int i = 0; i < arr.length/2; i++) {//数组反转方式1
            String tem = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = tem;
        }*/

        for (int i = 0,j = arr.length-1;i<j;i++,j--){//数组反转方式2
            String temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
