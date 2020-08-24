package by.clowns.service.serviceImpl;

import by.clowns.repository.RegionRepository;
import by.clowns.entity.Region;
import by.clowns.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public void create(Region entity) {
        regionRepository.save(entity);
    }

    @Override
    public Set<Region> read() {
        List<Region> regionList = regionRepository.findAll();
        return new HashSet<>(regionList);
    }

    @Override
    public void update(Region entity) {
        regionRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        regionRepository.deleteById(id);
    }

    @Override
    public Region get(long id) {
        return regionRepository.findById(id);
    }
}
