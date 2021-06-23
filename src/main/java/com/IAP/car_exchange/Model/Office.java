package com.IAP.car_exchange.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "offices")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Office {
    @Getter
    @Setter
    @Id
    //@Size(max = 32)
    @NotNull
    @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Min(40)
    @Max(50)
    public long id;
    
    //@OneToMany(fetch = FetchType.LAZY)
    //@JoinColumn(name = "id", nullable = false, referencedColumnName = "office_id")
    //private User id;
    
    @Getter
    @Setter
    @Column(name = "city")
    public String city;

    @Getter
    @Setter
    @Column(name = "type")
    public String type;
    
    @Getter
    @Setter
    @Column(name = "address")
    public String address;
    
    @Getter
    @Setter
    @Column(name = "sync")
    public Boolean sync;
}
