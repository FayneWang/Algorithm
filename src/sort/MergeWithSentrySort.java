/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import java.lang.Integer;

/**
 *
 * @author wangzz
 */
public class MergeWithSentrySort {
    
    static public void MergeSort(int array[],int first,int end)
    {
        if(first >= end) {
            return;
        }
        
        int mid = (first+end)>>1;
        MergeSort(array,first,mid); // left
        MergeSort(array,mid+1,end); // right
        merge(array,first,mid,end);
    }
    
    static private void merge(int array[], int first, int middle, int end)
    {
        int n1 = middle-first+1;
        int n2 = end-middle;
        
        int[] left = new int[n1+1];
        int[] right = new int[n2+1];
        
        int i,j;
        for(i=0; i<n1; ++i) {
            left[i] = array[first+i];
        }
        
        for(j=0; j<n2; ++j) {
            right[j] = array[middle+j+1];
        }
        
        left[n1] = Integer.MAX_VALUE;
        right[n2] = Integer.MAX_VALUE;
        
        i=j=0;        
        for(int k=first;k<=end; ++k){
            if(left[i] > right[j]){
                array[k] = right[j];
                j+=1;
            }
            else{
                array[k] = left[i];
                i+=1;
            }
        }
    }
    
    static private void mergeSortTmp(int array[], int first, int end, int tmp[])
    {
        if(first >= end) {
            return;
        }
        
        int mid = (first+end)>>1;
        mergeSortTmp(array,first,mid,tmp); // left
        mergeSortTmp(array,mid+1,end,tmp); // right
        mergeTmp(array,first,mid,end,tmp);
    }
    
    static private void mergeTmp(int array[], int first, int middle, int end, int tmp[])
    {
        int i,j;
        int leftEnd = middle-first+1; 
        for(i=0; i<leftEnd; ++i) {
            tmp[i] = array[first+i];
        }
        tmp[leftEnd] = Integer.MAX_VALUE;
        
        int rightEnd = end-first+2; // 2为 n1的MAX_VALUE和n2的MAX_VALUE两个元素。
        for(j=leftEnd+1,++middle; j<rightEnd; ++j,++middle) {
            tmp[j] = array[middle];
        }
        tmp[rightEnd] = Integer.MAX_VALUE;
        
        i = 0;
        j=leftEnd+1;
        for(int k=first;k<=end; ++k){
            if(tmp[i] > tmp[j]){
                array[k] = tmp[j];
                j+=1;
            }
            else{
                array[k] = tmp[i];
                i+=1;
            }
        }
    }    
    
    static public void mergeSort2(int array[], int first, int end){
        int tmp[] = new int[array.length+2];
        mergeSortTmp(array,first,end,tmp);
    }
      
    
    static public void main(String args[]){
        
        int array[] = {4,3,2,1,23,41,34,1,23,4,32,5,4,4,5,3,11,14};
        
        MergeWithSentrySort.mergeSort2(array,0,array.length-1);
        
    }    
}
