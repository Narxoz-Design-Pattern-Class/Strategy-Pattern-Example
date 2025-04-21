// 1. Strategy Interface
interface SortStrategy {
    void sort(int[] data);
}

// 2. Concrete Strategies:
class BubbleSort implements SortStrategy {
    @Override
    public void sort(int[] data) {
        System.out.println("Sorting with Bubble Sort...");
        int n = data.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
    }
}

class QuickSort implements SortStrategy {
    @Override
    public void sort(int[] data) {
        System.out.println("Sorting with Quick Sort...");
        quickSort(data, 0, data.length - 1);
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
                i++;
            }
        }
        int temp = arr[i]; arr[i] = arr[high]; arr[high] = temp;
        return i;
    }
}

// 3. Context class: holds a reference to a strategy
class DataSet {
    private int[] data;
    private SortStrategy strategy;

    public DataSet(int[] data) {
        this.data = data;
    }

    public void setSortStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void sortData() {
        if (strategy == null) {
            System.out.println("No sorting strategy set!");
            return;
        }
        strategy.sort(data);
        display();
    }

    private void display() {
        System.out.print("Sorted result: ");
        for (int i : data) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

// 4. Demo
public class StrategyPatternDemo {
    public static void main(String[] args) {
        int[] numbers = { 5, 3, 8, 1, 2 };

        DataSet dataset = new DataSet(numbers.clone());

        dataset.setSortStrategy(new BubbleSort());
        dataset.sortData();

        dataset = new DataSet(numbers.clone()); // reset unsorted
        dataset.setSortStrategy(new QuickSort());
        dataset.sortData();
    }
}
