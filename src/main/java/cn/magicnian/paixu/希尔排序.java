package cn.magicnian.paixu;

import java.util.Arrays;

public class 希尔排序 {

    public static void main(String[] args) {
        int arr[] = new int[]{12, 15, 8, 6, 3, 7};
        shellSort(arr, 6);
        System.out.println(Arrays.toString(arr));
    }

    private static void shellSort(int arr[], int len) {
        //初始的增量设置为数组长度的一半，每次按照一半递减
        for (int step = len / 2; step > 0; step /= 2) {
            for (int i = step; i < len; i++) {
                for (int j = i; j >= step; j -= step) {
                    if(arr[j]<arr[j-step]){
                        int tmp = arr[j];
                        arr[j] = arr[j-step];
                        arr[j-step] = tmp;
                    }
                }
            }
        }
    }
}
