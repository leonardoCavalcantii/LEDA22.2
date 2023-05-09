package orderStatistic;

import static util.Util.swap;

import java.util.HashMap;

/**
 * O quickselect eh um algoritmo baseado no quicksort para
 * descobrir/selectionar, em tempo linear, a k-esima estatistica de ordem
 * (k-esimo menor elemento) de um conjunto de dados.
 * <p>
 * O quiskselect escolhe um elemento para ser o pivot e particiona o array
 * inicial em dois subarrays da mesma forma que o quicksort (elementos menores
 * que o pivot a esquerda do pivot e elementos maiores que o pivot a direita
 * dele). Entretanto, ao inves de chamar o quicksort recursivo nas duas metades,
 * o quickselect eh executado (recursivamente) apenas na metade que contem o
 * elemento que ele procura (o k-esimo menor elemento). Isso reduz a
 * complexidade de O(n.log n) para O(n)
 *
 * @author adalberto e campelo
 */
public class QuickSelect<T extends Comparable<T>> {

    /**
     * O algoritmo quickselect usa a mesma abordagem do quicksort para calcular o
     * k-esimo menor elemento (k-esima estatistica de ordem) de um determinado
     * array de dados comparaveis. Primeiro ele escolhe um elemento como o pivot
     * e particiona os dados em duas partes, baseando-se no pivot (exatemente da
     * mesma forma que o quicksort). Depois disso, ele chama recursivamente o
     * mesmo algoritmo em apenas uma das metades (a que contem o k-esimo menor
     * elemento). Isso reduz a complexidade de O(n.log n) para O(n).
     * <p>
     * Caso o array seja vazio ou a ordem (posicao) do elemento desejado esteja
     * fora do tamanho do array, o metodo deve retornar null.
     *
     * @param array o array de dados a procurar o k-esimo menor elemento
     *              este array normalmente nao esta ordenado
     * @param k     a ordem do elemento desejado. 1 significa primeiro menor
     *              elemento, 2 significa segundo menor elemento e assim por
     *              diante
     * @return
     */
    public T quickSelect(T[] array, int k) {

        if (array.length == 0 || k > array.length || k < 1)
            return null;

        return quickSelectRec(array, k, 0, array.length - 1);
    }

    private T quickSelectRec(T[] array, int k, int left, int right) {
        int pivotIndex = particiona(array, left, right);

        if (pivotIndex == k - 1)
            return array[pivotIndex];

        if (k - 1 > pivotIndex)
            return quickSelectRec(array, k, pivotIndex + 1, right);

        else
            return quickSelectRec(array, k, left, pivotIndex - 1);
    }

    private int particiona(T[] array, int leftIndex, int rightIndex) {
        selectPivot(array, leftIndex, rightIndex);
        T pivot = array[leftIndex];

        int i = leftIndex + 1;
        int j = rightIndex;

        while (i <= j) {
            if (array[i].compareTo(pivot) > 0) {
                while (j >= i && array[j].compareTo(pivot) > 0) {
                    j--;
                }

                if (i > j) break;
                swap(array, i, j);
            }

            i++;
        }

        swap(array, leftIndex, j);
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