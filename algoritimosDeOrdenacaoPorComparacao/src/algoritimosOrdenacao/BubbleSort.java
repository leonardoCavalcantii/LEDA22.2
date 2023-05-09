package algoritimosOrdenacao;

public class BubbleSort {

    public void bubbleSort(int[] v) {

        
        for (int i = 0; i < v.length - 1; i++) {
            for(int j = 0; i < v.length - i - 1; j++){
                if(v[j] > v[j+1])
                swap(v, j, j + 1);
            }

        }

    }

    public void swap(int[] vetor, int i, int j) {
        int aux = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = aux;
    }

}
