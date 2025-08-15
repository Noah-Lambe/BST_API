package com.noahl.bst.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noahl.bst.model.Node;
import com.noahl.bst.model.PreviousTree;
import com.noahl.bst.model.ProcessRecord;
import com.noahl.bst.repository.ProcessRecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreviousTreesService {

    private final ProcessRecordRepository repo;
    private final ObjectMapper mapper;

    public PreviousTreesService(ProcessRecordRepository repo, ObjectMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Page<PreviousTree> list(int page, int size) {
        return repo.findAllByOrderByCreatedAtDesc(PageRequest.of(page, size))
                .map(rec -> {
                    try {
                        List<Integer> numbers = mapper.readValue(
                                rec.getInputNumbersJson(),
                                new TypeReference<List<Integer>>() {});
                        Node tree = mapper.readValue(rec.getTreeJson(), Node.class);
                        return new PreviousTree(rec.getId(), rec.getCreatedAt(), numbers, tree);
                    } catch (Exception e) {
                        return new PreviousTree(rec.getId(), rec.getCreatedAt(), List.of(), null);
                    }
                });
    }
}
