package by.clowns.service;

import by.clowns.repository.RequestRepository;
import by.clowns.entity.RentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RequestService implements ServiceInterface<RentRequest> {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public void create(RentRequest entity) {
        requestRepository.save(entity);
    }

    @Override
    public Set<RentRequest> read() {
        List<RentRequest> requestList = requestRepository.findAll();
        return new HashSet<>(requestList);
    }

    @Override
    public void update(RentRequest entity, long id) {
        RentRequest requestToUpdate = requestRepository.findById(id);
        requestToUpdate.setCar(entity.getCar());
        requestToUpdate.setUser(entity.getUser());
        requestRepository.save(requestToUpdate);
    }

    @Override
    public void delete(long id) {
        requestRepository.deleteById(id);
    }

    @Override
    public RentRequest get(long id) {
        return requestRepository.findById(id);
    }
}
