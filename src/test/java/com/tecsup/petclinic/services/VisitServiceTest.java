package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;

@SpringBootTest
@Slf4j
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    @Test
    public void testFindVisitById() {
        Integer ID = 1;
        String DESCRIPCION_ESPERADA = "rabies shot";

        Visit visit = null;
        try {
            visit = this.visitService.findById(ID);
        } catch (VisitNotFoundException e) {
            fail(e.getMessage());
        }
        log.info("VISIT ENCONTRADA: " + visit);
        assertEquals(DESCRIPCION_ESPERADA, visit.getDescription());
    }

    @Test
    public void testCreateVisit() {
        String DESCRIPCION = "general checkup";
        Integer PET_ID = 1;
        Integer VET_ID = 1;

        Visit visit = new Visit();
        visit.setPetId(PET_ID);
        visit.setVetId(VET_ID);
        visit.setVisitDate(new Date());
        visit.setDescription(DESCRIPCION);
        visit.setCost(50.00);

        Visit nuevaVisit = this.visitService.create(visit);
        log.info("VISIT CREADA: " + nuevaVisit);

        assertNotNull(nuevaVisit.getId());
        assertEquals(DESCRIPCION, nuevaVisit.getDescription());
        assertEquals(PET_ID, nuevaVisit.getPetId());
    }

    @Test
    public void testUpdateVisit() {
        String DESCRIPCION = "dental cleaning";
        String NEW_DESCRIPCION = "dental cleaning updated";

        Visit visit = new Visit();
        visit.setPetId(1);
        visit.setVetId(1);
        visit.setVisitDate(new Date());
        visit.setDescription(DESCRIPCION);
        visit.setCost(100.00);

        Visit visitCreada = this.visitService.create(visit);
        log.info("VISIT CREADA: " + visitCreada);

        visitCreada.setDescription(NEW_DESCRIPCION);
        Visit visitActualizada = this.visitService.update(visitCreada);
        log.info("VISIT ACTUALIZADA: " + visitActualizada);

        assertEquals(NEW_DESCRIPCION, visitActualizada.getDescription());
    }

    @Test
    public void testDeleteVisit() {
        String DESCRIPCION = "vaccination";

        Visit visit = new Visit();
        visit.setPetId(1);
        visit.setVetId(1);
        visit.setVisitDate(new Date());
        visit.setDescription(DESCRIPCION);
        visit.setCost(75.00);

        Visit visitCreada = this.visitService.create(visit);
        log.info("VISIT CREADA PARA ELIMINAR: " + visitCreada);

        try {
            this.visitService.delete(visitCreada.getId());
        } catch (VisitNotFoundException e) {
            fail(e.getMessage());
        }

        try {
            this.visitService.findById(visitCreada.getId());
            assertTrue(false);
        } catch (VisitNotFoundException e) {
            assertTrue(true);
        }
    }
}