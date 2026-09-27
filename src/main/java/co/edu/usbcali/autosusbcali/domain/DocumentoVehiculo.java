package co.edu.usbcali.autosusbcali.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "documentos_vehiculo")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class DocumentoVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @Column(name = "tipo_documento", length = 50, nullable = false)
    private String tipoDocumento;

    @Column(name = "url", length = 500, nullable = false)
    private String url;

    @Column(name = "fecha_expedicion")
    private LocalDate fechaExpedicion;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @Column(name = "validado", nullable = false)
    private boolean validado;

    @Column(name = "fecha_validacion")
    private LocalDateTime fechaValidacion;

    @ManyToOne
    @JoinColumn(name = "usuario_valida_id")
    private Usuario usuarioValida;
}