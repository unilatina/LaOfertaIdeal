package xyz.camiloarguello.laofertaideal;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static final String CHANNEL_1 = "canal1";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();

    }

    private void createNotificationChannel() {

        // OREO Y PIE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Creamos canal de comunicacion
            NotificationChannel canal = new NotificationChannel(CHANNEL_1,"Mi canal de notificacion", NotificationManager.IMPORTANCE_DEFAULT);

            // Creamos el administrador del canal
            NotificationManager manager = getSystemService(NotificationManager.class);

            // Creamos notificacion con el canal creado
            manager.createNotificationChannel(canal);

        }


    }


}
