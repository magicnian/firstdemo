package cn.magicnian.paixu;

import java.util.Arrays;

public class 选择排序 {

    public static void main(String[] args) {
        int arr[] = new int[]{12, 15, 8, 6, 3, 7};
        selectSort(arr,6);
        System.out.println(Arrays.toString(arr));

    }

    private static void selectSort(int arr[],int len){
        for(int i=0;i<len;i++){
            int minIndex = i;
            for(int j=i+1;j<len;j++){
                if(arr[j]<arr[minIndex]){
                    minIndex=j;
                }
            }

            if(minIndex!=i){
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
        }
    }
}
