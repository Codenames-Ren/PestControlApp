package pestcontrol.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 *
 * @author ren
 */

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false)
    private Package pkg;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;
    
    @Column(name = "service_date", nullable = false)
    private LocalDateTime serviceDate;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    // ===== getter setter =====
    public Long getId() { return id; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public Package getPkg() { return pkg; }
    public void setPkg(Package pkg) { this.pkg = pkg; }

    public LocalDateTime getServiceDate() { return serviceDate; }
    public void setServiceDate(LocalDateTime serviceDate) { this.serviceDate = serviceDate; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
}
