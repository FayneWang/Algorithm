package sort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by wangzz on 2017-02-15.
 */
public class QuickSort {

    static void sort(int array[],int left,int right){

        if (left >= right)
            return;

        int i = left-1;
        int keyValue = array[right];

        int temp;
        for (int l=left; l<right; ++l){

            if (array[l] <= keyValue){

                ++i;
                temp = array[i];
                array[i] = array[l];
                array[l] = temp;
            }
        }

        ++i;
        temp = array[i];
        array[i] = array[right];
        array[right] = temp;

        sort(array,left,i-1);
        sort(array,i+1,right);
    }

    static public void sort2(int array[],int left,int right) {

        if (left >= right)
            return;

        int l = left;
        int r = right;
        int key = array[left];

        while (l < r){

            while (l < r && key <= array[r]) {
                --r;
            }
            array[l] = array[r];

            while (l < r && key >= array[l]) {
                ++l;
            }
            array[r] = array[l];
        }

        array[l] = key;
        if (left < --l)
            sort2(array,left,l);

        if (++r < right)
            sort2(array,r,right);
    }


    static public void sort3(int array[],int left,int right) {

        if (left >= right)
            return;

        int l = left+1;
        int r = right;
        int key = array[left];


        while (true){


            while(array[r] > key)
                --r;

            while(array[l] <= key && l<r)
                ++l;


            if (l >= r)
                break;

            {
                int temp = array[l];
                array[l] = array[r];
                array[r] = temp;
            }

            --r;
        }


        {
            int temp = array[left];
            array[left] = array[r];
            array[r] = temp;
        }

        if (left < --l)
            sort3(array,left,l);

        if (right > ++r)
            sort3(array,r,right);

    }

    public static void sort4(int array[],int left,int right){

        if (left >= right)
            return;

        int l = left;
        int r = right;
        int key = array[left];

        while (l < r){

            while(l<r && array[r] >= key)
                --r;

            if (l<r) {
                array[l] = array[r];
                ++l;
            }

            while(l<r && array[l] <= key)
                ++l;

            if (l<r) {
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


    static private class Range{

        public Range(int l,int r){
            this.left = l;
            this.right = r;
        }

        private int left = 0;
        private int right = 0;

    }


    public static void sort5(int array[]){

        if (array.length <= 0)
            return;

        ArrayList<Range> rangQueue = new ArrayList<Range>(); // 保存被抽样元素分区之后的子分区的范围
        Range current = new Range(0,array.length-1);  // 当前排序分区的范围
        int p = 0;   // 剩余需排序分区的数量

        int l,r,key;
        do {
            l = current.left;
            r = current.right;
            key = array[l];

            // partition
            while (l < r){

                while(l <r && array[r] >= key)
                    --r;

                if (l < r) {
                    array[l] = array[r];
                    ++l;
                }

                while(l <r && array[l] <= key)
                    ++l;

                if (l <r) {
                    array[r] = array[l];
                    ++r;
                }
            }
            array[l] = key;

            if (current.left < --l)
            {
                if (rangQueue.size() == p){
                    rangQueue.add(new Range(current.left, l));
                }
                else
                {
                    Range temp = rangQueue.get(p);
                    temp.left = current.left;
                    temp.right = l;
                }

                ++p;
            }

            if (current.right > ++r)
            {
                if (rangQueue.size() == p){
                    rangQueue.add(new Range(r, current.right));
                }
                else
                {
                    Range temp = rangQueue.get(p);
                    temp.left = r;
                    temp.right = current.right;
                }
                ++p;
            }

            if (p > 0)
            {
                --p;
                Range temp = rangQueue.get(p);
                current.left = temp.left;
                current.right = temp.right;
            }
            else
            {
                break;
            }

        }while (true);

    }

    public static void main(String argv[]){

        int array[] = {5,0,0,0,3,3,3,2,2,2,23,23,23,41,41,41,1,1,1,5,5,4,4,4,32,32,32,11,11,11,14,14,14};

        QuickSort.sort5(array);

        for (int i = 0; i< array.length; ++i)
            System.out.print(array[i] + " ");
    }
}
