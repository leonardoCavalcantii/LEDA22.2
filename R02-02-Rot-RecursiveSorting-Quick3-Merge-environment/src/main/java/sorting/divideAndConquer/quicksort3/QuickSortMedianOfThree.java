package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import util.Util;

import java.util.Arrays;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
        AbstractSorting<T> {

    public void sort(T[] array, int leftIndex, int rightIndex) {

        if (leftIndex >= rightIndex)
            return;

        T[] v = (T[]) new Comparable[3];
        v[0] = array[leftIndex];
        int mid = (leftIndex + rightIndex) / 2;
        v[1] = array[mid];
        v[2] = array[rightIndex];
        sorT(v);

        T pivot = v[1];

        sWap(array, rightIndex, v[2], v, leftIndex, rightIndex);
        sWap(array, leftIndex, v[0], v, leftIndex, rightIndex);
        sWap(array, rightIndex - 1, pivot, v, leftIndex, rightIndex);

        int j = rightIndex - 2;

        for (int i = rightIndex - 2; i > leftIndex; i--) {

            if (array[i].compareTo(pivot) > 0) {
                Util.swap(array, i, j);
                j--;
            }

        }
        Util.swap(array, rightIndex - 1, j + 1);
        sort(array, leftIndex, j);
        sort(array, j + 2, rightIndex);

    }

    private void sWap(T[]array, int i, T j, T [] v, int left, int right){
        int index = 0;

        if(array[left].equals(j))
            index = left;

        else if (array[right].equals(j))
            index = right;

        else{
            int mid = (left + right)/2;
            index = mid;
        }

        Util.swap(array, i, index);

    }

    private void sorT(T[] array) {
        Arrays.sort(array);
    }




}
