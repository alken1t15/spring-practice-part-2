package alken1t.runtime.kz.springpractice_9_00.repository;

import alken1t.shop.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option,Long> {
}