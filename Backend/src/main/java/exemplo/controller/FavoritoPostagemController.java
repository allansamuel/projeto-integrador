package exemplo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import exemplo.model.FavoritoPostagem;
import exemplo.persistencia.FavoritoPostagemDAO;

@Controller    
@RequestMapping(path="/favorito_postagem") 
public class FavoritoPostagemController {


	private FavoritoPostagemDAO fpDAO;

	@RequestMapping(value = "", method = RequestMethod.POST)

	public ResponseEntity<FavoritoPostagem> favoritar(@RequestBody FavoritoPostagem favoritoPostagem) {
		fpDAO = new FavoritoPostagemDAO();
		Object objeto = favoritoPostagem;
		favoritoPostagem = (FavoritoPostagem) fpDAO.favoritar(objeto);
		return new ResponseEntity<FavoritoPostagem>(favoritoPostagem, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable long id){
		fpDAO = new FavoritoPostagemDAO();
		fpDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> listaTodos() {
		
		fpDAO = new FavoritoPostagemDAO();
		List<Object> listaFavoritoPostagem = fpDAO.buscarTodos(); 
		
		if(!listaFavoritoPostagem.isEmpty()){
			return new ResponseEntity<List<Object>>(listaFavoritoPostagem, HttpStatus.OK);
			} else{
				return new ResponseEntity<List<Object>>(HttpStatus.NOT_FOUND);

			}
		
	}

	@RequestMapping(value = "/buscar_por_usuario/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> buscarPorUsuario(@PathVariable long id){
		
		fpDAO = new FavoritoPostagemDAO();

		List<Object> listaFavoritoPostagem = fpDAO.buscarFavoritosDeUsuario(id);
		if(!listaFavoritoPostagem.isEmpty()){
		return new ResponseEntity<List<Object>>(listaFavoritoPostagem, HttpStatus.OK);
		} else{
			return new ResponseEntity<List<Object>>(HttpStatus.NOT_FOUND);

		}
	}
	
	
	@RequestMapping(value = "/buscar_por_postagem/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> buscarPorPostagem(@PathVariable long id){
		
		fpDAO = new FavoritoPostagemDAO();

		List<Object> listaFavoritoPostagem = fpDAO.buscarFavoritosDePostagem(id);
		if(!listaFavoritoPostagem.isEmpty()){
		return new ResponseEntity<List<Object>>(listaFavoritoPostagem, HttpStatus.OK);
		} else{
			return new ResponseEntity<List<Object>>(HttpStatus.NOT_FOUND);

		}
		
	}
}