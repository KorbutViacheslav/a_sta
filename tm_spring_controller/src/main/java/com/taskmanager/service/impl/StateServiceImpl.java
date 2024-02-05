package com.taskmanager.service.impl;

import com.taskmanager.model.State;
import com.taskmanager.repository.StateRepository;
import com.taskmanager.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {
    private final StateRepository stateRepository;

    @Override
    public State create(State state) { return stateRepository.save(state);}

    @Override
    public State readById(long id) {
        return stateRepository.findById(id).orElse(null);
    }

    @Override
    public State update(State state) {
        return stateRepository.save(state);
    }

    @Override
    public void delete(long id) {
        stateRepository.deleteById(id);
    }

    @Override
    public List<State> getAll() {
        return stateRepository.findAll();
    }

    @Override
    public State getByName(String name) {
        return stateRepository.findStateByName(name);
    }

    @Override
    public List<State> getSortAsc() {
        return stateRepository.findAllByOrderByNameAsc();
    }

}
