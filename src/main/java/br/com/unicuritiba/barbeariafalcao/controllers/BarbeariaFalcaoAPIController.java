package br.com.unicuritiba.barbeariafalcao.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		
		List<Agenda> agenda = repository.findAll();
		
		ModelAndView view = new ModelAndView("atualizar");
		view.addObject("agenda", agenda);
		
		return view;
	}
	
	@PostMapping("/agendar")
    public String salvarHorario(Agenda agenda, Model model, BindingResult result) {

		repository.save(agenda);

        return "redirect:/";
    }
	
	
    @GetMapping("/remarcar/{id}")
    public ModelAndView atualizarCliente(@PathVariable("id") long id) {
        Agenda agenda = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        ModelAndView view = new ModelAndView("remarcar");
        view.addObject("agenda", agenda);
        return view;
    }
	
    
    @PutMapping("/remarcar/{id}")
    public String remarcarCliente(@PathVariable("id") long id, @ModelAttribute Agenda agenda, Model model) {
        // Recuperar o agendamento existente do banco de dados
        Agenda agendamentoExistente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        
        // Atualizar os detalhes do agendamento com os novos dados do formulário
        agendamentoExistente.setNome(agenda.getNome());
        agendamentoExistente.setTelefone(agenda.getTelefone());
        agendamentoExistente.setDia(agenda.getDia());
        agendamentoExistente.setHorario(agenda.getHorario());
        agendamentoExistente.setBarbeiro(agenda.getBarbeiro());
        agendamentoExistente.setServico(agenda.getServico());
        
        // Salvar as alterações no banco de dados
        repository.save(agendamentoExistente);
        
        // Redirecionar para a página inicial ou para onde desejar após a atualização
        return "redirect:/";
    }

	
	@DeleteMapping("/remover/{id}")
	public String removeAgenda(@PathVariable("id") long id) {
		repository.deleteById(id);
	    return "redirect:/"; 
	}
	
}
