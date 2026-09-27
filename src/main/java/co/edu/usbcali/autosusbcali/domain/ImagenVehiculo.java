package co.edu.usbcali.autosusbcali.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "imagenes_vehiculo")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ImagenVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @Column(name = "url", length = 500, nullable = false)
    private String url;

    @Column(name = "orden", nullable = false)
    private Short orden;

    @Column(name = "es_principal", nullable = false)
    private boolean esPrincipal;

    @Column(name = "fecha_carga", nullable = false)
    private LocalDateTime fechaCarga;
}