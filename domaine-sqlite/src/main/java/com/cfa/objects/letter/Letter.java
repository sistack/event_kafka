package com.cfa.objects.letter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "letter")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "message")
    private String message;

    @Column(name = "creationDate")
    private Date creationDate;

    @Column(name = "treatmentDate")
    private Date treatmentDate;
}
