package org.periodicalswebapp.daoimpl;

import org.periodicalswebapp.dao.PeriodicalDao;
import org.periodicalswebapp.models.*;
import java.util.List;
import java.util.ArrayList;

public class PeriodicalDaoImpl implements PeriodicalDao {
    private List<Periodical> periodicals;

    public PeriodicalDaoImpl() {
        periodicals = new ArrayList<Periodical> ();
        periodicals.add(new Periodical(1, "NY Times"));
        periodicals.add(new Periodical(2, "The New Yorker"));
    }

    @Override
    public List<Periodical> getAllPeriodicals() {
        return periodicals;
    }

    @Override
    public Periodical getPeriodicalById(int id) {
        for(Periodical periodical : periodicals) {
            if (periodical.getId() == id) {
                return periodical;
            }
        }
        return null;
    }

    @Override
    public void savePeriodical(Periodical periodical) {
        periodicals.add(periodical);
    }

    @Override
    public void deletePeriodical(Periodical periodical) {
        periodicals.remove(periodical);
    }
}
