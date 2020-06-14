package by.clowns.service;

import by.clowns.repository.RegionRepository;
import by.clowns.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RegionService implements ServiceInterface<Region> {

    private final RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
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
    public void update(Region entity, long id) {
        Region regionToUpdate = regionRepository.findById(id);
        regionToUpdate.setName(entity.getName());
        regionToUpdate.setCars(entity.getCars());
        regionRepository.save(regionToUpdate);
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
