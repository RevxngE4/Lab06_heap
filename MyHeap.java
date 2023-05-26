import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MyHeap {
    private static final String INPUT_FILE_NAME = "enterFile";
    private static final String OUTPUT_FILE_NAME = "outFile";

    public static void main(String[] args) {
        try {
            MyHeap myHeap = new MyHeap();
            myHeap.processOperations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processOperations() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
             BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME))) {
            int numOperations = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.naturalOrder());
            String line;

            while ((line = br.readLine()) != null) {
                String[] operation = line.split(" ");
                char operationType = operation[0].charAt(0);

                switch (operationType) {
                    case 'A':
                        int elementToAdd = Integer.parseInt(operation[1]);
                        priorityQueue.add(elementToAdd);
                        break;
                    case 'X':
                        processExtractMin(bw, priorityQueue);
                        break;
                    case 'D':
                        int index = Integer.parseInt(operation[1]);
                        int newValue = Integer.parseInt(operation[2]);
                        processDecreaseKey(priorityQueue, index, newValue);
                        break;
                }
            }
        }
    }

    private void processExtractMin(BufferedWriter bw, PriorityQueue<Integer> priorityQueue) throws IOException {
        if (!priorityQueue.isEmpty()) {
            int minElement = priorityQueue.poll();
            bw.write(String.valueOf(minElement));
            bw.newLine();
        } else {
            bw.write("*");
            bw.newLine();
        }
    }

    private void processDecreaseKey(PriorityQueue<Integer> priorityQueue, int index, int newValue) {
        Object[] queueArray = priorityQueue.toArray();
        if (index >= 0 && index < queueArray.length) {
            priorityQueue.remove(queueArray[index]);
            priorityQueue.add(newValue);
        }
    }
}
