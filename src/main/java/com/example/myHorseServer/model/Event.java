package com.example.myHorseServer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event")

public class Event{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="event_id", nullable = false, unique=true)
    private Integer eventId;

    @Column(name="event_type", nullable = false)
    private Integer eventType;

    @Column(name="date", nullable = false)
    private Date date;

}
