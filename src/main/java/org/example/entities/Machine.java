package org.example.entities;


import javax.persistence.*;
import java.util.Date;


@Entity
@NamedNativeQuery(
        name = "findBetweenDateNative",
        query = "SELECT * FROM Machine WHERE date BETWEEN :date1 AND :date2",
        resultClass = Machine.class
)
@NamedQuery(
        name = "findBetweenDate",
        query = "SELECT m FROM Machine m WHERE m.dateAchat BETWEEN :date1 AND :date2"
)

public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ref;

    @Temporal(TemporalType.DATE)
    private Date dateAchat;

    @ManyToOne
    private Salle salle;

    public Machine(int id, String ref, Date dateAchat, Salle salle) {
        this.id = id;
        this.ref = ref;
        this.dateAchat = dateAchat;
        this.salle = salle;
    }

    public Machine(String m1, Date date, Salle byId) {
        this.ref = m1;
        this.dateAchat = date;
        this.salle = byId;

    }

    public Machine() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }
}
