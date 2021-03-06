package com.example.myHorseServer.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event_list")

public class EventList {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="event_list_id", nullable = false, unique=true)
    private Integer eventListId;

    @OneToOne
    @JoinColumn(name = "horse_id")
    private Horse horse;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name="gamer_id")
    private Integer gamer;
}
