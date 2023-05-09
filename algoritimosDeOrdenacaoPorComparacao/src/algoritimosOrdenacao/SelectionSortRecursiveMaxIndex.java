package algoritimosOrdenacao;

//implementação a partir do maior indicie
public class SelectionSortRecursiveMaxIndex {

    public void recursive(int[] v, int n) {

        if (n == 1)
            return;

        int maxIndex = 0;

        for (int i = 0; i < n; i++) {
            if (v[i] > v[maxIndex])
                maxIndex = i;

        }
        swap(v, maxIndex, n - 1);
        recursive(v, n - 1);

    }

    public void swap(int[] vetor, int i, int j) {
        int aux = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = aux;
    }
}
