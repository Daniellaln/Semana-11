package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

	@Autowired
	private OwnerService ownerService;

	@Test
	public void testFindOwnerById() {
		Integer ID = 1;
		String NOMBRE_ESPERADO = "George";

		Owner owner = null;
		try {
			owner = this.ownerService.findById(ID);
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}
		log.info("OWNER ENCONTRADO: " + owner);
		assertEquals(NOMBRE_ESPERADO, owner.getFirstName());
	}

	@Test
	public void testCreateOwner() {
		String FIRST_NAME = "Maria";
		String LAST_NAME = "Lopez";

		Owner owner = new Owner();
		owner.setFirstName(FIRST_NAME);
		owner.setLastName(LAST_NAME);
		owner.setAddress("Av. Lima 123");
		owner.setCity("Lima");
		owner.setTelephone("999111222");

		Owner nuevoOwner = this.ownerService.create(owner);
		log.info("OWNER CREADO: " + nuevoOwner);

		assertNotNull(nuevoOwner.getId());
		assertEquals(FIRST_NAME, nuevoOwner.getFirstName());
		assertEquals(LAST_NAME, nuevoOwner.getLastName());
	}

	@Test
	public void testUpdateOwner() {
		String FIRST_NAME = "Pedro";
		String LAST_NAME = "Garcia";
		String NEW_LAST_NAME = "Garcia2";

		Owner owner = new Owner();
		owner.setFirstName(FIRST_NAME);
		owner.setLastName(LAST_NAME);
		owner.setAddress("Av. Arequipa 456");
		owner.setCity("Arequipa");
		owner.setTelephone("888333444");

		Owner ownerCreado = this.ownerService.create(owner);
		log.info("OWNER CREADO: " + ownerCreado);

		ownerCreado.setLastName(NEW_LAST_NAME);
		Owner ownerActualizado = this.ownerService.update(ownerCreado);
		log.info("OWNER ACTUALIZADO: " + ownerActualizado);

		assertEquals(NEW_LAST_NAME, ownerActualizado.getLastName());
	}

	@Test
	public void testDeleteOwner() {
		String FIRST_NAME = "Rosa";
		String LAST_NAME = "Flores";

		Owner owner = new Owner();
		owner.setFirstName(FIRST_NAME);
		owner.setLastName(LAST_NAME);
		owner.setAddress("Jr. Cusco 789");
		owner.setCity("Cusco");
		owner.setTelephone("777555666");

		Owner ownerCreado = this.ownerService.create(owner);
		log.info("OWNER CREADO PARA ELIMINAR: " + ownerCreado);

		try {
			this.ownerService.delete(ownerCreado.getId());
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}

		try {
			this.ownerService.findById(ownerCreado.getId());
			assertTrue(false);
		} catch (OwnerNotFoundException e) {
			assertTrue(true);
		}
	}
}