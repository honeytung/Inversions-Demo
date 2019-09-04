import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Inversions_Demo {
    // This Program find inversions from Integer Array file
    static File mergetxt = new File("IntegerArray.txt");
    static ArrayList<Integer> intArray = new ArrayList<Integer>();

    public static void main (String[] args) throws IOException {
        if (mergetxt.exists()) {
            System.out.println("Merge Sort: ");
            Scanner mergefile = new Scanner(mergetxt);

            while (mergefile.hasNextInt()) {
                intArray.add(mergefile.nextInt());
            }

            mergefile.close();

            int arraySize = intArray.size();
            int array[] = new int[arraySize];

            for (int i = 0; i < arraySize; i++) {
                array[i] = intArray.get(i);
            }

            System.out.println("Number of Inversions: " + mergeSort(array, arraySize));
        }
    }

    public static long mergeSort (int arr[], int arraySize) {
        int temp[] = new int[arraySize];

        return _mergeSort(arr, temp, 0, arraySize - 1);
    }

    public static long _mergeSort (int arr[], int temp[], int left, int right) {
        int mid;
        long inversions = 0;

        if (right > left) {
            mid = (right + left) / 2;

            inversions = _mergeSort(arr, temp, left, mid);
            inversions += _mergeSort(arr, temp, mid + 1, right);

            inversions += merge(arr, temp, left, mid + 1, right);
        }
        return inversions;
    }

    public static long merge (int arr[], int temp[], int left, int mid, int right) {
        int i, j, k;
        long inversions = 0;

        i = left;
        j = mid;
        k = left;

        while ((i <= mid - 1) && (j <= right)) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            }
            else {
                temp[k++] = arr[j++];
                inversions = inversions + (mid - i);
            }
        }

        while (i <= mid - 1)
            temp[k++] = arr[i++];

        while (j <= right)
            temp[k++] = arr[j++];

        for (i = left; i <= right; i++)
            arr[i] = temp[i];

        return inversions;
    }
}

