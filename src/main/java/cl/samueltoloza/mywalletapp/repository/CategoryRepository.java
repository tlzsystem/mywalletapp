package cl.samueltoloza.mywalletapp.repository;

import cl.samueltoloza.mywalletapp.model.Category;
import cl.samueltoloza.mywalletapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByUser(User user);

    Optional<Category> findByIdAndUser(Long id, User user);

    List<Category> findByUserAndParentIsNull(User user);

    List<Category> findByParent(Category parent);

}
