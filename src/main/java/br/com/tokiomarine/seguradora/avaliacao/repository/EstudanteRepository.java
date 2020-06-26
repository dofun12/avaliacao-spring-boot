package br.com.tokiomarine.seguradora.avaliacao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;

@Repository
public interface EstudanteRepository extends JpaRepository<Estudante,Integer> {

	List<Estudante> findByNome(String nome);

	Optional<Estudante> getById(Integer id);

}
