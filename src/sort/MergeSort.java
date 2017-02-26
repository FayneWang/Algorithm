/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

/**
 *
 * @author wangzz
 */
public class MergeSort {
    
    
    static private <T extends Comparable> void mergeSortTmp(T array[], int first, int end, T tmp[]){

        if(first >= end) {
            return;
        }
        
        int mid = (first+end)>>1;
        mergeSortTmp(array,first,mid,tmp); // left
        mergeSortTmp(array,mid+1,end,tmp); // right
        mergeTmp(array,first,mid,end,tmp);
    }
    
    static private  <T extends Comparable>  void mergeTmp(T array[], int first, int middle, int end, T tmp[])
    {        
        int left,right;
        int leftEnd = middle-first+1; 
        for(left=0; left<leftEnd; ++left) {
            tmp[left] = array[first+left];
        }
        
        int rightEnd = end-first+1;
        for(right=leftEnd; right<rightEnd; ++right) {
            tmp[right] = array[++middle];
        }   
        
        left = 0;
        right = leftEnd;
        while(left<leftEnd && right<rightEnd){
           array[first++] =  tmp[left].compareTo(tmp[right]) >0 ? tmp[left++] : tmp[right++];
        }
        
        while(left < leftEnd){
            array[first++] = tmp[left++];
        }
        
        while(right < rightEnd){
            array[first++] = tmp[right++];
        }
    }    
    
    static public <T extends Comparable> void mergeSort2(T array[]){
        T tmp[] = null;
        mergeSortTmp(array,0,array.length-1,tmp);
    }
    
    static public <T extends Comparable> void mergeSort(T array[]){
        
        int leftMin,leftMax,rightMin,rightMax;
        int next;
        T temp[] = null;// new T[array.length];
        for(int i=1; i<array.length; i*=2){
            
            for(leftMin = 0;leftMin < array.length-i; leftMin = rightMax){
                leftMax = leftMin +i;
                rightMin = leftMax;
                rightMax = leftMax +i;
                
                if(array.length < rightMax) {
                    rightMax = array.length;
                }
                
                for(next = 0;leftMin < leftMax && rightMin<rightMax; ++next) {
                    temp[next] = array[leftMin].compareTo(array[rightMin]) > 0 ? array[leftMin++] : array[rightMin++];
                }
                
                while(leftMin < leftMax){
                    array[--rightMin] = array[--leftMax];
                }
                
                while(next >0){
                    array[--rightMin] = temp[--next];                   
                }
            }
        }
    }  
    
    static public void main(String args[]){
        
        Integer array[] = {4,3,2,1,23,41,34,1,23,4,32,5,4,4,5,3,11,14};
        
        MergeSort.mergeSort2(array);
    }  
}
