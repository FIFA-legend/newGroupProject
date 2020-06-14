package by.clowns.service;

import by.clowns.dao.RequestRepository;
import by.clowns.entity.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RequestService implements ServiceInterface<Request> {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public void create(Request entity) {
        requestRepository.save(entity);
    }

    @Override
    public Set<Request> read() {
        List<Request> requestList = requestRepository.findAll();
        return new HashSet<>(requestList);
    }

    @Override
    public void update(Request entity, long id) {
        Request requestToUpdate = requestRepository.findById(id);
        requestToUpdate.setCar(entity.getCar());
        requestToUpdate.setUser(entity.getUser());
        requestRepository.save(requestToUpdate);
    }

    @Override
    public void delete(long id) {
        requestRepository.deleteById(id);
    }

    @Override
    public Request get(long id) {
        return requestRepository.findById(id);
    }
}
