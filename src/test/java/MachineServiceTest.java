import org.example.entities.Machine;
import org.example.entities.Salle;
import org.example.service.MachineService;
import org.example.service.SalleService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class MachineServiceTest {
    private MachineService machineService;
    private SalleService salleService;

    @Before
    public void setUp() {
        machineService = new MachineService();
        salleService = new SalleService();

        Salle salle1 = new Salle("A1");
        Salle salle2 = new Salle("B2");

        salleService.create(salle1);
        salleService.create(salle2);

        Machine machine1 = new Machine("M1", new Date(), salleService.findById(1));
        Machine machine2 = new Machine("M2", new Date(), salleService.findById(2));

        machineService.create(machine1);
        machineService.create(machine2);



    }

    @After
    public void tearDown() {
        for (Machine machine : machineService.findAll()) {
            machineService.delete(machine);
        }

        for (Salle salle : salleService.findAll()) {
            salleService.delete(salle);
        }
        if (machineService.findAll().isEmpty() && salleService.findAll().isEmpty()) {
            System.out.println("All machines and salles have been deleted");
        }
    }

    @Test
    public void testCreate() {
        Machine machine = new Machine("M3", new Date(), salleService.findById(1));
        machineService.create(machine);
        assertNotNull("Machine should have been created with an ID", machine.getId());
    }

    @Test
    public void testFindById() {
        Machine machine = machineService.findById(1);
        assertNotNull("Machine should be found", machine);

    }

    @Test
    public void testUpdate() {
        Machine machine = machineService.findById(1);
        machine.setRef("M4");
        boolean result = machineService.update(machine);
        assertTrue("Machine should be updated successfully", result);

        Machine updatedMachine = machineService.findById(1);
        assertEquals("Updated machine ref should match", "M4", updatedMachine.getRef());
    }


    @Test
    public void testFindAll() {
        assertTrue("Machine list should not be empty", machineService.findAll().size() > 0);
    }

    @Test
    public void testFindBetweenDate() {
        Date d1 = new Date(2021, 1, 1);
        Date d2 = new Date();

        System.out.println("Machine entre deux dates" + d1 + " et " + d2 + " : ");

        for (Machine machine : machineService.findBetweenDateNative(d1, d2)) {
            System.out.println("Machine: " + machine.getRef());
        }
        assertTrue("Machine list should not be empty", machineService.findBetweenDateNative(d1, d2).size() > 0) ;
    }


    @Test
    public void testDelete() {
        Machine machine = machineService.findById(1);
        boolean result = machineService.delete(machine);
        assertTrue("Machine should be deleted successfully", result);

        Machine foundMachine = machineService.findById(1);
        assertNull("Machine should not be found after deletion", foundMachine);
    }




}
