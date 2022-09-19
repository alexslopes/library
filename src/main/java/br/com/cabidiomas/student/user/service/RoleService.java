package br.com.cabidiomas.student.user.service;


import br.com.cabidiomas.student.user.model.Role;
import br.com.cabidiomas.student.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    public Role findRoleById(Long id){
        return repository.findById(id).
                orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role não encontrado") );
    }
}
