package by.clowns;

import by.clowns.dao.Dao;
import by.clowns.dao.RegionDao;
import by.clowns.dao.UserDao;
import by.clowns.entity.*;


import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Dao<Region> dao = RegionDao.getInstance();

        Region region = new Region();
        region.setName("Voshod");

        dao.create(region);
//        Set<Region> users = dao.reed();

//        System.out.println(users.iterator().hasNext());
        System.out.println(region);
    }
}
