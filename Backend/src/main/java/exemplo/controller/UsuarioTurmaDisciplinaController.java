package exemplo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import exemplo.model.UsuarioTurmaDisciplina;
import exemplo.persistencia.UsuarioTurmaDisciplinaDAO;

@Controller    
@RequestMapping(path="/usuario_turma_disciplina") 
public class UsuarioTurmaDisciplinaController {


	private UsuarioTurmaDisciplinaDAO utdDAO;

	@RequestMapping(value = "", method = RequestMethod.POST)

	public ResponseEntity<UsuarioTurmaDisciplina> cadastrar(@RequestBody UsuarioTurmaDisciplina usuarioTurmaDisciplina) {
		utdDAO = new UsuarioTurmaDisciplinaDAO();
		Object objeto = usuarioTurmaDisciplina;
		usuarioTurmaDisciplina = (UsuarioTurmaDisciplina) utdDAO.cadastrar(objeto);
		return new ResponseEntity<UsuarioTurmaDisciplina>(usuarioTurmaDisciplina, HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody UsuarioTurmaDisciplina usuarioTurmaDisciplina){
		utdDAO = new UsuarioTurmaDisciplinaDAO();

		Object objeto = usuarioTurmaDisciplina;
		utdDAO.editar(objeto);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		utdDAO = new UsuarioTurmaDisciplinaDAO();
		utdDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> listaTodos() {

		utdDAO = new UsuarioTurmaDisciplinaDAO();
		List<Object> listaUsuarioTurmaDisciplina = utdDAO.buscarTodos(); 
		
		if(!listaUsuarioTurmaDisciplina.isEmpty()){
			return new ResponseEntity<List<Object>>(listaUsuarioTurmaDisciplina, HttpStatus.OK);
			} else{
				return new ResponseEntity<List<Object>>( HttpStatus.NOT_FOUND);

			}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UsuarioTurmaDisciplina> buscarPorId(@PathVariable long id){
		
		utdDAO = new UsuarioTurmaDisciplinaDAO();

		UsuarioTurmaDisciplina usuarioTurmaDisciplina = (UsuarioTurmaDisciplina) utdDAO.buscarPorId(id); 
		
		if(usuarioTurmaDisciplina != null){
		return new ResponseEntity<UsuarioTurmaDisciplina>(usuarioTurmaDisciplina, HttpStatus.OK);
		} else{
			return new ResponseEntity<UsuarioTurmaDisciplina>( HttpStatus.NOT_FOUND);

		}
		
	}

	@RequestMapping(value = "/buscar_por_usuario/{idUsuario}", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> buscarPorUsuario(@PathVariable long idUsuario){


		utdDAO = new UsuarioTurmaDisciplinaDAO();

		List<Object> listaTurmasDisciplinasPorUsuarios = utdDAO.buscarTurmasEDisciplinasPorUsuario(idUsuario);



		if(!listaTurmasDisciplinasPorUsuarios.isEmpty()){
			return new ResponseEntity<List<Object>>(listaTurmasDisciplinasPorUsuarios, HttpStatus.OK);
		} else{
			return new ResponseEntity<List<Object>>(HttpStatus.NOT_FOUND);

		}
	}

}
