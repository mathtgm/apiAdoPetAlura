package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @GetMapping
    public List<Pet> listarTodosPetsNaoAdotados() {
        List<Pet> pets = petRepository.findAll();

        return pets.stream().filter(pet -> !pet.getAdotado()).toList();
    }
}
