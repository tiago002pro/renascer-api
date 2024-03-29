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
    public Sermon create(Sermon sermon) {
        return repository.save(sermon);
    }

    @Transactional
    public Sermon update(Sermon sermon) {
        return repository.save(sermon);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public List<Sermon> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Sermon getById(Long id) {
        return repository.findById(id).get();
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
    public List<String> searchSpeakers(String name) {
        return repository.searchAllSpeakers(name);
    }

    @Transactional
    public List<Sermon> getMostRecent(Integer limit) {
        return repository.findMostRecent(limit);
    }
}
