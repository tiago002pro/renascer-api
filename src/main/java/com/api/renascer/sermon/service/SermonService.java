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

    @Transactional
    public List<Sermon> searchBySpeaker(String speaker) {
        return repository.findBySpeaker(speaker);
    }

    @Transactional
    public List<Sermon> searchBySpeakers(List<String> speakers) {
        return repository.findBySpeakers(speakers);
    }

    @Transactional
    public List<String> searchAllSpeakers(String name) {
        return repository.findAllSpeakers(name);
    }
}
