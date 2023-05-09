package problems;

import static util.Util.swap;

public class FloorBinarySearchImpl implements Floor {

    @Override
    public Integer floor(Integer[] array, Integer x) {
        if (!estaOrdenado(array)) quickSort(array, 0, array.length - 1);
        return busca(array, null, x, 0, array.length - 1);
    }

    private boolean estaOrdenado(Integer[] array) {
        for (int index = 1; index < array.length; index++) {
            if (array[index] < array[index - 1]) return false;
        }
        return true;
    }

    private void quickSort(Integer[] array, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) return;
        int indexParticiona = particiona(array, leftIndex, rightIndex);
        quickSort(array, leftIndex, indexParticiona - 1);
        quickSort(array, indexParticiona + 1, rightIndex);
    }

    private int particiona(Integer[] array, int leftIndex, int rightIndex) {
        Integer pivot = array[leftIndex];

        int changeIndex = leftIndex + 1;

        for (int currentIndex = leftIndex + 1; currentIndex <= rightIndex; currentIndex++) {
            if (array[currentIndex] >= pivot) continue;
            swap(array, currentIndex, changeIndex);

            changeIndex++;
        }
        swap(array, leftIndex, changeIndex - 1);
        return changeIndex - 1;
    }


    private Integer busca(Integer[] array, Integer lastFloor, Integer numero, int left, int right) {
        if (left > right) return lastFloor;

        int middleIndex = (left + right) / 2;

        if (numero == array[middleIndex])
            return numero;

        if (array[middleIndex] < numero && (lastFloor == null || array[middleIndex] > lastFloor))
            lastFloor = array[middleIndex];

        if (numero < array[middleIndex]) return busca(array, lastFloor, numero, left, middleIndex - 1);

        else
            return busca(array, lastFloor, numero, middleIndex + 1, right);
    }

}