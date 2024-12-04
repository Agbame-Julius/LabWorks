package org.example.websortingalgo.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;


/**
 * Service that provides functionality to perform Radix Sort on an array of integers.
 * Radix Sort is a non-comparative sorting algorithm that processes each digit of the numbers.
 * It uses a stable intermediate sorting algorithm (Counting Sort) for each digit in the numbers.
 */
@Service
public class RadixSortService {

    /**
     * Sorts the given array of integers using the Radix Sort algorithm.
     * The sorting is done by processing each digit of the numbers starting from the least significant digit (LSD).
     *
     * @param algorithm The name of the algorithm (not used in this implementation, but could be for logging or future enhancements).
     * @param arr The array of integers to be sorted.
     * @return The sorted array of integers.
     * @throws IllegalArgumentException If the input array is null or empty.
     */
    public int[] sort(String algorithm, int[] arr) {

        if (algorithm.isEmpty() || arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Input array must not be null or empty.");
        }
        int max = Arrays.stream(arr).max().orElse(0);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
        return arr;
    }

    private void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        Arrays.fill(count, 0);

        for (int j : arr) {
            count[(j / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }
}
