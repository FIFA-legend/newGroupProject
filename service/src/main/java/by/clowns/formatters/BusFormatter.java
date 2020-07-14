package by.clowns.formatters;

import by.clowns.entity.Bus;
import by.clowns.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class BusFormatter implements Formatter<Bus> {

    private final BusRepository busRepository;

    @Autowired
    public BusFormatter(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Override
    public Bus parse(String s, Locale locale) throws ParseException {
        return busRepository.findById(Long.parseLong(s));
    }

    @Override
    public String print(Bus bus, Locale locale) {
        return bus.getId().toString();
    }
}
