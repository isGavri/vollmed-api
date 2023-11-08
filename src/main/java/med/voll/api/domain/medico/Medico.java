package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;


@Entity
@Table(name = "Medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    private String email;
    private String telefono;
    private String documento;
    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;


    public Medico(DatosDeRegistroMedico datosMedico) {
        this.activo = true;
        this.nombre =  datosMedico.nombre();
        this.email = datosMedico.email();
        this.documento = datosMedico.documento();
        this.direccion = new Direccion(datosMedico.direccion());
        this.especialidad = datosMedico.especialidad();
        this.telefono = datosMedico.telefono();
    }

    public void actualizarMedico(DatosActualizarMedico datosActualizarMedico) {
        if (datosActualizarMedico.nombre() != null){
            this.nombre =  datosActualizarMedico.nombre();
        }
        if (datosActualizarMedico.documento() != null){
            this.documento = datosActualizarMedico.documento();
        }
        if (datosActualizarMedico.direccion() != null){
            this.direccion = direccion.actualizarDireccion(datosActualizarMedico.direccion());

        }
    }

    public void desactivarMedico() {
        this.activo = false;
    }
}
