package orderStatistic;

import java.util.HashMap;

import static util.Util.swap;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * <p>
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1].
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * <p>
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as
 * estatisticas de ordem maiores que k.
 * <p>
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Porem, voce pode modificar o array original.
 * Voce pode criar ainda um array de tamanho k que vai armazenar apenas
 * os elementos a serem retornados.
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala
 * para calcular estatisticas de ordem.
 * - Se a entrada for invalida, deve-se retornar um array vazio (por exemplo,
 * ao solicitar os 5 maiores elementos em um array que soh contem 3 elementos).
 * Este metodo NUNCA deve retornar null.
 *
 * @param <T>
 * @author campelo and adalberto
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T> {

    @Override
    public T[] getKLargest(T[] array, int k) {
        if (k > array.length)
            return (T[]) new Comparable[0];
        T[] largests = (T[]) new Comparable[k];

        for (int currentIndex = 0; currentIndex < k; currentIndex++) {
            int order = array.length - k + currentIndex + 1;
            largests[currentIndex] = orderStatistics(array, order);
        }
        return largests;
    }

    /**
     * Metodo que retorna a k-esima estatistica de ordem de um array, usando
     * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista
     * a k-esima estatistica de ordem, entao retorna null.
     * <p>
     * Obs: o valor de k deve ser entre 1 e N.
     *
     * @param array
     * @param k
     * @return
     */
    public T orderStatistics(T[] array, int k) {
        if (!estaOrdenado(array))
            quickSort(array, 0, array.length - 1);
        return array[k - 1];
    }

    private boolean estaOrdenado(T[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(array[i - 1]) < 0)
                return false;
        }
        return true;
    }

    private void quickSort(T[] array, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex)
            return;

        int pivotIndex = particiona(array, leftIndex, rightIndex);
        quickSort(array, leftIndex, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, rightIndex);
    }

    private int particiona(T[] array, int leftIndex, int rightIndex) {
        selectPivot(array, leftIndex, rightIndex);

        T pivot = array[leftIndex];

        int i = leftIndex;
        int j = rightIndex;

        while (i <= j) {

            if (array[i].compareTo(pivot) > 0) {
                while (array[j].compareTo(pivot) > 0) {
                    j--;
                }
                if (j < i) break;
                swap(array, i, j);
            }
            i++;
        }
        swap(array, j, leftIndex);
        return j;
    }

    private void selectPivot(T[] array, int leftIndex, int rightIndex) {

        int middleIndex = (leftIndex + rightIndex) / 2;
        HashMap<Integer, Integer> removidos = new HashMap<>(3);

        int[] orders = new int[3];

        for (int orderIndex = 0; orderIndex < 3; orderIndex++) {
            int smallestIndex = leftIndex;

            if (!removidos.containsKey(middleIndex) &&
                    array[middleIndex].compareTo(array[smallestIndex]) < 0)
                smallestIndex = middleIndex;

            if (!removidos.containsKey(rightIndex) &&
                    array[rightIndex].compareTo(array[smallestIndex]) < 0)
                smallestIndex = rightIndex;
            orders[orderIndex] = smallestIndex;
            removidos.put(smallestIndex, 1);
        }
        swap(array, leftIndex, orders[1]);
    }
}
