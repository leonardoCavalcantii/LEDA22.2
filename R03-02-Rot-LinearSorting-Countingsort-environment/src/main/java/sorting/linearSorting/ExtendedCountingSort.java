package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

    @Override
    public void sort(Integer[] array, int leftIndex, int rightIndex) {
        if (leftIndex < 0 || rightIndex >= array.length)
            return;

        if (estaOrdenado(array, leftIndex, rightIndex))
            return;

        Integer[] valores = encontraMaiorEMenor(array, leftIndex, rightIndex);
        Integer menor = valores[0];
        Integer maior = valores[1];
        int[] frequencia = new int[maior - menor + 1];


        for (int index = leftIndex; index <= rightIndex; index++) {
            frequencia[array[index] - menor] += 1;
        }

        for (int index = 1; index < frequencia.length; index++) {
            frequencia[index] = frequencia[index] + frequencia[index - 1];
        }


        int[] helper = new int[rightIndex - leftIndex + 1];
        for (int index = rightIndex; index >= leftIndex; index--) {
            int positionHelper = frequencia[array[index] - menor] - 1;
            helper[positionHelper] = array[index];
            frequencia[array[index] - menor] -= 1;
        }


        int indexHelper = 0;
        for (int index = leftIndex; index <= rightIndex; index++) {
            array[index] = helper[indexHelper++];
        }
    }

    private Integer[] encontraMaiorEMenor(Integer[] array, int leftIndex, int rightIndex) {
        Integer menor = array[leftIndex];
        Integer maior = array[leftIndex];
        for (int index = leftIndex + 1; index <= rightIndex; index++) {
            if (array[index] < menor)
                menor = array[index];
            else if (array[index] > maior)
                maior = array[index];
        }
        return new Integer[]{menor, maior};
    }

    private boolean estaOrdenado(Integer[] array, int leftIndex, int rightIndex) {
        for (int index = leftIndex + 1; index <= rightIndex; index++) {
            if (array[index] < array[index - 1])
                return false;
        }
        return true;
    }

}