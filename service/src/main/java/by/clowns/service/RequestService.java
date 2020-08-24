package by.clowns.service;

import by.clowns.entity.RentRequest;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RequestService {

    void create(RentRequest entity);

    Set<RentRequest> read();

    void update(RentRequest entity);

    void delete(long id);

    RentRequest get(long id);

}
