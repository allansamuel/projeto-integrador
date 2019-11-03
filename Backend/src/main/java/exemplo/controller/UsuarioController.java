package exemplo.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import exemplo.model.Usuario;
import exemplo.persistencia.UsuarioDAO;

@Controller    
@RequestMapping(path="/usuario") 
public class UsuarioController {


	private UsuarioDAO uDAO;

	@RequestMapping(value = "", method = RequestMethod.POST)

	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
		uDAO = new UsuarioDAO();
		Object objeto = (Object) usuario;
		usuario = (Usuario) uDAO.cadastrar(objeto);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);

	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Usuario usuario){
		uDAO = new UsuarioDAO();
		Object objeto = (Object) usuario;
		uDAO.editar(objeto);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		uDAO = new UsuarioDAO();
		uDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> buscarTodos() {

		uDAO = new UsuarioDAO();

		List<Object> listaUsuarios = uDAO.buscarTodos();

		if(!listaUsuarios.isEmpty()){
			return new ResponseEntity<List<Object>>(listaUsuarios, HttpStatus.OK);
		} else{
			return new ResponseEntity<List<Object>>(HttpStatus.NOT_FOUND);

		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> buscarPorId(@PathVariable long id){


		Usuario usuario = (Usuario) uDAO.buscarPorId(id); 

		if(usuario != null){
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		} else{
			return new ResponseEntity<Usuario>( HttpStatus.NOT_FOUND);

		}

	}


	@RequestMapping(value = "/{email}/{senha}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> buscarPorLoginESenha(@PathVariable String email,@PathVariable String senha){

		uDAO = new UsuarioDAO();

		Usuario usuario = (Usuario) uDAO.buscarPorEmailESenha(email, senha); 

		if(usuario != null){
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

		}else{
			return new ResponseEntity<Usuario>( HttpStatus.NOT_FOUND);

		}


	}
	
	
	@RequestMapping(value = "/nome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> buscarPorNome(@PathVariable String nome){

		uDAO = new UsuarioDAO();

		List<Object> listaUsuarios = uDAO.buscarPorNome(nome);

		if(!listaUsuarios.isEmpty()){
			return new ResponseEntity<List<Object>>(listaUsuarios, HttpStatus.OK);

		}else{
			return new ResponseEntity<List<Object>>( HttpStatus.NOT_FOUND);

		}


	}
	
	
	@RequestMapping(value = "/email/{email}/", method = RequestMethod.GET)
	public  ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email){
		uDAO = new UsuarioDAO();

		Usuario usuario = (Usuario) uDAO.buscarPorEmail(email); 

		
		if(usuario.getEmail() != null){
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

		}else{
			return new ResponseEntity<Usuario>( HttpStatus.NOT_FOUND);

		}


	}

	

}