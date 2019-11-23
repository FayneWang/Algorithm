package com.wangzezhou.sort;

/**
 * Created by FayneWang(83209400@qq.com) on 2017-02-04.
 */
public class HeapSort {

    static private void heapAdjust(int array[], int i, int size){

        int tempElement;
        int childIndex = (i<<1)+1; // 计算子节点
        while( childIndex < size){

            // 比较i节点下左右叶节点的值，取比较大的元素的下标。
            if (childIndex<(size-1) && array[childIndex] < array[childIndex+1]) {
                ++childIndex;
            }

            // 将i节点与它的子节点比较，子节点大于父节点时，
            // 将值交换使父节点的值比子节点大。
            if (array[i] < array[childIndex]){
                tempElement = array[i];
                array[i] = array[childIndex];
                array[childIndex] = tempElement;
            }
            else{
                break;
            }

            i = childIndex;
            childIndex = (i<<1)+1;
        }
    }


    static public void sort(int array[]){

        int i;
        // array.length/2-1 为最后一个非叶节点的下标
        for (i=array.length/2-1; i>=0; --i){ // 构建最大堆
            heapAdjust(array,i,array.length);
        }

        int tempElement;
        for (i=array.length-1;i>0; --i){ // 排序

            // 将堆中的最大值交换到数组最后一个位置，
            // 然后继续堆调整为最大堆
            tempElement = array[0];
            array[0] = array[i];
            array[i] = tempElement;
            heapAdjust(array,0,i);
        }
    }


    static public void main(String args[]){

        int array[] = {4,3,23,2,41,2};

        HeapSort.sort(array);
    }
}
