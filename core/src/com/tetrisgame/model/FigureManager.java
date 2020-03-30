package com.tetrisgame.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class FigureManager
{
    static Socket socket = null;
    static ObjectOutputStream oos = null;
    static ObjectInputStream ois = null;


    public static GameFigure getNextFigure() throws IOException, ClassNotFoundException {

        socket = new Socket("127.0.0.1", 6666);
        oos = new ObjectOutputStream(socket.getOutputStream());

        oos.writeObject("");

        ois = new ObjectInputStream(socket.getInputStream());

        String message = (String) ois.readObject();

        ois.close();
        oos.close();

        return new GameFigure(Integer.parseInt(message));


    }

    public static void CheckConnection()
    {
        while(true) {

            try {
                socket = new Socket("127.0.0.1", 6666);
                oos = new ObjectOutputStream(socket.getOutputStream());

                oos.writeObject("");

                ois = new ObjectInputStream(socket.getInputStream());

                String message = (String) ois.readObject();

                ois.close();
                oos.close();
                socket.close();
                break;
            } catch (IOException e) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
