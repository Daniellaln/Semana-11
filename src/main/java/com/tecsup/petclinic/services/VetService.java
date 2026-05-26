package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exceptions.VetNotFoundException;

public interface VetService {

    Vet findById(Integer id) throws VetNotFoundException;

    Vet create(Vet vet);

    Vet update(Vet vet);

    void delete(Integer id) throws VetNotFoundException;
}