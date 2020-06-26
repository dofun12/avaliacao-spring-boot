package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.List;

import javax.validation.Valid;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import org.springframework.stereotype.Service;


public interface EstudandeService {

	List<Estudante> buscarEstudantes();

	void cadastrarEstudante(@Valid Estudante estudante);

	Estudante buscarEstudante(Integer id);

	Estudante buscaPorMatricula(String matricula);

	void atualizarEstudante(@Valid Estudante estudante);

	void deletar(Integer id);
}
