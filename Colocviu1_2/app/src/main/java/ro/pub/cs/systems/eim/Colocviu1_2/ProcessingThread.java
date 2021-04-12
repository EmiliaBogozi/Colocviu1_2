package ro.pub.cs.systems.eim.Colocviu1_2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.sql.Date;

public class ProcessingThread extends Thread {

    private Context context = null;
    private int suma = 0;

    public ProcessingThread(Context context, int suma) {
        this.context = context;
        this.suma = suma;
    }

    @Override
    public void run() {
        Log.d(Constants.TAG, "Thread has started! ");
        sleep();
        sendMessage();
        Log.d(Constants.TAG, "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.INTENT_ACTION);
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA,
                new Date(System.currentTimeMillis()) + " " + suma);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
}
