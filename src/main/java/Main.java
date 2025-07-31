import com.mycompany.app.Controller.*;
import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    public static void main(String[] args) {
        String url = ReadENV.getConnectionURL();
        String user = ReadENV.getUsername();
        String password = ReadENV.getPassword();

        DB_Connect db = new DB_Connect(url, user, password);

        db.insertDB();
    }
}
