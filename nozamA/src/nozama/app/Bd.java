package nozama.app;
import nozama.products.Product;
import nozama.users.*;

import java.util.ArrayList;
import javax.swing.JFrame;

public class Bd{

    /* Padrão Singlton pra criação de um banco de dados em tempo de execução */

    private static Bd instance; // Instância do nosso banco de dados.

    private Bd(){
        products   = new ArrayList<Product>();
        users      = new ArrayList<User>();
    }; // Construtor privado para evitar que mais de uma instancia seja criada.

    public static synchronized Bd getInstance(){ // Método estático pra conseguir seracessado pelas classes e iniciar a única instância.
        if(instance == null) instance = new Bd();
        return instance;
    }

    /*********************************************************************************/

    /* Atribudos do nosso banco de dados que poderam ser ultizados nas classes */

    private ArrayList<Product> products;    // Todos os produtos do nozamA.
    private ArrayList<User> users;       // Todos os usuários do nozamA.

    public boolean creatUser = false;
    public JFrame mainWindow;
    public JFrame loginWindow;

    public ArrayList<Product> getProducts()  {return this.products;}
    public ArrayList<User>    getUsers()     {return this.users;}

}