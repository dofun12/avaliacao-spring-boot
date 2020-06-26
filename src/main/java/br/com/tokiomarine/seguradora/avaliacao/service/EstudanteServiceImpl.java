package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO Efetue a implementação dos métodos da classe service
@Service
public class EstudanteServiceImpl implements EstudandeService {

    @Autowired
    private EstudanteRepository repository;

    @Override
    public void cadastrarEstudante(@Valid Estudante estudante) {
        repository.saveAndFlush(estudante);
    }

    @Override
    public void atualizarEstudante(@Valid Estudante estudante) {
        Estudante tmp = repository.getById(estudante.getId()).get();
        tmp.setNome(estudante.getNome());
        tmp.setEmail(estudante.getEmail());
        tmp.setTelefone(estudante.getTelefone());
        tmp.setMatricula(estudante.getMatricula());
        tmp.setCurso(estudante.getCurso());
        repository.saveAndFlush(tmp);
    }

    @Override
    public List<Estudante> buscarEstudantes() {
        return repository.findAll();
    }

    @Override
    public Estudante buscaPorMatricula(String matricula) {
        return repository.getByMatricula(matricula).orElse(null);
    }

    @Override
    public Estudante buscarEstudante(Integer id) {
		Optional<Estudante> optional = repository.findById(id);
		return optional.orElse(null);

	}

    @Override
    public void deletar(Integer id) {
        Optional<Estudante> optional = repository.findById(id);
        if (optional.isPresent()) {
            Estudante tmp = optional.get();
            repository.delete(tmp);
        }

    }
}
