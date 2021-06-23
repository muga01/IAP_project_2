package com.IAP.car_exchange.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;

import java.util.Date;


@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Getter
    @Setter
    @Id
    @NotNull
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Min(40000)
    @Max(60000)
    private long id;
    

    @Getter
    @Setter
    @Column(name = "first_name")
    private String firstName;

    @Getter
    @Setter
    @Column(name = "middle_name")
    private String middleName;

    @Getter
    @Setter
    @Column(name = "surname")
    private String surname;

    @Getter
    @Setter
    @Size(min = 10, max = 12)
    @Column(name = "pesel")
    private String pesel;

    @Getter
    @Setter
    @Column(name = "gender")
    private char gender;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    @Getter
    @Setter
    //@OneToOne(cascade = CascadeType.ALL,orphanRemoval=true)
    //@JoinColumn(name = "office_id", referencedColumnName = "id")
    //private Office officeId;
    //@NotNull
    //@Column(name = "office_id")
    //private Long officeId;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@Cascade( { org.hibernate.annotations.CascadeType.ALL } )
    @JoinColumn(name = "office_id", nullable = false, referencedColumnName = "id")
    private Office officeId;
    
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@Cascade( { org.hibernate.annotations.CascadeType.ALL } )
    @JoinColumn(name = "role_id", nullable = false, referencedColumnName = "id")
    private Role roleId;
    
    @Getter
    @Setter
    @Column(name = "sync")
    private Boolean sync;
    

    public User(String firstName, String middleName, String surname, String pesel, char gender, Date birthDate,Role roleId,Office officeId){
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = surname;
        this.pesel = pesel;
        this.gender = gender;
        this.birthDate = birthDate;
        this.roleId = roleId;
        this.officeId = officeId;
    }
}
