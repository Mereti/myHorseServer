package com.example.myHorseServer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gamerStud")

public class GamerStud {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="gamerStudId", nullable = false, unique=true)
    private  Integer gamerStudId;

    @OneToOne
    @JoinColumn(name = "gamerId")
    private Gamer gamerId;

    @Column(name="gamerStudName", nullable = false)
    private String gamerStudName;

}
