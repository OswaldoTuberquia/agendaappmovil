package co.edu.umb.academia.agendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;

import co.edu.umb.academia.agendaapp.adapter.ApiAdapter;
import co.edu.umb.academia.agendaapp.dto.ResponseApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAgendaActivity extends AppCompatActivity implements Callback<ResponseApi> {
    private EditText txtFecha, txtAsunto, txtActividad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_agenda);
        txtFecha = (EditText) findViewById(R.id.txtFecha);
        txtAsunto = (EditText) findViewById(R.id.txtAsunto);
        txtActividad = (EditText) findViewById(R.id.txtActividad);
    }

    public void opMainAgenda(View view){
        Intent mainAgenda = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(mainAgenda);
    }

    public void opSaveAgenda(View view){
        boolean isOk = true;
        if(txtFecha.getText().toString().trim().isEmpty()){
            isOk = false;
            Toast.makeText(this, "Ingrese Fecha", Toast.LENGTH_LONG).show();
        }
        if(txtAsunto.getText().toString().trim().isEmpty()){
            isOk = false;
            Toast.makeText(this, "Ingrese Asunto", Toast.LENGTH_LONG).show();
        }
        if(txtActividad.getText().toString().trim().isEmpty()){
            isOk = false;
            Toast.makeText(this, "Ingrese Actividad", Toast.LENGTH_LONG).show();
        }

        if(isOk){
            try {
                String pFecha = txtFecha.getText().toString().trim();
                String pAsunto = txtAsunto.getText().toString().trim();
                String pActividad = txtActividad.getText().toString().trim();
                Call<ResponseApi> response = ApiAdapter.getApiService().saveAgenda(pFecha, pAsunto, pActividad);
                response.enqueue(this);
            }catch (Exception ex){
                StringWriter errors = new StringWriter();
                ex.printStackTrace(new PrintWriter(errors));
                Toast.makeText(this, errors.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
        if(response.isSuccessful()){
            Toast.makeText(this, response.body().getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<ResponseApi> call, Throwable t) {
        StringWriter errors = new StringWriter();
        t.printStackTrace(new PrintWriter(errors));
        Toast.makeText(this, errors.toString(), Toast.LENGTH_LONG).show();
    }
}