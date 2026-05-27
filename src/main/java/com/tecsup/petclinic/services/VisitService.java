package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;

public interface VisitService {

    Visit findById(Integer id) throws VisitNotFoundException;

    Visit create(Visit visit);

    Visit update(Visit visit);

    void delete(Integer id) throws VisitNotFoundException;
}