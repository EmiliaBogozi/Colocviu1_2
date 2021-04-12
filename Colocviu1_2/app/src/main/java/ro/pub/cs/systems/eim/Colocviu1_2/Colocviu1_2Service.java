package ro.pub.cs.systems.eim.Colocviu1_2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Colocviu1_2Service extends Service {
    private ProcessingThread processingThread = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int sum = intent.getIntExtra(Constants.SUM, -1);

        processingThread = new ProcessingThread(this, sum);
        processingThread.start();

        return Service.START_REDELIVER_INTENT;
    }

}