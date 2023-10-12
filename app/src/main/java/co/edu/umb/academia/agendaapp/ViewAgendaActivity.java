package co.edu.umb.academia.agendaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.text.MessageFormat;
import co.edu.umb.academia.agendaapp.adapter.ApiAdapter;
import co.edu.umb.academia.agendaapp.dto.Agenda;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAgendaActivity extends AppCompatActivity implements Callback<List<Agenda>> {

    private ListView listview;
    public static final String ITEM_AGENDA = "Fecha: {0}\nAsunto: {1}\nActividad: {2}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_agenda);

        listview = (ListView)findViewById(R.id.listViewAgenda);

        try{
       Call<List<Agenda>> call = ApiAdapter.getApiService().getAllAgendas();
       call.enqueue(this);
        }catch (Exception ex){
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            Toast.makeText(this, errors.toString(), Toast.LENGTH_LONG).show();
        }

    }

    public void backHome(View view){
        Intent back = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(back);
    }

    @Override
    public void onResponse(Call<List<Agenda>> call, Response<List<Agenda>> response) {
        if(response.isSuccessful()){
           List<Agenda> agendas = response.body();
           List<String> arrayAgendas = new ArrayList<>();
           for(Agenda item : agendas){
               arrayAgendas.add(MessageFormat.format(ITEM_AGENDA,item.getFecha().trim(),item.getAsunto().trim(),item.getActividad().trim()));
           }
           ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayAgendas);
           listview.setAdapter(adapter);
        }
    }

    @Override
    public void onFailure(Call<List<Agenda>> call, Throwable t) {
        StringWriter errors = new StringWriter();
        t.printStackTrace(new PrintWriter(errors));
        Toast.makeText(this, errors.toString(), Toast.LENGTH_LONG).show();
    }
}