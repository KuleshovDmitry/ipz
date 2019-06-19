package service;

import java.util.List;

public interface DefaultServise<T> {
    public boolean create(T t);
    public boolean delete(T t);
    public boolean delete(long i);
    public boolean update(T t);
    public List<T> selectAll();
    public long getId(T t);
}
