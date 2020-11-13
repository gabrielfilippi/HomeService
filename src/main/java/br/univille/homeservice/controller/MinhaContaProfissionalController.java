package br.univille.homeservice.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.univille.homeservice.model.Agenda;
import br.univille.homeservice.model.Profissional;
import br.univille.homeservice.service.AgendaService;
import br.univille.homeservice.service.ProfissionalService;
import br.univille.homeservice.viewmodel.Evento;

@Controller
@RequestMapping("/conta-profissional")
public class MinhaContaProfissionalController {
    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private AgendaService agendaService;

    Date dataAtual = new Date();

    long idProfissionalTeste = 1L;
    
    //pagina inicial do cliente
    @GetMapping("")
    public ModelAndView index(){

        Profissional profissional = profissionalService.getProfissional(idProfissionalTeste);
        return new ModelAndView("minha-conta-profissional/index", "profissional", profissional);
    }

    //carrega a página dos dados do cliente
    @GetMapping("dados")
    public ModelAndView dados(){
        
        Profissional profissional = profissionalService.getProfissional(idProfissionalTeste);
        return new ModelAndView("minha-conta-profissional/dados", "profissional", profissional);
    }

    @GetMapping("agenda")
    public ModelAndView agenda(){
        Profissional profissional = profissionalService.getProfissional(idProfissionalTeste);

        return new ModelAndView("minha-conta-profissional/agenda", "profissional", profissional);
    }

    @GetMapping(value="/carregarAgenda/{idProfissional}")
	public @ResponseBody List<Evento> GetEventos(@PathVariable("idProfissional") Profissional profissional){

        List<Agenda> eventos = agendaService.getAgenda(profissional.getId());
                
        List<Evento> listEvento = new ArrayList<>();
        for(Agenda item:eventos){
            listEvento.add(new Evento(item.getTitulo(),item.getData(),item.getDataFinal()));
        }
		String mesAtual = String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+ 1);
		
		if(mesAtual.length() <2){
			mesAtual = "0" + mesAtual;
        }
		
		String anoAtual = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
				
        /*ADICIONANDO OS EVENTOS*/
        /*
		eventos.add(new Agenda("Estudar Linux",     anoAtual+"-"+mesAtual+"-02T12:00:00",anoAtual+"-"+mesAtual+"-02T13:30:00", null));
		eventos.add(new Agenda("Estudar Java", 	   anoAtual+"-"+mesAtual+"-02T14:00:00",anoAtual+"-"+mesAtual+"-02T14:30:00", null));
		eventos.add(new Agenda("Estudar C#", 	   anoAtual+"-"+mesAtual+"-02T15:00:00",anoAtual+"-"+mesAtual+"-02T15:30:00", null));
		eventos.add(new Agenda("Estudar SOA Suite", anoAtual+"-"+mesAtual+"-02T16:00:00",anoAtual+"-"+mesAtual+"-02T17:30:00", null));
		eventos.add(new Agenda("Estudar Jquery",    anoAtual+"-"+mesAtual+"-02T19:00:00",anoAtual+"-"+mesAtual+"-02T20:30:00", null));		
		eventos.add(new Agenda("Correr",     	   anoAtual+"-"+mesAtual+"-03T13:00:00",anoAtual+"-"+mesAtual+"-03T13:30:00", null));
		eventos.add(new Agenda("Reunião",	       anoAtual+"-"+mesAtual+"-05T12:00:00",anoAtual+"-"+mesAtual+"-05T13:30:00", null));		
		eventos.add(new Agenda("Dois dias de evento", anoAtual+"-"+mesAtual+"-07T12:00:00",anoAtual+"-"+mesAtual+"-08T12:00:00", null));
		
		eventos.add(new Agenda("Publicar Artigo",   anoAtual+"-"+mesAtual+"-10T12:00:00",anoAtual+"-"+mesAtual+"-10T13:30:00", null));
		eventos.add(new Agenda("Reunião",	       anoAtual+"-"+mesAtual+"-10T15:00:00",anoAtual+"-"+mesAtual+"-10T18:30:00", null));		
		
		eventos.add(new Agenda("Festa",  		   anoAtual+"-"+mesAtual+"-13T12:00:00",anoAtual+"-"+mesAtual+"-13T13:30:00", null));
		eventos.add(new Agenda("Festa 2",	       anoAtual+"-"+mesAtual+"-13T15:00:00",anoAtual+"-"+mesAtual+"-13T18:30:00", null));		
		eventos.add(new Agenda("Curso de Inglês",   anoAtual+"-"+mesAtual+"-15",null, null));				
		eventos.add(new Agenda("Blog Cícero",       anoAtual+"-"+mesAtual+"-23",null, "http://www.ciceroednilson.com.br"));
        */
        
		return listEvento;
		
	}
}
