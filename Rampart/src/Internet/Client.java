package Internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class  Client{
    private  String serverIP;
    private int port;
    private boolean isStop;
    private String msg;
    private PrintWriter out;
    private BufferedReader in;
    private Socket socket;

//    public ArrayList<String> getData() {
//        return data;
//    }

    public String[] getData() {
        String[] str = data.split(",");
        return str;
    }

        private String data;
//    private ArrayList<String> data;


    private static Client client;

//    private Client(String serverIP, int port) {
//        this.serverIP = serverIP;
//        this.isStop = false;
//        this.port = port;
//        this.data = "";
//        this.msg = "";
//
//    }

    private Client() {
        this.isStop = false;
        this.data = "";
        this.msg = "";
    }

    public static Client getInstance() {
        if (client == null) {
            client = new Client();
        }
        return client;
    }

    public void closeConnect() throws IOException {
        isStop = true;
        out.close();
        in.close();
        socket.close();

    }

    public void send (String msg) {
        out.println(msg);
        out.flush();
    }

    public void start(int port, String serverIP) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    connect(port,serverIP);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    private   void connect(int port, String serverIP) throws IOException {
        //建立連線指定Ip和埠的socket
        socket = new Socket( serverIP,port);
        //獲取系統標準輸入流
        out = new PrintWriter(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //建立一個執行緒用於讀取伺服器的資訊
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!isStop){
                        String str = "";
                        str =in.readLine();// 逗號分隔字串
//                        String[] strArr = str.split(",");
//                        data = new ArrayList<String>(Arrays.asList(strArr));
                        data = str;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();





        //寫資訊給客戶端
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    while (!isStop){
//                            System.out.println("是我 " + msg);
//                            out.println(msg);
//                            out.flush();
//
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//        while (!isStop){
//            //將從鍵盤獲取的資訊給到伺服器
////            out.close();
////            in.close();
////            socket.close();
//        }


    }
}
