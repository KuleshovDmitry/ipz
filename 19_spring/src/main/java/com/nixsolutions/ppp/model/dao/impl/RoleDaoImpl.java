package com.nixsolutions.ppp.model.dao.impl;

import com.nixsolutions.ppp.model.dao.RoleDao;
import com.nixsolutions.ppp.model.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class RoleDaoImpl extends DefaultDao<Role> implements RoleDao {
    private static final Logger log = LogManager.getLogger(RoleDaoImpl.class.getName());

    public RoleDaoImpl() {
        setTableName("Role");
    }


    @Override
    @Transactional
    public long getId(Role role) {
        log.traceEntry(role.toString());
        Role result = getUniqueObjectFromQuery("" +
                "from Role where name = \'" + role.getName() + "\'");
        if (result == null) {
            return log.traceExit(0);
        }
        return log.traceExit(result.getId());
    }
}