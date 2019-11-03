package exemplo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import exemplo.model.TurmaDisciplina;
import exemplo.persistencia.TurmaDisciplinaDAO;

@Controller    
@RequestMapping(path="/turma_disciplina") 
public class TurmaDisciplinaController {


	private TurmaDisciplinaDAO tdDAO;

	@RequestMapping(value = "", method = RequestMethod.POST)

	public ResponseEntity<TurmaDisciplina> cadastrar(@RequestBody TurmaDisciplina turmaDisciplina) {
		tdDAO = new TurmaDisciplinaDAO();
		Object objeto = turmaDisciplina;
		turmaDisciplina = (TurmaDisciplina) tdDAO.cadastrar(objeto);
		return new ResponseEntity<TurmaDisciplina>(turmaDisciplina, HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody TurmaDisciplina turmaDisciplina){
		tdDAO = new TurmaDisciplinaDAO();
		Object objeto = turmaDisciplina;
		tdDAO.editar(objeto);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		tdDAO = new TurmaDisciplinaDAO();
		tdDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> listaTodosComentario() {
	
		tdDAO = new TurmaDisciplinaDAO();
		List<Object> listaTurmaDisciplinas = tdDAO.buscarTodos(); 
		
		if(!listaTurmaDisciplinas.isEmpty()){
			return new ResponseEntity<List<Object>>(listaTurmaDisciplinas, HttpStatus.OK);
			} else{
				return new ResponseEntity<List<Object>>( HttpStatus.NOT_FOUND);

			}
	}

	@RequestMapping(value = "/disciplina/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> buscarPorIdDisciplina(@PathVariable long id){
		
		tdDAO = new TurmaDisciplinaDAO();

		List<Object> listaTurmaDisciplinas = tdDAO.buscarPorDisciplina(id); 
		
		if(!listaTurmaDisciplinas.isEmpty()){
			return new ResponseEntity<List<Object>>(listaTurmaDisciplinas, HttpStatus.OK);
			} else{
				return new ResponseEntity<List<Object>>( HttpStatus.NOT_FOUND);
			}
	}
}
