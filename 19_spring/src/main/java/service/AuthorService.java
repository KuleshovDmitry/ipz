package service;

import model.dao.AuthorDao;
import entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorDao authorDao;
    public boolean create(Author author){
        return authorDao.insert(author);
    }
    public boolean delete(Author author){
        return authorDao.delete(author);
    }
    public boolean delete(long i){
        return authorDao.delete(i);
    }
    public boolean update(Author author){
        return authorDao.update(author);
    }
    public List<Author> selectAll(){
        return authorDao.selectAll();
    }
    public long getId(Author author){
        return authorDao.getId(author);
    }
    public Author getAuthor(long id){
        return authorDao.getById(id, Author.class);
    }
    public List<Author> find(String firstName, String lastName){
        return authorDao.getByName(firstName, lastName);
    }
}
