package co.edu.usbcali.autosusbcali.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehiculos")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "vin", length = 17, nullable = false)
    private String vin;

    @Column(name = "placa", length = 10)
    private String placa;

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;

    @Column(name = "anio", nullable = false)
    private Short anio;

    @Column(name = "tipo", length = 6, nullable = false)
    private String tipo;

    @Column(name = "color", length = 50)
    private String color;

    @Column(name = "kilometraje", nullable = false)
    private Integer kilometraje;

    @Column(name = "precio_base", nullable = false)
    private BigDecimal precioBase;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "publicado_en")
    private LocalDateTime publicadoEn;

    @Column(name = "retirado_en")
    private LocalDateTime retiradoEn;

    @ManyToOne
    @JoinColumn(name = "usuario_registra_id")
    private Usuario usuarioRegistra;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}