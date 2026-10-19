package serverPackage;

import java.io.*;
import java.net.*;


public class Server {
    public static void main(String[] args)throws IOException {
        InetAddress addressIP = InetAddress.getByName("192.168.1.16");
        int port =1234;
        ServerSocket serveur= new ServerSocket();
        serveur.bind(new InetSocketAddress(addressIP,port));
        System.out.println("Serveur en attente sur "+addressIP.getHostAddress()+":"+port);
        Socket socket =serveur.accept();
        System.out.println("client connecte :"+socket.getInetAddress().getHostAddress());
        DataInputStream DIS =new DataInputStream(socket.getInputStream());
        DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
        while(true){
            int choice=DIS.readInt();
            if(choice==0){
                break;
            }
            double a=DIS.readDouble();
            double b=DIS.readDouble();
            double Res=0;
            boolean etat=true;
            switch (choice){
                case 1:
                    Res=a+b;
                    break;
                case 2:
                    Res=a-b;
                    break;
                case 3:
                    Res=a*b;
                    break;
                case 4:
                    if(b==0){
                        etat=false;
                    }else{
                        Res=a/b;
                    }break;
                default:
                    etat=false;

            }
            DOS.writeBoolean(etat);
            if (etat) {
                DOS.writeDouble(Res);
                System.out.println("Résultat envoyé : " + Res);
            } else {
                System.out.println("Opération invalide.");
            }
        }
        DIS.close();
        DOS.close();
        socket.close();
        serveur.close();
        System.out.println("La communication est terminée");
        System.out.println("Server closed");

}
    }
