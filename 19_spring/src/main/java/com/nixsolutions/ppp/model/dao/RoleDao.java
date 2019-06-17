package com.nixsolutions.ppp.model.dao;

import com.nixsolutions.ppp.model.entity.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RoleDao {
    @Transactional
    public boolean insert(Role role);
    public boolean delete(Role role);
    public boolean delete(long i);
    public boolean update(Role role);
    public List<Role> executeWithResultList(String query);
    public List<Role> selectAll();
    public long getId(Role role);
    public Role getById(long id, Class objClass);
}
