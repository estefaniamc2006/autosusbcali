package co.edu.usbcali.autosusbcali.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacciones_pasarela")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class TransaccionPasarela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "pago_id")
    private Pago pago;

    @Column(name = "proveedor", length = 50, nullable = false)
    private String proveedor;

    @Column(name = "referencia_pasarela", length = 100, nullable = false)
    private String referenciaPasarela;

    @Column(name = "estado_pasarela", length = 50)
    private String estadoPasarela;

    @Column(name = "monto")
    private BigDecimal monto;

    @Column(name = "respuesta_raw")
    private String respuestaRaw;

    @Column(name = "fecha_transaccion", nullable = false)
    private LocalDateTime fechaTransaccion;
}