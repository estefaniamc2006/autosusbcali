package co.edu.usbcali.autosusbcali.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "tipo", length = 50, nullable = false)
    private String tipo;

    @Column(name = "titulo", length = 200, nullable = false)
    private String titulo;

    @Column(name = "cuerpo")
    private String cuerpo;

    @Column(name = "canal", length = 10, nullable = false)
    private String canal;

    @Column(name = "leida", nullable = false)
    private boolean leida;

    @Column(name = "referencia_tipo", length = 50)
    private String referenciaTipo;

    @Column(name = "referencia_id")
    private Long referenciaId;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
}