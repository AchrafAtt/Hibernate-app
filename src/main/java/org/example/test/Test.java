package org.example.test;

import org.example.entities.Machine;
import org.example.entities.Salle;
import org.example.service.MachineService;
import org.example.service.SalleService;

import java.util.Date;

public class Test {

    public static void main(String[] args) {

        SalleService salleService = new SalleService();
        MachineService machineService = new MachineService();

        //creation et insertion
        Salle salle1 = new Salle("A1");
        Salle salle2 = new Salle("B2");

        salleService.create(salle1);
        salleService.create(salle2);

        //creation et insertion des machines
        Machine machine1 = new Machine("M1", new Date(), salleService.findById(1));
        Machine machine2 = new Machine("M2", new Date(), salleService.findById(2));

        machineService.create(machine1);
        machineService.create(machine2);
         //affichage
        for (Salle salle : salleService.findAll()) {
            System.out.println( "Salle:" + salle.getCode());
        }

        for (Machine machine : machineService.findAll()) {
            System.out.println("Machine: " + machine.getRef() );
        }

        //metode findBetweenDateNative

        Date d1 = new Date(2021, 1, 1);
        Date d2 = new Date();

        System.out.println("Machine entre deux dates" + d1 + " et " + d2 + " : ");

        for (Machine machine : machineService.findBetweenDateNative(d1, d2)) {
            System.out.println("Machine: " + machine.getRef());
        }

    }
}

