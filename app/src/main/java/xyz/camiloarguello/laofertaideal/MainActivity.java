package xyz.camiloarguello.laofertaideal;

import android.app.Application;
import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static xyz.camiloarguello.laofertaideal.App.CHANNEL_1;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    // Declaración de variable de notificacion
    NotificationManagerCompat managerCompat;
    TextView tituloTextView;
    SharedPreferences preferences;
    ActionBar actionBar;
    LinearLayout linearLayout;
    Button button;
    EditText editTextTitle;
    EditText editTextDescr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linear_layout_home);
        tituloTextView = findViewById(R.id.titulo_app);
        button = findViewById(R.id.button);
        editTextTitle = findViewById(R.id.titulo);
        editTextDescr = findViewById(R.id.descripcion);

        // Inicializar notificacion
        managerCompat = NotificationManagerCompat.from(this);

        setupPreferences();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);

    }

    public void setupPreferences(){
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        changeColor(preferences.getBoolean(getString(R.string.pref_dark_mode_key),getResources().getBoolean(R.bool.pref_show_bass_default)));

        preferences.registerOnSharedPreferenceChangeListener(this);
    }

    public void changeColor(Boolean b){
        if(b){

            editTextTitle.setHintTextColor(getResources().getColor(R.color.background));
            editTextTitle.setTextColor(getResources().getColor(R.color.background));
            button.setBackgroundColor(getResources().getColor(R.color.background));
            linearLayout.setBackgroundColor(getResources().getColor(R.color.dark_background));
            tituloTextView.setTextColor(getResources().getColor(R.color.background));
            editTextDescr.setHintTextColor(getResources().getColor(R.color.background));
            editTextDescr.setTextColor(getResources().getColor(R.color.background));
        }else{
            tituloTextView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            linearLayout.setBackgroundColor(getResources().getColor(R.color.background));
            editTextTitle.setHintTextColor(getResources().getColor(R.color.dark_colorPrimaryDark));
            editTextTitle.setTextColor(getResources().getColor(R.color.dark_colorPrimary));
            editTextDescr.setHintTextColor(getResources().getColor(R.color.dark_colorPrimary));
            editTextDescr.setTextColor(getResources().getColor(R.color.dark_colorPrimary));
            button.setBackgroundColor(getResources().getColor(R.color.background));
        }
    }

    public void lanzarNotificacion(View v){
        if(!editTextTitle.getText().toString().isEmpty() && !editTextDescr.getText().toString().isEmpty()){
            Notification noti = new NotificationCompat.Builder(this, CHANNEL_1)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(editTextTitle.getText().toString())
                    .setContentText(editTextDescr.getText().toString())
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .build();

            managerCompat.notify(1,noti);
        }else{
            Toast.makeText(this, "Ingresa datos de la notificación", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings){
            Intent startSettingsActivity = new Intent(this,SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if(s.equals(getString(R.string.pref_dark_mode_key))){
            changeColor(sharedPreferences.getBoolean(s,getResources().getBoolean(R.bool.pref_show_bass_default) ));
        }
    }
}
