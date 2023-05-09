package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;
import util.Util;

import java.util.Arrays;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * <p>
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 * que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 * interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 * INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
        AbstractSorting<T> {

    /**
     * For inputs with size less or equal to this value, the insertionsort
     * algorithm will be used instead of the mergesort.
     */
    public static final int SIZE_LIMIT = 4;

    protected static int MERGESORT_APPLICATIONS = 0;
    protected static int INSERTIONSORT_APPLICATIONS = 0;

    public void sort(T[] array, int leftIndex, int rightIndex) {

        if (leftIndex < 0 || rightIndex >= array.length)
            return;

        T[] v = Arrays.copyOfRange(array, leftIndex, rightIndex + 1);

        this.MERGESORT_APPLICATIONS = 0;
        this.INSERTIONSORT_APPLICATIONS = 0;

        int x = rightIndex - leftIndex + 1;
        if (x <= this.SIZE_LIMIT) {
            insertionSort(array, leftIndex, rightIndex);
            return;
        }

        int mid = (leftIndex + rightIndex) / 2;
        sort(array, leftIndex, mid);
        sort(array, mid + 1, rightIndex);
        merge(array, leftIndex, rightIndex);
        v = Arrays.copyOfRange(array, leftIndex, rightIndex + 1);
    }

    private void insertionSort(T[] vetor, int left, int right) {
        if (left < 0 || right >= vetor.length)
            return;

        for (int i = 0; i <= right; i++) {

            int j = i;

            while (j > 0 && vetor[j].compareTo(vetor[j - 1]) < 0) {
                Util.swap(vetor, j, j - 1);
                j--;

            }
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





