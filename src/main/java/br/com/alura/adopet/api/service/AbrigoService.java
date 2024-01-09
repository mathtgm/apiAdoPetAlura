package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.abrigo.CadastraAbrigoDto;
import br.com.alura.adopet.api.dto.abrigo.CadastroPetAbrigoDto;
import br.com.alura.adopet.api.dto.abrigo.ListaPetsDoAbrigoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.validacoes.abrigo.ValidacaoCadastroAbrigo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbrigoService {

    @Autowired
    private List<ValidacaoCadastroAbrigo> validacaoCadastroAbrigo;

    @Autowired
    private AbrigoRepository abrigoRepository;

    public void cadastrarAbrigo(CadastraAbrigoDto abrigoDto) {

        validacaoCadastroAbrigo.forEach(s -> s.verifica(abrigoDto));

        Abrigo abrigo = new Abrigo(abrigoDto.nome(), abrigoDto.telefone(), abrigoDto.email());
        this.abrigoRepository.save(abrigo);
    }

    public List<Abrigo> listaAbrigos() {
        return abrigoRepository.findAll();
    }

    public List<Pet> listarPets(ListaPetsDoAbrigoDto listaPetsDoAbrigoDto) {

        Abrigo abrigo = procuraAbrigoPorNomeOuId(listaPetsDoAbrigoDto.abrigo());

        List<Pet> pets = abrigo.getPets();

        return pets;

    }

    public void cadastrarPetsAbrigo(String nomeOuId, CadastroPetAbrigoDto cadastroPetDto) {

        Abrigo abrigo = procuraAbrigoPorNomeOuId(nomeOuId);

        abrigo.cadastraPetAbrigo(cadastroPetDto);
        abrigoRepository.save(abrigo);

    }

    public Abrigo procuraAbrigoPorNomeOuId(String valor) {
        Abrigo abrigo;
        try {
            Long id = Long.parseLong(valor);
            abrigo = abrigoRepository.getReferenceById(id);
        } catch (NumberFormatException erro) {
            abrigo = abrigoRepository.findByNome(valor);
        }

        return abrigo;

    }
}
