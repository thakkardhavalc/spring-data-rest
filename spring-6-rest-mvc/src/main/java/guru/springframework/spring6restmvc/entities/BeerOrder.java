package guru.springframework.spring6restmvc.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

/**
 * Created By dhaval on 2023-06-13
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@Builder
public class BeerOrder {

    public BeerOrder(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, String customerRef,
                     Customer customer, BeerOrderShipment beerOrderShipment, Set<BeerOrderLine> beerOrderLines) {
        this.id = id;
        this.version = version;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.customerRef = customerRef;
        this.setCustomer(customer);
        this.beerOrderLines = beerOrderLines;
        this.setBeerOrderShipment(beerOrderShipment);
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @Version
    private Long version;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    public boolean isNew() {
        return this.id == null;
    }

    private String customerRef;

    @ManyToOne
    private Customer customer;

    @OneToOne(cascade = CascadeType.PERSIST)
    private BeerOrderShipment beerOrderShipment;

    public void setCustomer(Customer customer) {
        this.customer = customer;
        customer.getBeerOrders().add(this);
    }

    public void setBeerOrderShipment(BeerOrderShipment beerOrderShipment) {
        this.beerOrderShipment = beerOrderShipment;
        beerOrderShipment.setBeerOrder(this);
    }

    @OneToMany(mappedBy = "beerOrder")
    private Set<BeerOrderLine> beerOrderLines;

}
