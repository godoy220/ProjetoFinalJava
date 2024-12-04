import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class NotificacaoOrganizador extends Notificacao {
    final String url = "jdbc:mysql://localhost:3306/provafinaljackson_db";
    final String user = "root";
    final String password = "";
    Organizador organizador = null;

    public void enviarNotificacao() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Verificação de organizadores...\n");

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stm = con.createStatement();
            ResultSet sql = stm.executeQuery("SELECT * FROM organizador");
            while(sql.next()) {
                Organizador organizador = new Organizador(
                    sql.getInt("idOrganizador"),
                    sql.getString("NomeOrganizador"),
                    sql.getString("cpfOrganizador"),
                    sql.getString("email"),
                    sql.getString("matricula")
                );
                System.out.println(organizador);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }

        try {
            Connection con2 = DriverManager.getConnection(url, user, password);
            Statement stm2 = con2.createStatement();

            System.out.print("\nDigite o ID do organizador: ");
            int idOrganizador = scanner.nextInt();

            // Buscar o organizador no banco de dados
            String query = "SELECT * FROM organizador WHERE idOrganizador = " + idOrganizador;
            ResultSet resultSet = stm2.executeQuery(query);

            if (resultSet.next()) {
                
                this.organizador = new Organizador(
                    resultSet.getInt("idOrganizador"),
                    resultSet.getString("nomeOrganizador"),
                    resultSet.getString("cpfOrganizador"),
                    resultSet.getString("email"),
                    resultSet.getString("matricula")
                );
                System.out.println("O Organizador com ID " + this.organizador.getIdOrganizador() + " é " + this.organizador.getEmailOrganizador() + ".");
                System.out.println("Notificação de email enviada para o endereço: " + this.organizador.getEmailOrganizador());
            } else {
                System.out.println("Organizador com ID " + idOrganizador + " não encontrado.");
            }

            con2.close();
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }
    
}