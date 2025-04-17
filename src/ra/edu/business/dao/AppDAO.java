package ra.edu.business.dao;

public interface AppDAO {
    void insert(Object obj);

    void update(Object obj);

    void delete(int id);

    Object getById(int id);

    Object getAll();
}
