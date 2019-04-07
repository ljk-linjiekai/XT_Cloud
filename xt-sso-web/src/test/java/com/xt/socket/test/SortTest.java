package com.xt.socket.test;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;

public class SortTest {

    public static final int a[] = {3, 1, 5, 7, 2, 4, 9, 6, 10, 8};

    public static void main(String[] args) throws Exception {

    }

    /**
     * 简单选择排序
     *
     * @param v
     */
    public void selectSort(View v) {
        int min;
        for (int i = 0; i < a.length; i++) {
            min = i;
            for (int j = i + 1; j < a.length; j++) {//找到最小值下标
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
    }

    /**
     * 堆排序
     *
     * @param v
     */
    public void heapSort(View v) {
        for (int i = 0; i < a.length; i++) {
            createMaxHeap(a, a.length - 1 - i);
            swap(a, 0, a.length - 1 - i);
        }
    }

    public void createMaxHeap(int[] data, int lastIndex) {
        // 从lastIndex处节点（最后一个节点）的父节点开始
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            // 保存当前正在判断的节点
            int k = i;
            // 如果当前k节点的子节点存在
            while (2 * k + 1 <= lastIndex) {
                // biggerIndex总是记录较大节点的值,先赋值为当前节点的左子节点的索引
                int biggerIndex = 2 * k + 1;
                // 若当前节点的右子节点存在
                if (biggerIndex + 1 <= lastIndex) {
                    if (data[biggerIndex] < data[biggerIndex + 1]) {
                        // 若右子节点值比左子节点值大，则biggerIndex记录的是右子节点的索引
                        biggerIndex++;
                    }
                }
                // 如果k节点的值小于其较大的子节点的值
                if (data[k] < data[biggerIndex]) {
                    // 交换两者的值
                    swap(data, k, biggerIndex);
                    // 将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 冒泡排序
     *
     * @param v
     */
    public void bubbleSort(View v) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                // 每遍历一次都把最大的数沉到最底下去了
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                }
            }
        }
    }

    /**
     * 冒泡排序改进
     *
     * @param v
     */
    public void bubbleSort2(View v) {
        int low = 0;
        int high = a.length - 1; //设置变量的初始值
        int i;
        while (low < high) {
            for (i = low; i < high; i++) //正向冒泡,找到最大者
                if (a[i] > a[i + 1]) {
                    swap(a, i, i + 1);
                }
            --high;//修改high值, 前移一位
            for (i = high; i > low; i--) //反向冒泡,找到最小者
                if (a[i] < a[i - 1]) {
                    swap(a, i, i - 1);
                }
            ++low;//修改low值,后移一位
        }
    }

    /**
     * 快速排序
     *
     * @param
     */
    public void quickSort(View v) {
        quickSort(a, 0, a.length - 1);
    }

    private void quickSort(int[] a, int low, int high) {
        if (low < high) { //如果不加这个判断递归会无法退出导致堆栈溢出异常
            int middle = getMiddle(a, low, high);
            quickSort(a, 0, middle - 1);  //递归对低子表递归排序
            quickSort(a, middle + 1, high);  //递归对高子表递归排序
        }
    }
    public int getMiddle(int[] a, int low, int high) {
        int key = a[low];//基准元素，排序中会空出来一个位置
        while (low < high) {
            while (low < high && a[high] >= key) {//从high开始找比基准小的，与low换位置
                high--;
            }
            a[low] = a[high];
            while (low < high && a[low] <= key) {//从low开始找比基准大,放到之前high空出来的位置上
                low++;
            }
            a[high] = a[low];
        }
        a[low] = key;//此时low=high 是基准元素的位置，也是空出来的那个位置
        return low;
    }

    /**
     * 快速排序改进
     *
     * @param
     */
    public void quickSort2(View v) {
        quickSort2(a, 0, a.length - 1, 8);//先调用改进算法Qsort使之基本有序,k=8
        //再用插入排序对基本有序序列排序
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            int j;
            for (j = i - 1; j >= 0 && a[j] > temp; j--) {
                // 将大于temp的往后移动一位
                a[j + 1] = a[j];
            }
            a[j + 1] = temp;
        }
    }

    private void quickSort2(int[] a, int low, int high, int k) {
        if (high - low > k) { //长度大于k时递归, k为指定的数
            int pivot = partition(a, low, high); // 调用的Partition算法保持不变
            quickSort2(a, low, pivot - 1, k);
            quickSort2(a, pivot + 1, high, k);
        }
    }

    private int partition(int a[], int low, int high) {
        int privotKey = a[low]; //基准元素
        while (low < high) {   //从表的两端交替地向中间扫描
            while (low < high && a[high] >= privotKey) { //从high 所指位置向前搜索，至多到low+1 位置。将比基准元素小的交换到低端
                high--;
            }
            swap(a, low, high);
            while (low < high && a[low] <= privotKey) {
                low++;
            }
            swap(a, low, high);
        }
        return low;
    }

    /**
     * 归并排序
     *
     * @param
     */
    public void mergeSort(View v) {
        mergeSort(a, 0, a.length - 1);
    }

    public void mergeSort(int[] a, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            // 左边
            mergeSort(a, low, mid);
            // 右边
            mergeSort(a, mid + 1, high);
            // 左右归并
            merge(a, low, mid, high);
        }
    }

    public void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;// 左指针
        int j = mid + 1;// 右指针
        int k = 0;
        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = a[j++];
        }
        // 把新数组中的数覆盖a数组
        for (int k2 = 0; k2 < temp.length; k2++) {
            a[low + k2] = temp[k2];
        }
    }

    /**
     * 桶排序/基数排序
     *
     * @param
     */
    public void radixSort(View v) {
        // 找到最大数，确定要排序几趟
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }
        // 判断位数
        int times = 0;
        while (max > 0) {
            max = max / 10;
            times++;
        }
        // 建立十个队列
        List<ArrayList> queue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArrayList queue1 = new ArrayList();
            queue.add(queue1);
        }
        // 进行times次分配和收集
        for (int i = 0; i < times; i++) {
            // 分配
            for (int j = 0; j < a.length; j++) {
                int x = a[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                ArrayList queue2 = queue.get(x);
                queue2.add(a[j]);
                queue.set(x, queue2);
            }
            // 收集
            int count = 0;
            for (int j = 0; j < 10; j++) {
                while (queue.get(j).size() > 0) {
                    ArrayList<Integer> queue3 = queue.get(j);
                    a[count] = queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }
        }
    }

    /**
     * 元素交换
     *
     * @param
     */
    public void swap(int[] data, int i, int j) {
        if (i == j) {
            return;
        }
        data[i] = data[i] + data[j];
        data[j] = data[i] - data[j];
        data[i] = data[i] - data[j];
    }

}
