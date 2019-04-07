package repository;

import javassist.NotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class BaseRepository <T> {
    protected Session session;

    protected void refreshSession(){
        if (session == null || !session.isOpen()) session = HibernateConfig.getSession();
    }

    public void save (T toAdd){
        this.refreshSession();
        System.out.println("SAVING");
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(toAdd);
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            throw e;
        }
        finally {
            session.close();
        }
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
