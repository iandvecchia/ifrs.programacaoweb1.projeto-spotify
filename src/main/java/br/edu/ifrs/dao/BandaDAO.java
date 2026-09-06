package br.edu.ifrs.dao;

import java.util.List;

import br.edu.ifrs.dominio.Banda;

public interface BandaDAO {
    void salvar(Banda banda);

    Banda buscarPorId(Long id);

    List<Banda> listarTodos();

    Banda atualizar(Banda banda);

    void remover(Long id);

    List<Banda> listarPorGenero(String genero);


}
