package co.edu.usbcali.autosusbcali.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ventas")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "numero_venta", length = 30, nullable = false)
    private String numeroVenta;

    @ManyToOne
    @JoinColumn(name = "oportunidad_id")
    private OportunidadVenta oportunidad;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "comprador_id")
    private Usuario comprador;

    @ManyToOne
    @JoinColumn(name = "asesor_id")
    private Usuario asesor;

    @Column(name = "precio_final", nullable = false)
    private BigDecimal precioFinal;

    @Column(name = "forma_pago", length = 10, nullable = false)
    private String formaPago;

    @ManyToOne
    @JoinColumn(name = "solicitud_credito_id")
    private SolicitudCredito solicitudCredito;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
}