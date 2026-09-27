package co.edu.usbcali.autosusbcali.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "negociaciones")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Negociacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "oportunidad_id")
    private OportunidadVenta oportunidad;

    @ManyToOne
    @JoinColumn(name = "asesor_id")
    private Usuario asesor;

    @Column(name = "precio_ofertado", nullable = false)
    private BigDecimal precioOfertado;

    @Column(name = "descuento_porcentaje")
    private BigDecimal descuentoPorcentaje;

    @Column(name = "descuento_valor")
    private BigDecimal descuentoValor;

    @Column(name = "requiere_aprobacion", nullable = false)
    private boolean requiereAprobacion;

    @Column(name = "estado_aprobacion", length = 20)
    private String estadoAprobacion;

    @ManyToOne
    @JoinColumn(name = "aprobador_id")
    private Usuario aprobador;

    @Column(name = "fecha_aprobacion")
    private LocalDateTime fechaAprobacion;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
}