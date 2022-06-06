package ServerMain;

public class ServerMain {

    public static String path_to_file = System.getenv("PATH_TO_FILE");
//    public static String path_to_file = "/Users/dmeaaxd/Documents/JavaProjects/Lab6ServerClient/file.txt";
    public static void main(String []args){
        Server server = new Server();
        server.start(args);
    }

}
