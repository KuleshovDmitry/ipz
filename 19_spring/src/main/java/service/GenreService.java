package service;

import model.dao.GenreDao;
import entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreDao genreDao;
    public boolean create(Genre genre){
        return genreDao.insert(genre);
    }
    public boolean delete(Genre genre){
        return genreDao.delete(genre);
    }
    public boolean delete(long i){
        return genreDao.delete(i);
    }
    public boolean update(Genre genre){
        return genreDao.update(genre);
    }
    public List<Genre> selectAll(){
        return genreDao.selectAll();
    }
    public long getId(Genre genre){
        return genreDao.getId(genre);
    }
    public Genre getGenre(long id){
        return genreDao.getById(id, Genre.class);
    }
}
