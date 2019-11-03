package exemplo.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import exemplo.model.Prova;
import exemplo.persistencia.ProvaDAO;

@Controller    
@RequestMapping(path="/prova") 
public class ProvaController {


	private ProvaDAO pDAO;

	@RequestMapping(value = "", method = RequestMethod.POST)

	public ResponseEntity<Prova> cadastrar(@RequestBody Prova prova) {
		pDAO = new ProvaDAO();
		Object objeto = prova;
		prova = (Prova) pDAO.cadastrar(objeto);
		return new ResponseEntity<Prova>(prova, HttpStatus.CREATED);

	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Prova prova){
		pDAO = new ProvaDAO();
		pDAO.editar(prova);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		pDAO = new ProvaDAO();
		pDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}
	//---------------------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> listaTodos() {


		pDAO = new ProvaDAO();
		List<Object> listaProvas = pDAO.buscarTodos(); //criar metodo

		if(!listaProvas.isEmpty()){
			return new ResponseEntity<List<Object>>(listaProvas, HttpStatus.OK);
		} else{
			return new ResponseEntity<List<Object>>( HttpStatus.NOT_FOUND);

		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> buscarPorId(@PathVariable long id){

		pDAO = new ProvaDAO();

		Prova prova = (Prova) pDAO.buscarPorId(id); 


		if(prova != null){
			return new ResponseEntity<Object>(prova, HttpStatus.OK);
		} else{
			return new ResponseEntity<Object>( HttpStatus.NOT_FOUND);

		}

	}

}