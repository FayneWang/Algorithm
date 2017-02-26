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
public final class InsertionSort{
    
    static public <T extends Comparable> void Sort(T []array,boolean asc){
        
        int j = 1;
        int i;
        T key;

        while(j<array.length){
            key = array[j];
            i = j-1;
            while(i>=0 && (asc ? key.compareTo(array[i]) < 0 : key.compareTo(array[i]) > 0)){
                array[i+1] = array[i]; // 向后移元素
                --i;                
            }
            
            array[i+1] = key;
            ++j;
        }
    }
    
    static public void main(String args[]){
        Integer integerArray[] = new Integer[5];
        for(int i=0; i<integerArray.length; ++i){
            integerArray[i] = i;
            System.out.print(integerArray[i]+" ");
        }
        
        System.out.print("\n");
        InsertionSort.Sort(integerArray,false);
        
        for(int i=0;i<integerArray.length;++i){
            
            System.out.print(integerArray[i]+" ");
            
        }            
    }    
}
