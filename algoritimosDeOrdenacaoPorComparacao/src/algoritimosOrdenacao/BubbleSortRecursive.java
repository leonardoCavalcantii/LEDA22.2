package algoritimosOrdenacao;

public class BubbleSortRecursive {

    public void bubbleSortRecursive(int[] v) {
        sorting(v, v.length);

    }

    private void sorting(int[] v, int n) {

        if (n == 1)
            return;

        for (int i = 0; i < n - 1; i++) {
            if (v[i] > v[i + 1])
                swap(v, i, i + 1);

        }

        sorting(v, n - 1);

    }

    public void swap(int[] vetor, int i, int j) {
        int aux = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = aux;
    }

}
