
package tools;

import java.util.Random;

/**
 * Created by wangzz on 2017-02-08.
 */



public class PriorityQueue{

    private int heapQueue[] = new int[100];
    private int elementSize = 0;

    PriorityQueue(){
    }

    private void maxHeapify(){

        int tempElement;
        int maxIndex = 0;
        int childIndex = (maxIndex<<1)+1; // 计算子节点
        while( childIndex < this.elementSize){

            // 比较i节点下左右叶节点的值，取比较大的元素的下标。
            if (childIndex<(elementSize -1) && heapQueue[childIndex] < heapQueue[childIndex+1]) {
                ++childIndex;
            }

            // 将i节点与它的子节点比较，子节点大于父节点时，
            // 将值交换,使父节点的值比子节点大。
            if (heapQueue[maxIndex] < heapQueue[childIndex]){
                tempElement = heapQueue[maxIndex];
                heapQueue[maxIndex] = heapQueue[childIndex];
                heapQueue[childIndex] = tempElement;
            }
            else{
                break;
            }

            maxIndex = childIndex;
            childIndex = (maxIndex<<1)+1;
        }
    }

    int takeMax(){

        if (elementSize < 0)
            return -1;

        int max = this.heapQueue[0];
        --elementSize;
        this.heapQueue[0] = this.heapQueue[elementSize];
        maxHeapify();

        return max;
    }

    int getMax(){

        if (elementSize < 0)
            return -1;

        return this.heapQueue[0];
    }

    boolean increseKey(int location, int newValue){

        if (elementSize < location || heapQueue[location] > newValue)
            return false;

        heapQueue[location] = newValue;

        int parent = (location-1)>>1;
        int temp;
        while(location > 0 && heapQueue[parent] < newValue){

            temp = heapQueue[parent];
            heapQueue[parent] = heapQueue[location];
            heapQueue[location] = temp;

            location = parent; // 取父节点位置
            parent = (location-1)>>1;
        }

        return true;
    }

    boolean inert(int newValue){

        if (heapQueue.length == elementSize)
            return false;

        heapQueue[elementSize] = 0;
        increseKey(elementSize,newValue);
        ++elementSize;

        return true;
    }



    static public void main(String args[]){

        int array[] = {4,3,2,1,23,41,34,1,23,4,32,5,4,4,5,3,11,14};

        PriorityQueue queue= new PriorityQueue();

        Random ra =new Random();
        int value = 0;
        for (int i=0;i<10; ++i){
            value = ra.nextInt(1000);
            System.out.print(value +" ");
            queue.inert(value);
        }

        System.out.print("\n");

        for (int i=0;i<10; ++i){
            value = queue.takeMax();
            System.out.print(value +" ");
        }

        System.out.print("\n");
    }
}