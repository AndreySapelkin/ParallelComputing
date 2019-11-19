// Последовательный алгоритм сортировки слиянием

public class MergeSortConsistently {
    public static void mergeSort(int[] A, int left, int right) {
        if (left < right) {
            int half = (left + right) / 2;
            mergeSort(A, left, half); // сортировка первой половины
            mergeSort(A, half + 1, right); // сортировка второй половины
            merge(A, left, half, right); // слияние отсортированных половинок
        }
    }

    public static void merge(int[] Arr, int left, int half, int right) {
        int[] Temp = new int[right - left + 1];
        int i = left, j = half + 1, k = 0;
        while (i <= half || j <= right) {
            if (i > half) Temp[k++] = Arr[j++];
            else if (j > right) Temp[k++] = Arr[i++];
            else if (Arr[i] < Arr[j]) Temp[k++] = Arr[i++];
            else Temp[k++] = Arr[j++];
        }
        for (j = 0; j < (right - left + 1); j++) Arr[left + j] = Temp[j];
    }

}