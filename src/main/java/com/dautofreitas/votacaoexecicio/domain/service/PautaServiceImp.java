package com.dautofreitas.votacaoexecicio.domain.service;

import com.dautofreitas.votacaoexecicio.domain.entity.Pauta;
import com.dautofreitas.votacaoexecicio.domain.exeptions.RecursoNaoExisteException;
import com.dautofreitas.votacaoexecicio.domain.interfaces.repository.PautaRepository;
import com.dautofreitas.votacaoexecicio.domain.interfaces.service.PautaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PautaServiceImp implements PautaService {

    private PautaRepository repository;

    @Override
    public Pauta salvar(Pauta pauta) {
        return repository.salvar(pauta);
    }

    @Override
    public Pauta buscarPorId(UUID pautaId) {

        return repository.buscarPorId(pautaId)
                .orElseThrow(()-> new RecursoNaoExisteException(Pauta.class.getSimpleName(), pautaId));
    }

    @Override
    public List<Pauta> buscarTodos() {
        return repository.buscarTodos();
    }
}
