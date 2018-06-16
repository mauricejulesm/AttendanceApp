package repositories;

import java.util.List;
import model.Swipe;

public interface RepositoryInterface<T> {

    /**
     *
     * @param item
     */
    void add(Swipe item);

    /**
     *
     * @param id
     * @return
     */
    Swipe getItem(int id);

    List<Swipe> getItems();

    /**
     *
     * @param id
     */
    
    void remove(int id);

    void setItems(List<Swipe> items);

    /**
     *
     * @param filename
     */
    
    void store(String filename);

    /**
     *
     * @return
     */
    @Override
    String toString();
//    String toString(char delimiter);
    
}
