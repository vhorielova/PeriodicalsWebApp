package org.periodicalswebapp.dao;

import java.util.List;
import org.periodicalswebapp.models.*;

public interface PeriodicalDao {
    List<Periodical> getAllPeriodicals();
    Periodical getPeriodicalById(int id);
    void savePeriodical(Periodical periodical);
    void deletePeriodical(Periodical periodical);
}
