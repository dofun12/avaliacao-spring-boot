package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.List;
import java.util.NoSuchElementException;

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
		repository.saveAndFlush(tmp);
	}

	@Override
	public List<Estudante> buscarEstudantes() {
		return repository.findAll();
	}

	@Override
	public Estudante buscarEstudante(Integer id) {
		return repository.findById(id).get();
	}

	@Override
	public void deletar(Integer id) {
		try {
			Estudante tmp = repository.findById(id).get();
			repository.delete(tmp);
		}catch (NoSuchElementException nex){
			System.out.println("Elemento do id: "+id+" não encontrado");
		}catch (Exception ex){
			ex.printStackTrace();
		}


	}
}
