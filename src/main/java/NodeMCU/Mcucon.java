package NodeMCU;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Mcucon {
    Socket soc;

    public void mcon() {
        if (isConnected()) {
        } else {
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
    }
        private boolean isConnected () {
            // TCheck is socket is connected to server
            if (soc != null) {
                if (soc.isConnected())
                    return true;
            }
            return false;
        }

}