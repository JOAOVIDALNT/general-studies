package com.example.servermanbackend.services.implementation;

import com.example.servermanbackend.enums.Status;
import com.example.servermanbackend.models.Server;
import com.example.servermanbackend.repositories.ServerRepository;
import com.example.servermanbackend.services.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;

@RequiredArgsConstructor // o lombok implementa um construtor que ja injeta nosso repository
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {

    private final ServerRepository repository;


    @Override
    public Server create(Server server) {
        log.info("Saving new server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return repository.save(server);
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);

        Server server = repository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        repository.save(server);
        return server;
    }

    @Override
    public Collection<Server> getAll(int limit) {
        log.info("Fetching all servers");
        return repository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Server getById(Long id) {
        log.info("Fetching server by id: {}", id);
        return repository.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server: {}", server.getName());
        return repository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by id: {}", id);
        repository.deleteById(id);
        return true;
    }


    private String setServerImageUrl() {
        return null;
    }
}
