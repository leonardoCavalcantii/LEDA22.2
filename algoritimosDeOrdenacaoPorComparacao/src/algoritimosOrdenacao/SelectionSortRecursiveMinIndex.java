package algoritimosOrdenacao;

public class SelectionSortRecursiveMinIndex {

    public void selectionSort(int[] v) {
        recursive(v, 0);

    }

    private void recursive(int[] v, int n) {

        if (n < v.length - 1) {
            int minIndex = findMin(v, n, n + 1);

            if (minIndex != n)
                swap(v, n, minIndex);

            recursive(v, n + 1);

        } 
    }

    private int findMin(int[] v, int minIndex, int aux) {

        if (aux == v.length)
            return minIndex;

        if (v[aux] < v[minIndex])
            minIndex = aux;

        return findMin(v, minIndex, aux + 1);
    }

    public void swap(int[] vetor, int i, int j) {
        int aux = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = aux;
    }

}
