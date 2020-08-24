package by.clowns.formatters;

import by.clowns.entity.Region;
import by.clowns.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class RegionFormatter implements Formatter<Region> {

    private RegionRepository regionRepository;

    @Autowired
    public RegionFormatter(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public Region parse(String s, Locale locale) throws ParseException {
        return regionRepository.findById(Long.parseLong(s));
    }

    @Override
    public String print(Region region, Locale locale) {
        return region.getId().toString();
    }
}
