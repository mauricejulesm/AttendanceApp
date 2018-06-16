package daos;

//import repositories.Repository;

import repositories.Repository;


/**
 *
 * @author Jules Maurice Mulisa
 */
public interface DAOInterface {

    public Repository load(String filename);

    public void store(String filename, Repository repositoryObj);
}
