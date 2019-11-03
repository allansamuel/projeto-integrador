package exemplo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import exemplo.model.Comentario;
import exemplo.persistencia.ComentarioDAO;

@Controller    
@RequestMapping(path="/comentario") 
public class ComentarioController {


	private ComentarioDAO cDAO;

	@RequestMapping(value = "", method = RequestMethod.POST)

	public ResponseEntity<Comentario> cadastrar(@RequestBody Comentario comentario) {
		cDAO = new ComentarioDAO();
		Object objeto = comentario;
		comentario = (Comentario) cDAO.cadastrar(objeto);
		return new ResponseEntity<Comentario>(comentario, HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Comentario comentario){
		cDAO = new ComentarioDAO();
		Object objeto = comentario;
		cDAO.editar(objeto);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		cDAO = new ComentarioDAO();
		cDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> listaTodos() {

		cDAO = new ComentarioDAO();
		List<Object> listaComentario = cDAO.buscarTodos(); 
		
		if(!listaComentario.isEmpty()){
			return new ResponseEntity<List<Object>>(listaComentario, HttpStatus.OK);
			} else{
				return new ResponseEntity<List<Object>>( HttpStatus.NOT_FOUND);

			}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Comentario> buscarPorId(@PathVariable long id){
		
		cDAO = new ComentarioDAO();

		Comentario comentario = (Comentario) cDAO.buscarPorId(id); 
		
		if(comentario != null){
		return new ResponseEntity<Comentario>(comentario, HttpStatus.OK);
		} else{
			return new ResponseEntity<Comentario>( HttpStatus.NOT_FOUND);

		}
		
	}
}