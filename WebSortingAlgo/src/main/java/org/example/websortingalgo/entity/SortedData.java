package org.example.websortingalgo.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SortedData {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String algorithm;
    private String order;
    private String sortedData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSortedData() {
        return sortedData;
    }

    public void setSortedData(String sortedData) {
        this.sortedData = sortedData;
    }
}
