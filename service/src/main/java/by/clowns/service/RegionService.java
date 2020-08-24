package by.clowns.service;

import by.clowns.entity.Region;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RegionService {

    void create(Region entity);

    Set<Region> read();

    void update(Region entity);

    void delete(long id);

    Region get(long id);

}
