package exemplo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import exemplo.model.Turma;
import exemplo.persistencia.TurmaDAO;

@Controller    
@RequestMapping(path="/turma") 
public class TurmaController {


	private TurmaDAO tDAO;

	@RequestMapping(value = "", method = RequestMethod.POST)

	public ResponseEntity<Turma> cadastrar(@RequestBody Turma turma) {
		tDAO = new TurmaDAO();
		Object objeto = turma;
		turma = (Turma) tDAO.cadastrar(objeto);
		return new ResponseEntity<Turma>(turma, HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Turma turma){
		tDAO = new TurmaDAO();
		Object objeto = turma;
		tDAO.editar(objeto);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		tDAO = new TurmaDAO();
		tDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> listaTodos() {

		tDAO = new TurmaDAO();
		List<Object> listaTurmas = tDAO.buscarTodos(); 
		
		if(!listaTurmas.isEmpty()){
			return new ResponseEntity<List<Object>>(listaTurmas, HttpStatus.OK);
		} else{
			return new ResponseEntity<List<Object>>(HttpStatus.NOT_FOUND);

		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Turma> buscarPorId(@PathVariable long id){
		
		tDAO = new TurmaDAO();

		Turma turma = (Turma) tDAO.buscarPorId(id); 
		
		if(turma != null){
		return new ResponseEntity<Turma>(turma, HttpStatus.OK);
		} else{
			return new ResponseEntity<Turma>( HttpStatus.NOT_FOUND);

		}
		
	}
}
