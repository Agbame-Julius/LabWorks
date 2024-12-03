package org.example.websortingalgo.service;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class BucketSortService {
    public int[] sort(String algorithm, int[] arr) {
        if (arr == null || arr.length <= 1) return arr;

        if(algorithm == null)
            throw new NullPointerException("algorithm is null");

        int maxValue = Arrays.stream(arr).max().orElse(0);
        int minValue = Arrays.stream(arr).min().orElse(0);

        int bucketCount = (maxValue - minValue) / arr.length + 1;
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // Distribute input into buckets
        for (int value : arr) {
            int bucketIndex = (value - minValue) / arr.length;
            buckets.get(bucketIndex).add(value);
        }

        // Sort individual buckets
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        // Concatenate all buckets
        int index = 0;
        for (List<Integer> bucket : buckets) {
            for (int value : bucket) {
                arr[index++] = value;
            }
        }

        return arr;
    }
}
