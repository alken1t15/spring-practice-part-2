package alken1t.runtime.kz.springpractice_9_00.repository;

import alken1t.runtime.kz.springpractice_9_00.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {

}
