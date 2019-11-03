package exemplo.persistencia;

import java.util.List;

public interface DAO {

	Object cadastrar(Object objeto);
	void editar(Object objeto);
	void excluir(long id);
	Object buscarPorId(long id);
	List<Object> buscarTodos();
}
