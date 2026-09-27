package co.edu.usbcali.autosusbcali.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "nombres", length = 100, nullable = false)
    private String nombres;

    @Column(name = "apellidos", length = 100, nullable = false)
    private String apellidos;

    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @Column(name = "celular", length = 20)
    private String celular;

    @Column(name = "password_hash", length = 255, nullable = false)
    private String passwordHash;

    @Column(name = "tipo", length = 10, nullable = false)
    private String tipo;

    @Column(name = "tipo_documento", length = 10, nullable = false)
    private String tipoDocumento;

    @Column(name = "numero_documento", length = 30, nullable = false)
    private String numeroDocumento;

    @Column(name = "email_verificado", nullable = false)
    private boolean emailVerificado;

    @Column(name = "celular_verificado", nullable = false)
    private boolean celularVerificado;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}