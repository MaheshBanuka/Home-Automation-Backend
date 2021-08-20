package Lightonoff;

import NodeMCU.Mcucon;

import java.awt.Frame;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;

public class OnOff extends Frame {

    Socket soc;

    public void action(String count,String caption) throws IOException {
        int a = Integer.valueOf(count);
//        System.out.println(caption);
//        Mcucon mcucon = new Mcucon();
//        mcucon.mcon();
        if(isConnected()) {}
        else{
            System.out.println("com");
            try {
                soc = new Socket("192.168.8.101", 1987);
                System.out.println("Connected");
            } catch (UnknownHostException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                System.out.println("Can not connect");
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                System.out.println("Can not connect");

            }
        }

        InputStream socInput = soc.getInputStream();
        DataInputStream data = new DataInputStream(socInput);

        System.out.println("test test test" + data.readUTF());

        int aASCII=97;
        int command=0;
        for(int i=0;i<7;i++)
        {
            if(a==i && caption.equals("On_D"))
            {
                command=aASCII+i;
                // send command over network
                writeCommand(command);
                break;
            }
            if(a==i && caption.equals("Off_D"))
            {
                command=aASCII+i+7;
                // send command over network
                writeCommand(command);
                break;
            }
            System.out.println(a);
            if(a==i && caption.equals("dim"))
            {
                writeCommand(150+i);
                break;
            }
            if(a==i && caption.equals("ldim"))
            {
                writeCommand(160+i);
                break;
            }
        }
    }
    private boolean isConnected() {
        // TCheck is socket is connected to server
        if(soc!=null)
        {
            if(soc.isConnected())
                return true;
        }
        return false;
    }
    private void closeConnecition() {
        // If connected close the connection
        if(soc!=null)
        {
            if(soc.isConnected())
            {
                try {
                    soc.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }
    private void writeCommand(int command) {
        // If no socket is null return
        if(soc==null)
            return;
        // if socket is connected send command
        if(soc.isConnected())
        {
            byte[] cmdByte=new byte[2];
            cmdByte[0]=(byte)command;
            byte[] temp;
            try {
                temp = "!".getBytes("US-ASCII");
                cmdByte[1]=temp[0];
                System.out.println("Message sent");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("Error");
            }
            try {
                soc.getOutputStream().write(cmdByte);
                System.out.println("Message sent");
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                System.out.println("Error");
            }
        }
    }

    private void readData() throws IOException {
        InputStream socInput = soc.getInputStream();
        DataInputStream data = new DataInputStream(socInput);

        System.out.println("test test test" + data.readUTF());
        soc.close();
    }



}