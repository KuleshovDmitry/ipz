package com.nixsolutions.ppp.model.dao.impl;

import com.nixsolutions.ppp.model.dao.GenreDao;
import com.nixsolutions.ppp.model.entity.Genre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class GenreDaoImpl extends DefaultDao<Genre> implements GenreDao {
    private static final Logger log = LogManager.getLogger(GenreDaoImpl.class.getName());

    public GenreDaoImpl() {
        setTableName("Genre");
    }

    @Override
    public long getId(Genre genre) {
        log.traceEntry(genre.toString());
        Genre result = getUniqueObjectFromQuery("" +
                "from Genre where name = \'" + genre.getName() + "\'");
        if (result == null) {
            return log.traceExit(0);
        }
        return log.traceExit(result.getId());
    }

    @Override
    protected boolean isValid(Genre genre) {
        return super.isValid(genre) &&
                genre.getName() != null;
    }
}
