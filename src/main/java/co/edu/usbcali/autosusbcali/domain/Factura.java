package co.edu.usbcali.autosusbcali.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "facturas")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @Column(name = "numero_factura", length = 50, nullable = false)
    private String numeroFactura;

    @Column(name = "cufe", length = 255)
    private String cufe;

    @Column(name = "url_documento", length = 500)
    private String urlDocumento;

    @Column(name = "subtotal", nullable = false)
    private BigDecimal subtotal;

    @Column(name = "iva", nullable = false)
    private BigDecimal iva;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime fechaEmision;
}