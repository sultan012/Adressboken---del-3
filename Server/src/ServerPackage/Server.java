package ServerPackage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

    public static void main(String[] args) {


        try {
            ServerSocket serverSocket = new ServerSocket(61616);

            while (true) {

                Socket clientSocket = serverSocket.accept();
                System.out.println("Server connected with clint port number 61616!");

                new Thread(
                        new Runnable() {

                            public void run() {
                                try {
                                    InputStream inputStream = clientSocket.getInputStream();

                                    InputStreamReader inputStreamReader = new InputStreamReader(
                                            new FileInputStream("C:/OOPnew/InlamningsUppgift/Server/CentralKatalog.csv"));
                                    BufferedReader reader = new BufferedReader(inputStreamReader);

                                    InputStreamReader inputStreamReader1 = new InputStreamReader(inputStream);
                                    BufferedReader ordarReader = new BufferedReader(inputStreamReader1);

                                    OutputStream outputStream = clientSocket.getOutputStream();
                                    PrintWriter writer = new PrintWriter(outputStream);

                                    boolean connect = true;
                                    try {
                                        String lineToSend = "";
                                        while (connect) {

                                            String order = ordarReader.readLine();

                                            switch (order) {
                                                case "getall": {
                                                    System.out.println("Request from clint is : " + order);
                                                    for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                                                        line = line.replace(",", " ");
                                                        lineToSend += line + "\n";
                                                    }
                                                    writer.println(lineToSend);
                                                    writer.flush();
                                                }
                                                break;

                                                case "exit":
                                                    System.out.println("Request from clint is : " + order);

                                                    System.out.println("Disconnected with clint port number 61616!");

                                                    connect = false;
                                                    break;

                                                default: {
                                                    System.out.printf("Incorrect Request " + order);
                                                    break;
                                                }
                                            }
                                        }
                                    } catch (SocketException e) {
                                        System.out.println(" loss connection with clint ! ");
                                    } catch (IOException e) {
                                        System.out.println(" Something wrong when load and send data! ");
                                    } catch (NullPointerException e) {
                                        System.out.println(" Something wrong when load and send data! ");
                                    } finally {
                                        writer.close();
                                        reader.close();
                                        ordarReader.close();
                                        clientSocket.close();
                                    }


                                } catch (FileNotFoundException e) {
                                    System.out.println("File not found! ");
                                } catch (IOException e) {
                                    System.out.println(" Something wrong when load and send data! ");
                                }
                            }
                        }
                ).start();

            }

        } catch (SocketException e) {
            System.out.println("Connection reset! " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Can't accept client connection. " + e.getMessage());
        }
    }
}