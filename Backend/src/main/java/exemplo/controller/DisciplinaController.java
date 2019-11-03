package exemplo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import exemplo.model.Disciplina;
import exemplo.persistencia.DisciplinaDAO;

@Controller    
@RequestMapping(path="/disciplina") 
public class DisciplinaController {


	private DisciplinaDAO dDAO;

	@RequestMapping(value = "", method = RequestMethod.POST)

	public ResponseEntity<Disciplina> cadastrar(@RequestBody Disciplina disciplina) {
		dDAO = new DisciplinaDAO();
		Object objeto = disciplina;
		disciplina = (Disciplina) dDAO.cadastrar(objeto);
		return new ResponseEntity<Disciplina>(disciplina, HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Disciplina disciplina){
		dDAO = new DisciplinaDAO();
		Object objeto = disciplina;
		dDAO.editar(objeto);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		dDAO = new DisciplinaDAO();
		dDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> listaTodos() {

		dDAO = new DisciplinaDAO();
		List<Object> listaDisciplinas = dDAO.buscarTodos(); 
		
		if(!listaDisciplinas.isEmpty()){
			return new ResponseEntity<List<Object>>(listaDisciplinas, HttpStatus.OK);
			} else{
				return new ResponseEntity<List<Object>>( HttpStatus.NOT_FOUND);

			}
		}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Disciplina> buscarPorId(@PathVariable long id){
		
		dDAO = new DisciplinaDAO();

		Disciplina disciplina = (Disciplina) dDAO.buscarPorId(id);
		
		if(disciplina != null){
		return new ResponseEntity<Disciplina>(disciplina, HttpStatus.OK);
		} else{
			return new ResponseEntity<Disciplina>( HttpStatus.NOT_FOUND);

		}

	}
		@RequestMapping(value = "/{curso}/{serie}", method = RequestMethod.GET)
		public ResponseEntity<ArrayList<Disciplina>> buscarPorCursoESerie(@PathVariable String curso,@PathVariable int serie){

			dDAO = new DisciplinaDAO();

			ArrayList<Disciplina> listaDisciplinas = dDAO.buscarPorCursoESerie(curso, serie);

			if(listaDisciplinas != null){
				return new ResponseEntity<ArrayList<Disciplina>>(listaDisciplinas, HttpStatus.OK);

			}else{
				return new ResponseEntity<ArrayList<Disciplina>>( HttpStatus.NOT_FOUND);

			}			

	}
}
