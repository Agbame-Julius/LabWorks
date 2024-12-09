package org.example.websortingalgo.service;

import org.example.websortingalgo.dto.SortingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Service class that handles sorting requests and delegates sorting to appropriate sorting algorithm services.
 * <p>
 * This class is responsible for receiving a sorting request, choosing the appropriate sorting algorithm based on the
 * user's input, and returning the sorted data. It supports multiple sorting algorithms such as Bucket Sort, Merge Sort,
 * Quick Sort, Heap Sort, and Radix Sort.
 * </p>
 */
@Service
public class SortingService {
    private final BucketSortService bucketSortService;
    private final MergeSortService mergeSortService;
    private final QuickSort quickSort;
    private final HeapSortService heapSortService;
    private RadixSortService radixSortService;


    @Autowired
    public SortingService(BucketSortService bucketSortService,
                          MergeSortService mergeSortService, QuickSort quickSort,
                          HeapSortService heapSortService,
                          RadixSortService radixSortService) {
        this.bucketSortService = bucketSortService;
        this.mergeSortService = mergeSortService;
        this.quickSort = quickSort;
        this.heapSortService = heapSortService;
        this.radixSortService = radixSortService;
    }

    /**
     * Handles the sorting of data based on the user's request.
     *
     * @param sortingRequest the sorting request containing algorithm, order, and data to be sorted
     * @return a map containing the sorted data, the algorithm used, and the order of sorting
     * @throws  if an unsupported sorting algorithm is requested
     * @throws  if the data provided is invalid or empty
     */
    public Map<String, Object> handleSorting(SortingRequest sortingRequest) {
        String algorithm = sortingRequest.getAlgorithm();
        String order = sortingRequest.getOrder();
        List<Integer> data = sortingRequest.getData();

        //validate inputs
        if(data == null || data.isEmpty()){
            throw new NullPointerException("data is null or empty");
        }

        // Convert the list to an array
        int[] inputArray = data.stream().mapToInt(Integer::intValue).toArray();

        // Delegate sorting to the appropriate service
        int[] sortedArray;
        switch (algorithm.toLowerCase()) {
            case "bucket":
                sortedArray = bucketSortService.sort(algorithm, inputArray);
                break;
            case "merge":
                sortedArray = mergeSortService.sort(algorithm, inputArray);
                break;
            case "quick":
                sortedArray = quickSort.sort(algorithm, inputArray);
                break;
            case "heap":
                sortedArray = heapSortService.sort(algorithm, inputArray);
                break;
            case "radix":
                sortedArray = radixSortService.sort(algorithm, inputArray);
                break;
            default:
                throw new IllegalArgumentException("Unsupported algorithm: " + algorithm);
        }

        // Prepare the response
        Map<String, Object> response = new HashMap<>();
        response.put("sorted_data", Arrays.stream(sortedArray).boxed().collect(Collectors.toList()));
        response.put("algorithm", algorithm);
        response.put("order", order);
        return response;
    }
}
