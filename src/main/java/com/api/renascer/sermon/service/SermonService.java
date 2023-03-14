package com.api.renascer.sermon.service;

import com.api.renascer.sermon.model.Sermon;
import com.api.renascer.sermon.repository.SermonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SermonService {
    private final SermonRepository repository;

    public SermonService(SermonRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Sermon> getAll() {
        return repository.findAll();
    }
}
