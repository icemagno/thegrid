package br.com.cmabreu.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cmabreu.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.userName = :username AND u.provider = 'LOCAL'")
    public User getUserByUsername(@Param("username") String userName);	
	
}
