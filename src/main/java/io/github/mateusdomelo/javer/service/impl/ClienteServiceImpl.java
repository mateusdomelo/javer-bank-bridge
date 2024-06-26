package io.github.mateusdomelo.javer.service.impl;

import io.github.mateusdomelo.javer.client.ClienteFeignClient;
import io.github.mateusdomelo.javer.domain.entity.Cliente;
import io.github.mateusdomelo.javer.exception.ClienteNotFoundException;
import io.github.mateusdomelo.javer.exception.NotFoundException;
import io.github.mateusdomelo.javer.rest.dto.ClienteScoreDTO;
import io.github.mateusdomelo.javer.service.ClienteService;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteFeignClient feignClient;

    public ClienteServiceImpl(ClienteFeignClient client) {
        this.feignClient = client;
    }

    @Override
    public Cliente obterPorId(Long id) throws ClienteNotFoundException {
        try {
            return feignClient.obterPorId(id);
        } catch (NotFoundException e) {
            throw new ClienteNotFoundException(id);
        }
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        return feignClient.criar(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        try {
            feignClient.atualizar(id, cliente);
        } catch (NotFoundException e) {
            throw new ClienteNotFoundException(id);
        }
    }

    @Override
    public void excluir(Long id) {
        try {
            feignClient.excluir(id);
        } catch (NotFoundException e) {
            throw new ClienteNotFoundException(id);
        }
    }

    @Override
    public ClienteScoreDTO obterScore(Long id) {
        try {
            Cliente cliente = feignClient.obterPorId(id);
            ClienteScoreDTO clienteScoreDTO = new ClienteScoreDTO();
            clienteScoreDTO.setNome(cliente.getNome());
            clienteScoreDTO.setScore(cliente.getScoreCredito());
            return clienteScoreDTO;
        } catch (NotFoundException e) {
            throw new ClienteNotFoundException(id);
        }
    }
}
