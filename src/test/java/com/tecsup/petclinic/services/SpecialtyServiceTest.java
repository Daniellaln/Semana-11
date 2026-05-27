package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exceptions.SpecialtyNotFoundException;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class SpecialtyServiceTest {

	@Autowired
	private SpecialtyService specialtyService;

	@Test
	public void testFindSpecialtyById() {
		Integer ID = 1;
		String NOMBRE_ESPERADO = "radiology";

		Specialty specialty = null;
		try {
			specialty = this.specialtyService.findById(ID);
		} catch (SpecialtyNotFoundException e) {
			fail(e.getMessage());
		}
		log.info("SPECIALTY ENCONTRADA: " + specialty);
		assertEquals(NOMBRE_ESPERADO, specialty.getName());
	}

	@Test
	public void testCreateSpecialty() {
		String NAME = "cardiology";
		String OFFICE = "Block A";

		Specialty specialty = new Specialty();
		specialty.setName(NAME);
		specialty.setOffice(OFFICE);
		specialty.setHOpen(8);
		specialty.setHClose(17);

		Specialty nuevaSpecialty = this.specialtyService.create(specialty);
		log.info("SPECIALTY CREADA: " + nuevaSpecialty);

		assertNotNull(nuevaSpecialty.getId());
		assertEquals(NAME, nuevaSpecialty.getName());
		assertEquals(OFFICE, nuevaSpecialty.getOffice());
	}

	@Test
	public void testUpdateSpecialty() {
		String NAME = "neurology";
		String OFFICE = "Block B";
		String NEW_OFFICE = "Block C";

		Specialty specialty = new Specialty();
		specialty.setName(NAME);
		specialty.setOffice(OFFICE);
		specialty.setHOpen(9);
		specialty.setHClose(18);

		Specialty specialtyCreada = this.specialtyService.create(specialty);
		log.info("SPECIALTY CREADA: " + specialtyCreada);

		specialtyCreada.setOffice(NEW_OFFICE);
		Specialty specialtyActualizada = this.specialtyService.update(specialtyCreada);
		log.info("SPECIALTY ACTUALIZADA: " + specialtyActualizada);

		assertEquals(NEW_OFFICE, specialtyActualizada.getOffice());
	}

	@Test
	public void testDeleteSpecialty() {
		String NAME = "oncology";
		String OFFICE = "Block D";

		Specialty specialty = new Specialty();
		specialty.setName(NAME);
		specialty.setOffice(OFFICE);
		specialty.setHOpen(7);
		specialty.setHClose(15);

		Specialty specialtyCreada = this.specialtyService.create(specialty);
		log.info("SPECIALTY CREADA PARA ELIMINAR: " + specialtyCreada);

		try {
			this.specialtyService.delete(specialtyCreada.getId());
		} catch (SpecialtyNotFoundException e) {
			fail(e.getMessage());
		}

		try {
			this.specialtyService.findById(specialtyCreada.getId());
			assertTrue(false);
		} catch (SpecialtyNotFoundException e) {
			assertTrue(true);
		}
	}
}