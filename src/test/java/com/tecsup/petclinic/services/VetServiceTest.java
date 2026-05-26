package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exceptions.VetNotFoundException;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class VetServiceTest {

    @Autowired
    private VetService vetService;

    @Test
    public void testFindVetById() {
        Integer ID = 1;
        String NOMBRE_ESPERADO = "James";

        Vet vet = null;
        try {
            vet = this.vetService.findById(ID);
        } catch (VetNotFoundException e) {
            fail(e.getMessage());
        }
        log.info("VET ENCONTRADO: " + vet);
        assertEquals(NOMBRE_ESPERADO, vet.getFirstName());
    }

    @Test
    public void testCreateVet() {
        String FIRST_NAME = "Carlos";
        String LAST_NAME = "Perez";

        Vet vet = new Vet();
        vet.setFirstName(FIRST_NAME);
        vet.setLastName(LAST_NAME);
        vet.setEmail("carlos@test.com");
        vet.setPhone("999888777");
        vet.setActive(true);

        Vet nuevoVet = this.vetService.create(vet);
        log.info("VET CREADO: " + nuevoVet);

        assertNotNull(nuevoVet.getId());
        assertEquals(FIRST_NAME, nuevoVet.getFirstName());
        assertEquals(LAST_NAME, nuevoVet.getLastName());
    }

    @Test
    public void testUpdateVet() {
        String FIRST_NAME = "Ana";
        String LAST_NAME = "Torres";
        String NEW_LAST_NAME = "Torres2";

        Vet vet = new Vet();
        vet.setFirstName(FIRST_NAME);
        vet.setLastName(LAST_NAME);
        vet.setEmail("ana@test.com");
        vet.setPhone("111222333");
        vet.setActive(true);

        Vet vetCreado = this.vetService.create(vet);
        log.info("VET CREADO: " + vetCreado);

        vetCreado.setLastName(NEW_LAST_NAME);
        Vet vetActualizado = this.vetService.update(vetCreado);
        log.info("VET ACTUALIZADO: " + vetActualizado);

        assertEquals(NEW_LAST_NAME, vetActualizado.getLastName());
    }

    @Test
    public void testDeleteVet() {
        String FIRST_NAME = "Luis";
        String LAST_NAME = "Gomez";

        Vet vet = new Vet();
        vet.setFirstName(FIRST_NAME);
        vet.setLastName(LAST_NAME);
        vet.setEmail("luis@test.com");
        vet.setPhone("444555666");
        vet.setActive(true);

        Vet vetCreado = this.vetService.create(vet);
        log.info("VET CREADO PARA ELIMINAR: " + vetCreado);

        try {
            this.vetService.delete(vetCreado.getId());
        } catch (VetNotFoundException e) {
            fail(e.getMessage());
        }

        try {
            this.vetService.findById(vetCreado.getId());
            assertTrue(false);
        } catch (VetNotFoundException e) {
            assertTrue(true);
        }
    }
}