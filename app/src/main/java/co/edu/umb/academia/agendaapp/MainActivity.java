package co.edu.umb.academia.agendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void opCreateAgenda(View view){
        Intent createAgenda = new Intent(getApplicationContext(),CreateAgendaActivity.class);
        startActivity(createAgenda);
    }

    public void opRetrieveAgenda(View view){
        Intent retrieveAgenda = new Intent(getApplicationContext(),ViewAgendaActivity.class);
        startActivity(retrieveAgenda);
    }
}