import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(3);

        maxHeap.set(2);
        maxHeap.set(3);
        maxHeap.set(5);

        System.out.println("Is the max heap empty? " + maxHeap.isEmpty());
        System.out.println("Max heap size: " + maxHeap.size());

        String fileName = "inputA";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int n = Integer.parseInt(reader.readLine());
            String[] elements = reader.readLine().split(" ");

            // Check if array represents a max heap
            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(elements[i]);
            }
            boolean isMaxHeap = isMaxHeap(array, n);
            System.out.println("Is the array a max heap? " + isMaxHeap);

            // Convert array to max heap
            convertToMaxHeap(array);
            System.out.println("Array converted to max heap: " + Arrays.toString(array));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isMaxHeap(int[] array, int n) {
        for (int i = 0; i < n; i++) {
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;

            if (leftChild < n && array[i] < array[leftChild]) {
                return false;
            }

            if (rightChild < n && array[i] < array[rightChild]) {
                return false;
            }
        }

        return true;
    }

    private static void convertToMaxHeap(int[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeapify(array, n, i);
        }
    }

    private static void maxHeapify(int[] array, int n, int i) {
        int largest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < n && array[leftChild] > array[largest]) {
            largest = leftChild;
        }

        if (rightChild < n && array[rightChild] > array[largest]) {
            largest = rightChild;
        }

        if (largest != i) {
            swap(array, i, largest);
            maxHeapify(array, n, largest);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
