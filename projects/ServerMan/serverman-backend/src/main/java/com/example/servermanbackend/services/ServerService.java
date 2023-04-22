package com.example.servermanbackend.services;

import com.example.servermanbackend.models.Server;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {

    Server create(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> getAll(int limit); // limitaremos a quantidade de servidores na resposta
    Server getById(Long id);
    Server update(Server server);
    Boolean delete(Long id);


}
