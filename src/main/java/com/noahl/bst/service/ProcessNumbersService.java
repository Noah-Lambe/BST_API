package com.noahl.bst.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noahl.bst.model.Node;
import com.noahl.bst.model.ProcessNumbersResponse;
import com.noahl.bst.model.ProcessRecord;
import com.noahl.bst.repository.ProcessRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProcessNumbersService {

    private final BSTService bstService;
    private final ProcessRecordRepository repo;
    private final ObjectMapper mapper;

    public ProcessNumbersService(BSTService bstService,
                                 ProcessRecordRepository repo,
                                 ObjectMapper mapper) {
        this.bstService = bstService;
        this.repo = repo;
        this.mapper = mapper;
    }

    @Transactional
    public ProcessNumbersResponse process(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return new ProcessNumbersResponse(null, null, "No numbers provided.", false);
        }

        // Build BST from scratch using your existing insert
        bstService.clear();
        for (Integer n : numbers) {
            if (n != null) bstService.insert(n);
        }
        Node tree = bstService.getRoot();

        try {
            ProcessRecord rec = new ProcessRecord();
            rec.setInputNumbersJson(mapper.writeValueAsString(numbers));
            rec.setTreeJson(mapper.writeValueAsString(tree));
            rec = repo.save(rec);

            String msg = "Processed " + numbers.size() + " numbers. Record ID: " + rec.getId();
            return new ProcessNumbersResponse(rec.getId(), tree, msg, true);
        } catch (Exception e) {
            return new ProcessNumbersResponse(null, tree, "Processed but save failed: " + e.getMessage(), false);
        }
    }
}
