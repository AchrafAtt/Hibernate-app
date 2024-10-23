import org.example.entities.Salle;
import org.example.service.SalleService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SalleServiceTest {

    private SalleService salleService;
    private Salle salle;

    @Before
    public void setUp() {
        salleService = new SalleService();
        salle = new Salle();
        salle.setCode("A101"); // Exemple de code pour la salle

        // Créer et persister la salle avant chaque test
        salleService.create(salle);
    }

    @After
    public void tearDown() {
        // Supprimer la salle après chaque test si elle existe
        Salle foundSalle = salleService.findById(salle.getId());
        if (foundSalle != null) {
            salleService.delete(foundSalle);
        }
    }

    @Test
    public void testCreate() {
        assertNotNull("Salle should have been created with an ID", salle.getId());
    }

    @Test
    public void testFindById() {
        Salle foundSalle = salleService.findById(salle.getId());
        assertNotNull("Salle should be found", foundSalle);
        assertEquals("Found salle should match", salle.getCode(), foundSalle.getCode());
    }

    @Test
    public void testUpdate() {
        salle.setCode("B202"); // Modifiez le code pour tester la mise à jour
        boolean result = salleService.update(salle);
        assertTrue("Salle should be updated successfully", result);

        Salle updatedSalle = salleService.findById(salle.getId());
        assertEquals("Updated salle code should match", "B202", updatedSalle.getCode());
    }

    @Test
    public void testDelete() {
        boolean result = salleService.delete(salle);
        assertTrue("Salle should be deleted successfully", result);
    }

    @Test
    public void testFindAll() {
        assertTrue("Salle list should not be empty", salleService.findAll().size() > 0);
    }





}