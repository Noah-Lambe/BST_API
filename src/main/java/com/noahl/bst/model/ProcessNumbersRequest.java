package com.noahl.bst.model;

import java.util.List;

public class ProcessNumbersRequest {
    private List<Integer> numbers;

    public ProcessNumbersRequest() {}
    public ProcessNumbersRequest(List<Integer> numbers) { this.numbers = numbers; }

    public List<Integer> getNumbers() { return numbers; }
    public void setNumbers(List<Integer> numbers) { this.numbers = numbers; }
}
