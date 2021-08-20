package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.DAO.AlunoDAO;
import br.com.academy.model.Aluno;

@Controller
public class AlunoController {

	//@Autowired é a injeção de dependencia
	@Autowired
	private AlunoDAO alunorepositorio;
	
	@GetMapping("/inserirAlunos")
	public ModelAndView InsertAlunos(Aluno aluno) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("alunos/formAluno");	//View de retorno	
		mv.addObject("aluno",new Aluno()); //retorna a variavel aluno e instancia um novo objeto aluno 
	
		return mv;
	}
	
	//Duvida: Por que utilizar "InsertAlunos", sendo o mesmo o nome do método anterior?
	//Resposta: observar que a action do formulario é o nome do método e ao enviarmos o formulario estamos fazendo um post
	//logo no postmapping deve conter a action, que no caso é o metodo.
	@PostMapping("/InsertAlunos")
	public ModelAndView inserirAluno(Aluno aluno) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/alunos-adicionados");
		alunorepositorio.save(aluno);
		return mv;
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("alunos/alterar");
		Aluno aluno = alunorepositorio.getOne(id);
		mv.addObject("aluno", aluno);
		return mv;
	}
	
	//lembrar que o path deve ser o nome da action no form
	@PostMapping("alterar")
	public ModelAndView alterar(Aluno aluno) {
		ModelAndView mv = new ModelAndView();
		alunorepositorio.save(aluno);
		mv.setViewName("redirect:/alunos-adicionados");
		return mv;
	}
	
	@GetMapping("alunos-adicionados")
	public ModelAndView listagemAlunos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("alunos/listaAlunos");
		mv.addObject("alunosList", alunorepositorio.findAll());
		return mv;
	}

	;
	
}
