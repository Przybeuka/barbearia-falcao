package br.com.unicuritiba.barbeariafalcao.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.unicuritiba.barbeariafalcao.models.Agenda;
import br.com.unicuritiba.barbeariafalcao.repositories.AgendaRepository;

@RestController
public class BarbeariaFalcaoAPIController {

    @Autowired
    private AgendaRepository repository;

	@GetMapping("/") public ModelAndView home() {
		
		List<Agenda> agenda = repository.findAll();
		
		ModelAndView view = new ModelAndView("index");
		view.addObject("agenda", agenda);
		
		return view;
	}
	
	@GetMapping("/agendar") public ModelAndView agendar() {	
		
		ModelAndView view = new ModelAndView("agendar");
		view.addObject("model", new Agenda());
		
		return view;
	}
	
	@GetMapping("/atualizar") public ModelAndView atualizar() {	
		
		ModelAndView view = new ModelAndView("atualizar");
		view.addObject("model", new Agenda());
		
		return view;
	}
	
	@PostMapping("/agendar")
    public String salvarHorario(Agenda agenda, Model model, BindingResult result) {

		repository.save(agenda);

        return "redirect:/";
    }
	
	
	//NÃO FUNCIONANDO AINDA
	
	//ATUALIZAR
	

	@GetMapping("/atualizar/{id}")
	public ModelAndView update(@PathVariable("id") long id) {
	
		Agenda agenda = repository.findById(id).get();
		ModelAndView view = new ModelAndView("update");
		view.addObject("agenda", agenda );
		return view;
	}
	
	@PutMapping("/atualizar/{id}")
	public String updateFighter(@PathVariable("id") long id, Agenda agenda, Model model, BindingResult result) {
		
		agenda.setId(id);	
		repository.save(agenda);
		return "redirect:/";	
	}

}
