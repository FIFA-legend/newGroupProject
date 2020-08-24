package by.clowns.service.serviceImpl;

import by.clowns.repository.RequestRepository;
import by.clowns.entity.RentRequest;
import by.clowns.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository) {
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
    public void update(RentRequest entity) {
        requestRepository.save(entity);
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
