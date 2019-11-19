import java.util.Arrays;
import java.util.Random;

public class Test {
    public static int n = 2048000;
    public static int A[] = new int[n];

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < A.length; i++) {
            A[i] = new Random().nextInt() % 20 + 20;
        }

        //System.out.println("Basis array: " + Arrays.toString(A));

        //сортировка слиянием
        long startTime1 = System.nanoTime();
        MergeSortConsistently.mergeSort(A, 0, A.length - 1);
        long finishTime1 = System.nanoTime();
        long Time1 = finishTime1 - startTime1;
        //System.out.println("Marge sort: " + Arrays.toString(A));
        System.out.println("Time MergeSortConsistently: " + Time1);

        // сортировка слиянием для случая 2х потоков
        long startTime2 = System.nanoTime();
        sort1();
        long endTime2 = System.nanoTime();
        long Time2 = endTime2 - startTime2;
        System.out.println("Time MergeSortParallel by 2Thread : " + Time2);

        // сортировка слиянием для случая 4х потоков
        long startTime3 = System.nanoTime();
        sort2();
        long endTime3 = System.nanoTime();
        long Time3 = endTime3 - startTime3;
        System.out.println("Time MergeSortParallel by 4Thread: " + Time3);
    }
    /***********************************************************************/
    //сортировка слиянием для случая 2х потоков
    public static class Thread1 extends Thread {
        public void run() {
            int lenght = A.length / 2;
            MergeSortConsistently.mergeSort(A, 0, lenght);
        }
    }

    public static class Thread2 extends Thread {
        public void run() {
            int lenght = A.length / 2 + 1;
            MergeSortConsistently.mergeSort(A, lenght, A.length - 1);
        }
    }

    public static void sort1() throws Exception {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        MergeSortConsistently.merge(A, 0, A.length / 2, A.length - 1);
    }

    //для случая 4х потоков
    public static class Thread3 extends Thread {
        public void run() {
            int lenght = A.length / 4;
            MergeSortConsistently.mergeSort(A, 0, lenght);
        }
    }

    public static class Thread4 extends Thread {
        public void run() {
            int lenght = A.length / 4 + 1;
            MergeSortConsistently.mergeSort(A, lenght, A.length / 2);
        }
    }

    public static class Thread5 extends Thread {
        public void run() {
            int lenght = A.length / 2 + 1;
            MergeSortConsistently.mergeSort(A, lenght, A.length * 3 / 4);
        }
    }

    public static class Thread6 extends Thread {
        public void run() {
            int lenght = A.length * 3 / 4 + 1;
            MergeSortConsistently.mergeSort(A, lenght, A.length - 1);
        }
    }

    public static void sort2() throws Exception {
        Thread3 t3 = new Thread3();
        Thread4 t4 = new Thread4();
        Thread5 t5 = new Thread5();
        Thread6 t6 = new Thread6();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
        MergeSortConsistently.merge(A, 0, A.length / 2, A.length - 1);
    }
    /******************************************************************************************/
}