package co.edu.umb.academia.agendaapp.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Agenda implements Serializable {
    private static final long serialVersionUID = 1L;
    private int idagenda;
    private String fecha;
    private String asunto;
    private String actividad;

    public int getIdagenda() {
        return idagenda;
    }

    public void setIdagenda(int idagenda) {
        this.idagenda = idagenda;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }
}

