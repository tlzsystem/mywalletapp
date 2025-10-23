package cl.samueltoloza.mywalletapp.service;

import cl.samueltoloza.mywalletapp.model.Category;
import cl.samueltoloza.mywalletapp.model.User;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> getCategoriesForUser(User user);

    Category createCategoryForUser(Category category, User user);

    Optional<Category> findByIdAndUser(Long id, User user);

    void createDefaultCategoriesForUser(User user);

}
