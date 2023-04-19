package com.dautofreitas.votacaoexecicio.domain.service;

import com.dautofreitas.votacaoexecicio.domain.entity.Associado;
import com.dautofreitas.votacaoexecicio.domain.entity.Pauta;
import com.dautofreitas.votacaoexecicio.domain.exeptions.RecursoNaoExisteException;
import com.dautofreitas.votacaoexecicio.domain.interfaces.repository.AssociadoRepository;
import com.dautofreitas.votacaoexecicio.domain.interfaces.service.AssociadoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
public class AssociadoServiceImp implements AssociadoService {

    AssociadoRepository repository;

    @Override
    public Associado salvar(Associado associado) {
        return repository.salvar(associado);
    }

    @Override
    public Associado buscarPorId(UUID associadoId) {
        return repository.buscarPorId(associadoId)
                .orElseThrow(()-> new RecursoNaoExisteException(Associado.class.getSimpleName(), associadoId));
    }

    @Override
    public List<Associado> buscarTodos() {
        return repository.buscarTodos();
    }
}
