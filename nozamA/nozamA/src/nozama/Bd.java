package nozama;
import nozama.products.Product;

import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.Color;


class Bd{

    /* Padrão Singlton pra criação de um banco de dados em tempo de execução */

    private static Bd instance; // Instância do nosso banco de dados.

    private Bd(){
        products   = new ArrayList<Product>();
        users      = new ArrayList<Profile>();
        userAdmins = new ArrayList<UserAdmin>();
    }; // Construtor privado para evitar que mais de uma instancia seja criada.

    public static synchronized Bd getInstance(){ // Método estático pra conseguir seracessado pelas classes e iniciar a única instância.
        if(instance == null) instance = new Bd();
        return instance;
    }

    /*********************************************************************************/

    /* Atribudos do nosso banco de dados que poderam ser ultizados nas classes */

    private ArrayList<Product> products;    // Todos os produtos do nozamA.
    private ArrayList<Profile> users;       // Todos os usuários do nozamA.
    private ArrayList<UserAdmin> userAdmins;// Todos os funcionarios do nozamA.

    public boolean creatUser = false;
    public JFrame mainWindow;
    public JFrame loginWindow;

    public ArrayList<Product>   getProducts()  {return this.products;}
    public ArrayList<Profile>   getUsers()     {return this.users;}
    public ArrayList<UserAdmin> getUserAdmin() {return this.userAdmins;}

}