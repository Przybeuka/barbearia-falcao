package br.com.unicuritiba.barbeariafalcao.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.unicuritiba.barbeariafalcao.models.Agenda;
import br.com.unicuritiba.barbeariafalcao.repositories.AgendaRepository;


@Controller
public class BarbeariaFalcaoAPIController {

    @Autowired
    private AgendaRepository repository;

    //MOSTRA LISTA DA AGENDA
    @GetMapping("/") 
    public ModelAndView home() {
        List<Agenda> agenda = repository.findAll();
        ModelAndView view = new ModelAndView("index");
        view.addObject("agenda", agenda);
        return view;
    }
   
    //MOSTRAR PAGINA AGENDAR
    @GetMapping("/agendar") 
    public ModelAndView agendar() {    
        ModelAndView view = new ModelAndView("agendar");
        view.addObject("model", new Agenda());
        return view;
    }
    
    //SALVA NA AGENDA
    @PostMapping("/agendar")
    public String salvarHorario(Agenda agenda, Model model, BindingResult result) {
        repository.save(agenda);
        return "redirect:/";
    }
    
    //MOSTRAR PAGINA ATUALIZAR
    @GetMapping("/atualizar") 
    public ModelAndView atualizar() {    
        List<Agenda> agenda = repository.findAll();
        ModelAndView view = new ModelAndView("atualizar");
        view.addObject("agenda", agenda);
        return view;
    }
    
    //PEGA PELO ID O CLIENTE NA AGENDA E DIRECIONA PARA REMARCAR
    @GetMapping("/remarcar/{id}")
    public ModelAndView atualizarCliente(@PathVariable("id") long id) {
        Agenda agenda = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        ModelAndView view = new ModelAndView("remarcar");
        view.addObject("agenda", agenda);
        return view;
    }
    
    //REMARCA NA AGENDA PELO ID O CLIENTE JA SELECIONADO
    @PutMapping("/remarcar/{id}")
    public String remarcarCliente(@PathVariable("id") long id, @ModelAttribute Agenda agenda, Model model) {
        Agenda agendamentoExistente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));

        agendamentoExistente.setNome(agenda.getNome());
        agendamentoExistente.setTelefone(agenda.getTelefone());
        agendamentoExistente.setDia(agenda.getDia());
        agendamentoExistente.setHorario(agenda.getHorario());
        agendamentoExistente.setBarbeiro(agenda.getBarbeiro());
        agendamentoExistente.setServico(agenda.getServico());

        repository.save(agendamentoExistente);

        return "redirect:/";
    }
    
    //DELETA O AGENDAMENTO PELO ID DA TABELA
    @DeleteMapping("/{id}")
    public String feito(@PathVariable("id") long id) {
        repository.deleteById(id);
        return "redirect:/"; 
    }

    
    //DELETA O AGENDAMENTO PELO ID DA TABELA
    @DeleteMapping("/remover/{id}")
    public String removeAgenda(@PathVariable("id") long id) {
        repository.deleteById(id);
        return "redirect:/"; 
    }
}
