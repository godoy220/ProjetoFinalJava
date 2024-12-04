import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) throws Exception{
        final String url = "jdbc:mysql://localhost:3306/provafinaljackson_db";
        final String user = "root";
        final String password = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println(" PROGRMA DE EVENTOS \n");

        int opt = 0;

        do{
            System.out.println("\nMenu:");
            System.out.println("1 - Cadastrar Organizador");
            System.out.println("2 - Listar Organizadores");
            System.out.println("3 - Alterar Organizador");
            System.out.println("4 - Excluir Organizador");
            System.out.println("5 - Cadastrar Local");
            System.out.println("6 - Listar Local");
            System.out.println("7 - Alterar local");
            System.out.println("8 - Excluir Local");
            System.out.println("9 - Cadastrar Participante");
            System.out.println("10 - Listar Participantes");
            System.out.println("11 - Alterar Participantes");
            System.out.println("12 - Excluir Participantes");
            System.out.println("13 - Cadastrar Evento");
            System.out.println("14 - Listar Eventos");
            System.out.println("15 - Alterar Eventos");
            System.out.println("16 - Excluir Eventos");
            System.out.println("17 - Enviar Notificacao");
            System.out.println("18 - Encerrar Programa");
            System.out.println("\nEscolha uma opção:");
            opt = scanner.nextInt();

            switch (opt) {
                case 1:
                try{
                    System.out.println("\nCadastrar Organizador:");
                    System.out.println("Digite o nome do organizador:");
                    String NomeOrganizador = scanner.next();

                    System.out.println("Digite o CPF do organizador:");
                    String cpfOrganizador = scanner.next();

                    System.out.println("Digite o email do organizador:");
                    String email = scanner.next();

                    System.out.println("Digite o matricula do organizador:");
                    String matricula = scanner.next();
        
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    boolean sql = stm.execute("INSERT INTO organizador"
                        + " (NomeOrganizador, cpfOrganizador, email, matricula) VALUES "
                        + " ('" + NomeOrganizador + "', '" + cpfOrganizador + "', '" + email + "', '" + matricula + "')");
                    if(!sql) {
                        System.out.println("Organizador cadastrado com sucesso!");
                    } else {
                        System.out.println("Falha na execução!");
                    }
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                    break;

                case 2: {
                    System.out.println("\nListar Organizador:");
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
                    break;
                }

                case 3: // Alterar Organizador
                    try {
                        System.out.println("\nAlterar Organizador:");
                        System.out.print("ID do organizador: ");
                        int id = scanner.nextInt();

                        System.out.print("Novo Nome: ");
                        String novoNome = scanner.next();

                        System.out.print("Novo CPF: ");
                        String novoCpf = scanner.next();

                        System.out.print("Novo Email: ");
                        String novoEmail = scanner.next();

                        System.out.print("Nova Matrícula: ");
                        String novaMatricula = scanner.next();

                        Connection con = DriverManager.getConnection(url, user, password);
                        Statement stm = con.createStatement();
                        int rows = stm.executeUpdate("UPDATE organizador SET NomeOrganizador='" + novoNome + "', cpfOrganizador='" + novoCpf 
                            + "', email='" + novoEmail + "', matricula='" + novaMatricula + "' WHERE idOrganizador=" + id);
                        System.out.println(rows > 0 ? "Organizador alterado com sucesso!" : "Organizador não encontrado.");
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 4: // Excluir Organizador
                    try {
                        System.out.println("\nExcluir Organizador:");
                        System.out.print("ID do organizador: ");
                        int id = scanner.nextInt();

                        Connection con = DriverManager.getConnection(url, user, password);
                        Statement stm = con.createStatement();
                        int rows = stm.executeUpdate("DELETE FROM organizador WHERE idOrganizador=" + id);
                        System.out.println(rows > 0 ? "Organizador excluído com sucesso!" : "Organizador não encontrado.");
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 5:
                try{
                    System.out.println("\nCadastrar Local:");
                    System.out.println("Digite o nome do local:");
                    String nomeLocal = scanner.next();

                    System.out.println("Digite o endereço do local:");
                    String endereco = scanner.next();

                    System.out.println("Digite a capacidade do local:");
                    int capacidadeLocal = scanner.nextInt();

                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    boolean sql = stm.execute("INSERT INTO local"
                        + " (nomeLocal, endereco, capacidadeLocal) VALUES "
                        + " ('" + nomeLocal + "', '" + endereco + "', " + capacidadeLocal + ")");
                    if(!sql) {
                        System.out.println("Local cadastrado com sucesso!");
                    } else {
                        System.out.println("Falha na execução!");
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                    break;

                case 6: {
                    System.out.println("\nListar Local:");
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    ResultSet sql = stm.executeQuery("SELECT * FROM local");
                    while(sql.next()) {
                        Local local = new Local(
                            sql.getInt("idLocal"),
                            sql.getString("nomeLocal"),
                            sql.getString("endereco"),
                            sql.getInt("capacidadeLocal")
                        );
                        System.out.println(local);
                    }
                    con.close();
                }
                    break;

                    case 7: // Alterar Local
                    try {
                        System.out.println("\nAlterar Local:");
                        System.out.print("ID do local: ");
                        int id = scanner.nextInt();
                
                        System.out.print("Novo Nome: ");
                        String novoNome = scanner.next();
                
                        System.out.print("Novo Endereço: ");
                        String novoEndereco = scanner.next();
                
                        System.out.print("Nova Capacidade: ");
                        int novaCapacidade = scanner.nextInt();
                
                        Connection con = DriverManager.getConnection(url, user, password);
                        Statement stm = con.createStatement();
                        int rows = stm.executeUpdate("UPDATE local SET nomeLocal='" + novoNome + "', endereco='" + novoEndereco +
                            "', capacidadeLocal=" + novaCapacidade + " WHERE idLocal=" + id);
                        System.out.println(rows > 0 ? "Local alterado com sucesso!" : "Local não encontrado.");
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                
                case 8: // Excluir Local
                    try {
                        System.out.println("\nExcluir Local:");
                        System.out.print("ID do local: ");
                        int id = scanner.nextInt();
                
                        Connection con = DriverManager.getConnection(url, user, password);
                        Statement stm = con.createStatement();
                        int rows = stm.executeUpdate("DELETE FROM local WHERE idLocal=" + id);
                        System.out.println(rows > 0 ? "Local excluído com sucesso!" : "Local não encontrado.");
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 9:
                try{
                    System.out.println("\nCadastrar Participante:");
                    System.out.println("Digite o nome do participante:");
                    String nomeParticipante = scanner.next();

                    System.out.println("Digite o CPF do participante:");
                    String cpfParticipante = scanner.next();

                    System.out.println("Digite o código do ingresso:");
                    String codIngresso = scanner.next();

                    System.out.println("Digite o telefone do participante:");
                    String telefone = scanner.next();

                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    boolean sql = stm.execute("INSERT INTO participante"
                        + " (nomeParticipante, cpfParticipante, codIngresso, telefone) VALUES "
                        + " ('" + nomeParticipante + "', '" + cpfParticipante + "', " + codIngresso + ", '" + telefone + "')");
                    if(!sql) {
                        System.out.println("Participante cadastrado com sucesso!");
                    } else {
                        System.out.println("Falha na execução!");
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                    break;

                case 10: {
                    System.out.println("\nListar Participante:");
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    ResultSet sql = stm.executeQuery("SELECT * FROM participante");
                    while(sql.next()) {
                        Participante participante = new Participante(
                            sql.getInt("idParticipante"),
                            sql.getString("nomeParticipante"),
                            sql.getString("cpfParticipante"),
                            sql.getString("codIngresso"),
                            sql.getString("telefone")
                        );

                        System.out.println(participante);
                    }
                    con.close();
                    break;
                }

                case 11: // Alterar Participante
                 try {
                 System.out.println("\nAlterar Participante:");
                 System.out.print("ID do participante: ");
                 int id = scanner.nextInt();

                 System.out.print("Novo Nome: ");
                String novoNome = scanner.next();

                System.out.print("Novo CPF: ");
                String novoCpf = scanner.next();

                System.out.print("Novo Código do Ingresso: ");
                String novoCodIngresso = scanner.next();

                System.out.print("Novo Telefone: ");
                String novoTelefone = scanner.next();

                Connection con = DriverManager.getConnection(url, user, password);
                Statement stm = con.createStatement();
                int rows = stm.executeUpdate("UPDATE participante SET nomeParticipante='" + novoNome + 
                    "', cpfParticipante='" + novoCpf + "', codIngresso='" + novoCodIngresso + 
                    "', telefone='" + novoTelefone + "' WHERE idParticipante=" + id);
                System.out.println(rows > 0 ? "Participante alterado com sucesso!" : "Participante não encontrado.");
                con.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
                }
                break;

                case 12: // Excluir Participante
                    try {
                        System.out.println("\nExcluir Participante:");
                        System.out.print("ID do participante: ");
                        int id = scanner.nextInt();

                        Connection con = DriverManager.getConnection(url, user, password);
                        Statement stm = con.createStatement();
                        int rows = stm.executeUpdate("DELETE FROM participante WHERE idParticipante=" + id);
                        System.out.println(rows > 0 ? "Participante excluído com sucesso!" : "Participante não encontrado.");
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 13:
                 try{
                    System.out.println("\nCadastrar Evento:");
                    System.out.println("Digite o nome do evento:");
                    String nomeEvento = scanner.next();

                    System.out.println("Digite a data do evento:");
                    String dataEvento = scanner.next();

                    System.out.println("Digite a descrição do evento:");
                    String descricaoEvento = scanner.next();

                    System.out.println("Digite o código do organizador:");
                    int idOrganizador = scanner.nextInt();
                    
                    System.out.println("Digite o código do local:");
                    int idLocal = scanner.nextInt();

                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    boolean sql = stm.execute("INSERT INTO evento"
                        + " (nomeEvento, dataEvento, descricaoEvento, idOrganizador, idLocal) VALUES "
                        + " ('" + nomeEvento + "', '" + dataEvento + "', '" + descricaoEvento + "', " + idOrganizador + ", " + idLocal + ")");
                    if(!sql) {
                        System.out.println("Evento cadastrado com sucesso!");
                    } else {
                        System.out.println("Falha na execução!");
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                    break;

                case 14: {
                    System.out.println("\nListar Evento:");
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    ResultSet sql = stm.executeQuery("SELECT * FROM evento");
                    while(sql.next()) {
                        Evento evento = new Evento(
                            sql.getInt("idEvento"),
                            sql.getString("nomeEvento"),
                            sql.getString("dataEvento"),
                            sql.getString("descricaoEvento"),
                            sql.getInt("idOrganizador"),
                            sql.getInt("idLocal")
                        );

                        System.out.println(evento);
                    }
                    con.close();
                }
                    break;

                    case 15: // Alterar Evento
                    try {
                        System.out.println("\nAlterar Evento:");
                        System.out.print("ID do evento: ");
                        int id = scanner.nextInt();

                        System.out.print("Novo Nome do Evento: ");
                        String novoNomeEvento = scanner.next();

                        System.out.print("Nova Data do Evento: ");
                        String novaDataEvento = scanner.next();

                        System.out.print("Nova Descrição do Evento: ");
                        String novaDescricaoEvento = scanner.next();

                        System.out.print("Novo ID do Organizador: ");
                        int novoIdOrganizador = scanner.nextInt();

                        System.out.print("Novo ID do Local: ");
                        int novoIdLocal = scanner.nextInt();

                        Connection con = DriverManager.getConnection(url, user, password);
                        Statement stm = con.createStatement();
                        int rows = stm.executeUpdate("UPDATE evento SET nomeEvento='" + novoNomeEvento + 
                            "', dataEvento='" + novaDataEvento + "', descricaoEvento='" + novaDescricaoEvento + 
                            "', idOrganizador=" + novoIdOrganizador + ", idLocal=" + novoIdLocal + 
                            " WHERE idEvento=" + id);
                        System.out.println(rows > 0 ? "Evento alterado com sucesso!" : "Evento não encontrado.");
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 16: // Excluir Evento
                    try {
                        System.out.println("\nExcluir Evento:");
                        System.out.print("ID do evento: ");
                        int id = scanner.nextInt();

                        Connection con = DriverManager.getConnection(url, user, password);
                        Statement stm = con.createStatement();
                        int rows = stm.executeUpdate("DELETE FROM evento WHERE idEvento=" + id);
                        System.out.println(rows > 0 ? "Evento excluído com sucesso!" : "Evento não encontrado.");
                        con.close();
                    } catch (SQLException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                    case 17: {
                        System.out.println("\n* Enviar Notificações *");
                        System.out.println("Escolha para quem mandar a notificação:");
                        System.out.println("[1] - Organizador");
                        System.out.println("[2] - Participante");
                        opt = scanner.nextInt();
    
                        Notificacao notificacao = opt == 1 
                            ? new NotificacaoOrganizador()
                            : new NotificacaoParticipante();
    
                        notificacao.enviarNotificacao();
                        break;
                    }

                    case 18: {
                        System.out.println("Programa encerrando...");
                        break;
                    }
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } while(opt!= 18);
            scanner.close();
        }
    }