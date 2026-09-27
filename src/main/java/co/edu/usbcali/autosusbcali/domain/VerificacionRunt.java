package co.edu.usbcali.autosusbcali.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "verificaciones_runt")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class VerificacionRunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @Column(name = "placa", length = 10, nullable = false)
    private String placa;

    @Column(name = "tiene_prendas")
    private boolean tienePrendas;

    @Column(name = "tiene_embargos")
    private boolean tieneEmbargos;

    @Column(name = "reporte_hurto")
    private boolean reporteHurto;

    @Column(name = "estado_legal", length = 50)
    private String estadoLegal;

    @Column(name = "respuesta_raw")
    private String respuestaRaw;

    @ManyToOne
    @JoinColumn(name = "usuario_consulta_id")
    private Usuario usuarioConsulta;

    @Column(name = "fecha_consulta", nullable = false)
    private LocalDateTime fechaConsulta;
}