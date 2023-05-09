package algoritimosOrdenacao;

public class SelectionSort {

    public void selectionSort(int[] v) {

        for (int i = 0; i < v.length - 1; i++) {
            int indexMin = i;

            for (int j = i + 1; j < v.length; j++) {
                if (v[j] < v[indexMin])
                    indexMin = j;
            }

            swap(v, i, indexMin);

        }
    }

    public void swap(int[] vetor, int i, int j) {
        int aux = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = aux;
    }
}
