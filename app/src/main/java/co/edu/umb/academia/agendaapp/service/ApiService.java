package co.edu.umb.academia.agendaapp.service;

import java.util.List;

import co.edu.umb.academia.agendaapp.dto.Agenda;
import co.edu.umb.academia.agendaapp.dto.ResponseApi;
import retrofit2.*;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("agendas")
    Call<List<Agenda>> getAllAgendas();
    @POST("agregaragenda")
    @FormUrlEncoded
    Call<ResponseApi> saveAgenda(
            @Field("pFecha") String fecha,
            @Field("pAsunto") String asunto,
            @Field("pActividad") String actividad
    );
}
