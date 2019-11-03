package exemplo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import exemplo.model.UsuarioDisciplina;
import exemplo.persistencia.UsuarioDisciplinaDAO;

@Controller    
@RequestMapping(path="/usuario_disciplina") 
public class UsuarioDisciplinaController {


	private UsuarioDisciplinaDAO udDAO;

	@RequestMapping(value = "", method = RequestMethod.POST)

	public ResponseEntity<UsuarioDisciplina> cadastrar(@RequestBody UsuarioDisciplina usuarioDisciplina) {
		udDAO = new UsuarioDisciplinaDAO();
		Object objeto = usuarioDisciplina;
		usuarioDisciplina = (UsuarioDisciplina) udDAO.cadastrar(objeto);
		return new ResponseEntity<UsuarioDisciplina>(usuarioDisciplina, HttpStatus.CREATED);

	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody UsuarioDisciplina usuarioDisciplina){
		udDAO = new UsuarioDisciplinaDAO();
		Object objeto = usuarioDisciplina;
		udDAO.editar(objeto);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		udDAO = new UsuarioDisciplinaDAO();
		udDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> listaTodos() {

		udDAO = new UsuarioDisciplinaDAO();

		List<Object> listaDisciplinas = udDAO.buscarTodos();



		if(!listaDisciplinas.isEmpty()){
			return new ResponseEntity<List<Object>>(listaDisciplinas, HttpStatus.OK);
		} else{
			return new ResponseEntity<List<Object>>(HttpStatus.NOT_FOUND);

		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UsuarioDisciplina> buscarPorId(@PathVariable long id){

		udDAO = new UsuarioDisciplinaDAO();

		UsuarioDisciplina usuarioDisciplina = (UsuarioDisciplina) udDAO.buscarPorId(id); 

		if(usuarioDisciplina != null){
			return new ResponseEntity<UsuarioDisciplina>(usuarioDisciplina, HttpStatus.OK);
		} else{
			return new ResponseEntity<UsuarioDisciplina>( HttpStatus.NOT_FOUND);

		}

	}


	@RequestMapping(value = "/buscar_por_usuario/{idUsuario}", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> buscarDisciplinasPorUsuario(@PathVariable long idUsuario){


		udDAO = new UsuarioDisciplinaDAO();

		List<Object> listaDisciplinasPorUsuarios = udDAO.buscarDisciplinasPorUsuario(idUsuario);



		if(!listaDisciplinasPorUsuarios.isEmpty()){
			return new ResponseEntity<List<Object>>(listaDisciplinasPorUsuarios, HttpStatus.OK);
		} else{
			return new ResponseEntity<List<Object>>(HttpStatus.NOT_FOUND);

		}
	}
}