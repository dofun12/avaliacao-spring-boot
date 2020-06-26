package br.com.tokiomarine.seguradora.avaliacao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudandeService;

import java.util.regex.Matcher;

@Controller
@RequestMapping("/estudantes/")
public class EstudanteController {

	@Autowired
	private EstudandeService service;

	@GetMapping("criar")
	public String iniciarCastrado(Estudante estudante) {
		return "cadastrar-estudante";
	}

	@GetMapping("listar")
	public String listarEstudantes(Model model) {
		model.addAttribute("estudantes", service.buscarEstudantes());
		return "index";
	}

	private String doValidationEstudante(String target, Estudante estudante, Model model){

		if((estudante.getMatricula()==null || estudante.getMatricula().isEmpty())){
			return retornarErroCadastro(target,"A matricula deve ser preechida! ",model);
		}
		if(estudante.getMatricula().length()>255){
			return retornarErroCadastro(target,"A mátricula deve ter no máximo 255 caracteres! ",model);
		}



		if(estudante.getEmail()!=null && estudante.getEmail().length()>255){
			return retornarErroCadastro(target,"O Email deve ter no máximo 255 caracteres! ",model);
		}
		if(estudante.getEmail()!=null && !estudante.getEmail().isEmpty()){
			if(!estudante.getEmail().contains("@") || !estudante.getEmail().contains(".com")){
				return retornarErroCadastro(target,"O Email inserido é inválido! ",model);
			}
		}

		if(estudante.getNome()!=null && estudante.getNome().length()>255){
			return retornarErroCadastro(target,"O Nome deve ter no máximo 255 caracteres! ",model);
		}

		if(estudante.getTelefone()!=null && estudante.getTelefone().length()>20){
			return retornarErroCadastro(target,"O Telefone deve ter no máximo 20 caracteres! ",model);
		}
		if(estudante.getCurso()!=null && estudante.getCurso().length()>255){
			return retornarErroCadastro(target,"O Curso deve ter no máximo 255 caracteres! ",model);
		}

		return null;
	}

	private String retornarErroCadastro(String target,String message, Model model){
		model.addAttribute("message", message);
		return target;
	}

	@PostMapping("add")
	public String adicionarEstudante(@Valid Estudante estudante, BindingResult result, Model model) {
		String resposta = doValidationEstudante("cadastrar-estudante", estudante,model);
		if(resposta!=null){
			return resposta;
		}

		Estudante tmp = service.buscaPorMatricula(estudante.getMatricula());
		if(tmp!=null){
			return retornarErroCadastro("cadastrar-estudante","Essa matricula já existe! ",model);
		}

		if (result.hasErrors()) {
			return retornarErroCadastro("cadastrar-estudante","Erros ao gravar! ",model);
		}
		service.cadastrarEstudante(estudante);

		return "redirect:listar";
	}

	@GetMapping("editar/{id}")
	public String exibirEdicaoEstudante(@PathVariable("id") Integer id, Model model) {
		Estudante estudante = service.buscarEstudante(id);
		if(estudante!=null){
			model.addAttribute("estudante", estudante);
			return "atualizar-estudante";
		}else{
			return "redirect:../listar";
		}

	}

	@PostMapping("atualizar/{id}")
	public String atualizarEstudante(@PathVariable("id") Integer id, @Valid Estudante estudante, BindingResult result, Model model) {

		String resposta = doValidationEstudante("atualizar-estudante", estudante,model);
		if(resposta!=null){
			return resposta;
		}
		if (result.hasErrors()) {
			// estudante.setId(id);
			return "atualizar-estudante";
		}
		service.atualizarEstudante(estudante);

		model.addAttribute("estudantes", service.buscarEstudantes());
		return "index";
	}

	@GetMapping("apagar/{id}")
	public String apagarEstudante(@PathVariable("id") Integer id, Model model) {
		// TODO IMPLEMENTAR A EXCLUSAO DE ESTUDANTES
		service.deletar(id);
		model.addAttribute("estudantes", service.buscarEstudantes());
		return "index";
	}
}
