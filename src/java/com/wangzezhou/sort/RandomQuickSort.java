package com.wangzezhou.sort;

import java.util.ArrayDeque;

/**
 * Created by wangzz on 2017-02-15.
 */
public class RandomQuickSort {

    private static void sort(int array[], int left, int right){

        if (left >= right)
            return;

        int i = (int)(Math.random() * (right-left+1)) + left; // 随机抽样

        // 将抽样元素移到分区的最右端
        int keyValue = array[i];
        array[i] = array[right];

        int temp;
        i = left-1; // 小于抽样元素的末尾下标
        for (int l=left; l<right; ++l){ // l 为大于抽样元素的末尾下标

            if (array[l] <= keyValue){
                ++i;
                temp = array[i];
                array[i] = array[l];
                array[l] = temp;
            }

        }

        ++i;
        array[right] = array[i];
        array[i] = keyValue; // 将抽样元素移到分区的中间

        sort(array,left,i-1);
        sort(array,i+1,right);
    }

    static public void sort2(int array[],int left,int right) {

        if (left >= right)
            return;

        // 获取随机抽样的下标
        int key = (int)(Math.random() * (right-left+1)) + left;


        // 临时保存随机抽样元素
        int l = array[key];

        // 将最左端的元素替换到随机抽样的元素的下标位置。
        array[key] = array[left];

        key = l; // 保存随机抽样元素

        // 原最左端的元素已被移到随机抽样元素的下标位置，所以最左端的l下标可以作为临时的替换空间。
        // l 下标总是作为每轮迭代的抽样元素值左边（小于等于抽样元素）的排序位置。
        l = left;
        int r = right; // r 下标总是作为每轮迭代抽样元素右边（大于等于抽样元素）的排序位置。
        while (l<r){

            while (l < r && key <= array[r]) {
                --r;
            }
            array[l] = array[r]; // 循环第n+1次时的该l下标元素已被移到上一次（第n次）的r下标位置，所以此时l位置可以作为当前r下标元素
                                 // 的排序位置。而此时的r下标将作为下面第2个子循环存储 l 下标元素。

            while (l < r && key >= array[l]) {
                ++l;
            }
            array[r] = array[l];
        }

        array[l] = key; // 循环结束后，第l下标为抽样元素的下标位置。因为 小于等于l位置的元素比抽样元素值小，大于等于r位置的元素比抽样元素值大。
        if (left < --l)
            sort2(array,left,l);

        if (++r < right)
            sort2(array,r,right);
    }

    static public void sort3(int array[],int left,int right) {

        if (left >= right)
            return;

        // 获取随机抽样的下标
        int key = (int)(Math.random() * (right-left+1)) + left;

        // 将随机抽样替换到最左边
        int temp = array[key];
        array[key] = array[left];
        array[left] = temp;
        key = temp;

        int l = left+1;
        int r = right;
        while (true){

            while(array[r] > key) {
                --r;
            }

            while(array[l] <= key && l<r) {
                ++l;
            }

            if (l >= r) {
                break;
            }

            temp = array[l];
            array[l] = array[r];
            array[r] = temp;

            --r;
        }

        temp = array[left];
        array[left] = array[r];
        array[r] = temp;

        if (left < --l)
            sort3(array,left,l);

        if (right > ++r)
            sort3(array,r,right);
    }

    public static void sort4(int array[],int left,int right){

        if (left >= right)
            return;

        // 获取随机抽样的下标
        int key = (int)(Math.random() * (right-left+1)) + left;

        // 临时保存随机抽样元素
        int l = array[key];

        // 将最左端的元素替换到随机抽样的元素的下标位置。
        array[key] = array[left];

        key = l; // 保存随机抽样元素

        l = left;
        int r = right;

        while (l < r){

            while(l < r && array[r] >= key) {
                --r;
            }
            if (l < r) {
                array[l] = array[r];
                ++l;
            }

            while(l < r && array[l] <= key) {
                ++l;
            }
            if (l < r) {
                array[r] = array[l];
                ++r;
            }
        }

        array[l] = key;

        if (left < --l)
            sort4(array,left,l);

        if (right > ++r)
            sort4(array,r,right);
    }

    private class Range{
        private int left = 0;
        private int right = 0;
    }

    public static void sort5(int array[]){

        if (array.length <= 0)
            return;

        int p = 0;
        ArrayDeque<Range> rangs = new ArrayDeque<Range>();



        Range rangeArray[] = new Range[array.length];
        rangeArray[p].left = 0;
        rangeArray[p].right = array.length -1;


        int left = 0,right = 0;
        // 获取随机抽样的下标
        int key = (int)(Math.random() * (right-left+1)) + left;

        // 临时保存随机抽样元素
        int l = array[key];

        // 将最左端的元素替换到随机抽样的元素的下标位置。
        array[key] = array[left];

        key = l; // 保存随机抽样元素

        l = left;
        int r = right;

        while (l < r){

            while(l < r && array[r] >= key) {
                --r;
            }
            if (l < r) {
                array[l] = array[r];
                ++l;
            }

            while(l < r && array[l] <= key) {
                ++l;
            }
            if (l < r) {
                array[r] = array[l];
                ++r;
            }
        }

        array[l] = key;

        if (left < --l)
            sort4(array,left,l);

        if (right > ++r)
            sort4(array,r,right);

    }

    public static void main(String argv[]){

        int array[] = {5,0,0,0,3,3,3,2,2,2,23,23,23,41,41,41,1,1,1,5,5,4,4,4,32,32,32,11,11,11,14,14,14};

        RandomQuickSort.sort4(array,0,array.length-1);

        for (int i = 0; i< array.length; ++i)
            System.out.print(array[i] + " ");

    }
}
