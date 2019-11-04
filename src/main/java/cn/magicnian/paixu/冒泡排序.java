package cn.magicnian.paixu;

import java.util.Arrays;

public class 冒泡排序 {

    public static void main(String[] args) {
        int[] arr = new int[]{12, 15, 8, 6, 3, 7};
        bubbleSort(arr, 6);
        System.out.println(Arrays.toString(arr));
    }

    private static void bubbleSort(int arr[], int len) {
        //i控制循环次数，长度为len的数组只需要循环len-1次，i的起始值为0,所以i<len-1
        for (int i = 0; i < len - 1; i++) {
            //j控制比较次数，第i次循环内需要比较len-i次
            //但是由于是arr[j]和arr[j+1]进行比较，为了保证arr[j+1]不越界，j<len-i-1
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }
}
