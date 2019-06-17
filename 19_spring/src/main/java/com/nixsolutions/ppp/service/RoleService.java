package com.nixsolutions.ppp.service;

import com.nixsolutions.ppp.model.dao.RoleDao;
import com.nixsolutions.ppp.model.dao.impl.RoleDaoImpl;
import com.nixsolutions.ppp.model.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;
    public boolean create(Role role){
        return roleDao.insert(role);
    }
    public boolean delete(Role role){
        return roleDao.delete(role);
    }
    public boolean delete(long i){
        return roleDao.delete(i);
    }
    public boolean update(Role role){
        return roleDao.update(role);
    }
    public List<Role> selectAll(){
        return roleDao.selectAll();
    }
    public long getId(Role role){
        return roleDao.getId(role);
    }
    public Role getRole(long id){
        return roleDao.getById(id, Role.class);
    }
}
