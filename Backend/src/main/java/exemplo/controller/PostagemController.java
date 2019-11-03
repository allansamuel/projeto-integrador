package exemplo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import exemplo.model.Postagem;
import exemplo.persistencia.PostagemDAO;

@Controller    
@RequestMapping(path="/postagem") 
public class PostagemController {



	private PostagemDAO pDAO;

	@RequestMapping(value = "", method = RequestMethod.POST)

	public ResponseEntity<Postagem> cadastrar(@RequestBody Postagem postagem) {
		pDAO = new PostagemDAO();
		Object objeto = (Object) postagem;
		postagem = (Postagem) pDAO.cadastrar(objeto);
		return new ResponseEntity<Postagem>(postagem, HttpStatus.CREATED);

	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Postagem postagem){
		pDAO = new PostagemDAO();
		Object objeto = (Object) postagem;
		pDAO.editar(objeto);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		pDAO = new PostagemDAO();
		pDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> listaTodos() {

		pDAO = new PostagemDAO();
		List<Object> listaPostagens = pDAO.buscarTodos(); 

		if(!listaPostagens.isEmpty()){
			return new ResponseEntity<List<Object>>(listaPostagens, HttpStatus.OK);
		} else{
			return new ResponseEntity<List<Object>>( HttpStatus.NOT_FOUND);

		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Postagem> buscarPorId(@PathVariable long id){

		pDAO = new PostagemDAO();

		Postagem postagem = (Postagem) pDAO.buscarPorId(id); 

		if(postagem != null){
			return new ResponseEntity<Postagem>(postagem, HttpStatus.OK);
		} else{
			return new ResponseEntity<Postagem>( HttpStatus.NOT_FOUND);

		}

	}
	
	@RequestMapping(value = "/buscar_por_usuario/{idUsuario}", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> buscarPorUsuario(@PathVariable long idUsuario){

		pDAO = new PostagemDAO();
		
		List<Object> listaPostagens = pDAO.buscarPorUsuario(idUsuario);

		if(!listaPostagens.isEmpty()){
			return new ResponseEntity<List<Object>>(listaPostagens, HttpStatus.OK);

		}else{
			return new ResponseEntity<List<Object>>( HttpStatus.NOT_FOUND);

		}


	}
	
	
	@RequestMapping(value = "/buscar_por_disciplina/{idDisciplina}", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> buscarPorDisciplina(@PathVariable long idDisciplina){

		pDAO = new PostagemDAO();
		
		List<Object> listaPostagens = pDAO.buscarPorDisciplina(idDisciplina);

		if(!listaPostagens.isEmpty()){
			return new ResponseEntity<List<Object>>(listaPostagens, HttpStatus.OK);

		}else{
			return new ResponseEntity<List<Object>>( HttpStatus.NOT_FOUND);

		}


	}

}