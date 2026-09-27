package co.edu.usbcali.autosusbcali.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "busquedas_guardadas")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class BusquedaGuardada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "criterios", nullable = false)
    private String criterios;

    @Column(name = "alerta_activa", nullable = false)
    private boolean alertaActiva;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
}