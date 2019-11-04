package cn.magicnian.paixu;

import java.util.Arrays;

public class 直接插入排序 {

    public static void main(String[] args) {

        int arr[] = new int[]{7, 5, 3, 4, 1};
        insertSort(arr, 5);
        System.out.println(Arrays.toString(arr));
    }

    private static void insertSort(int arr[], int len) {
        for (int i = 0; i < len - 1; i++) {
            for (int j = i; j >= 0; j--) {
                if (arr[j + 1] < arr[j]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }
}
