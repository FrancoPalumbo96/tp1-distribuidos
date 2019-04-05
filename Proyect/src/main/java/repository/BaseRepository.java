package repository;

import javassist.NotFoundException;
import org.hibernate.Session;

public abstract class BaseRepository <T> {
    protected Session session;

    protected void refreshSession(){
        if (session == null || !session.isOpen()) session = HibernateConfig.getSession();
    }

    public void add (T toAdd){
        this.refreshSession();
        session.save(toAdd);
        session.close();
    }

    public abstract T getById (Long id) throws NotFoundException;

    public void delete (T toDelete){
        this.refreshSession();
        this.session.delete(toDelete);
    }

    //Impleemnts because I think its not gonna change
    public void deleteById (Long id) throws NotFoundException{
        final T got = getById(id);
        if (got == null) throw new NotFoundException("Record Not Found");
        else delete(got);
    }

}
