package com.example.engindmr.preferenceframework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    private TextView tercihTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tercihTextView=(TextView)findViewById(R.id.tercihTextView);

        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        preferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

        String [] finansBirimiArray=getResources().getStringArray(R.array.finans_birimi);
        boolean otomatikGuncelle = sharedPreferences.getBoolean("PREF_OTOMATIK_GUNCELLE",false);
        String otomatikGuncelleMesaj=(otomatikGuncelle) ? "Evet" : "Hayır" ;
        String finansBirimi=sharedPreferences.getString("PREF_FINANS_BIRIMI","0");
        String uyarmaYuzdesi=sharedPreferences.getString("PREF_UYARMA_YUZDESI","");
        tercihTextView.setText("Otomatik Guncelle:" + otomatikGuncelleMesaj + "\n" +"Finans Birimi:" +finansBirimiArray[Integer.parseInt(finansBirimi)] + "\n" + "Uyarma Yüzdesi:" + uyarmaYuzdesi);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.ayarlar_menu:
                Intent intent=new Intent(this,PreferenceFrameworkActivity.class);
                startActivity(intent);
                return true;
            default:

                return super.onOptionsItemSelected(item);

        }
    }
}
