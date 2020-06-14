package by.clowns.repository;

import by.clowns.entity.Region;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RegionRepository extends Repository<Region, Long> {

    Region save(Region region);

    Region findById(Long id);

    List<Region> findAll();

    void deleteById(Long id);

}
