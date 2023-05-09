package sorting.divideAndConquer;

import sorting.AbstractSorting;
import java.util.Arrays;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

    @Override
    public void sort(T[] array, int leftIndex, int rightIndex) {


        if (leftIndex < rightIndex) {
            int mid = (rightIndex + leftIndex) / 2;
            sort(array, leftIndex, mid);
            sort(array, mid + 1, rightIndex);
            merge(array, leftIndex, rightIndex);
        }
    }

    private void merge(T[] array, int left, int right) {

        T[] v = Arrays.copyOf(array, array.length);

        int midIndex = (right + left) / 2;
        int i = left;
        int j = midIndex + 1;
        int k = left;

        while (i <= midIndex && j <= right) {

            if (v[i].compareTo(array[j]) <= 0) {
                array[k] = v[i];
                i++;

            } else {
                array[k] = v[j];
                j++;
            }
            k++;

        }

        while (i <= midIndex) {
            array[k] = v[i];
            i++;
            k++;
        }

        while (j <= right) {
            array[k] = v[j];
            j++;
            k++;
        }
    }
}
