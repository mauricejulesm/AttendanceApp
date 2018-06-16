package repositories;

import daos.DAOImpl;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import model.Swipe;

/**
 *
 * @author Jules Maurice Mulisa
 * @param <T>
 */

public class Repository<T> implements RepositoryInterface<T>{
    private List<Swipe> items;
    static char DELIMITER=',';    
    
    public Repository() {
        this.items = new LinkedList<>();       
    }
    
    public Repository(List<Swipe> items) {        
        this.items = items;
    }
    
    public Repository(String filename) {
        this();
        DAOImpl dao = new DAOImpl();
        this.items = dao.load(filename).getItems();  
    }
    
    @Override
    public List<Swipe> getItems() {        
        return this.items;
    }
    
    @Override
    public void setItems(List<Swipe> items) {        
        this.items = items;
    }
    
    @Override
    public void add(Swipe item) {
        this.items.add(item);
    }
       
    @Override
    public void remove(int id) {
        Predicate<Swipe> predicate = e->e.getId() == id;       
        this.items.removeIf(predicate);
    }
    
    @Override
    public Swipe getItem(int id) {
        for (Swipe item:this.items) {
            if (item.getId() == id)
                return item;
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "\nItems: " + this.items;
    }    
    
   
    public String toString(char delimiter) {
        String output = "";
        for (Swipe item: this.items) {
            output += item.toString(delimiter);
        }
        return output;
    }
    
    @Override
    public void store(String filename) { 
        DAOImpl daoObject = new DAOImpl();
        daoObject.store(filename, this);
        
    }        
}
