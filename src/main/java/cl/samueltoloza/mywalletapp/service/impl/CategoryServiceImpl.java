package cl.samueltoloza.mywalletapp.service.impl;

import cl.samueltoloza.mywalletapp.model.Category;
import cl.samueltoloza.mywalletapp.model.TransactionType;
import cl.samueltoloza.mywalletapp.model.User;
import cl.samueltoloza.mywalletapp.repository.CategoryRepository;
import cl.samueltoloza.mywalletapp.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategoriesForUser(User user) {
        return categoryRepository.findByUser(user);
    }

    @Override
    public Category createCategoryForUser(Category category, User user) {

        if (category == null) throw new IllegalArgumentException("Category must not be null");
        if (user == null) throw new IllegalArgumentException("User must not be null");

        category.setUser(user);
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findByIdAndUser(Long id, User user) {
        return categoryRepository.findByIdAndUser(id, user);
    }

    @Override
    public void createDefaultCategoriesForUser(User user) {

        Category sueldo = Category.builder()
                .name("Sueldo")
                .type(TransactionType.INCOME)
                .user(user)
                .build();

        Category otrosIngresos = Category.builder()
                .name("Otros ingresos")
                .type(TransactionType.INCOME)
                .user(user)
                .build();

        // EXPENSE categories (parent)
        Category alimentacion = Category.builder()
                .name("Alimentación")
                .type(TransactionType.EXPENSE)
                .user(user)
                .build();

        Category transporte = Category.builder()
                .name("Transporte")
                .type(TransactionType.EXPENSE)
                .user(user)
                .build();

        Category servicios = Category.builder()
                .name("Servicios")
                .type(TransactionType.EXPENSE)
                .user(user)
                .build();

        Category vivienda = Category.builder()
                .name("Vivienda")
                .type(TransactionType.EXPENSE)
                .user(user)
                .build();

        Category salud = Category.builder()
                .name("Salud")
                .type(TransactionType.EXPENSE)
                .user(user)
                .build();

        Category entretenimiento = Category.builder()
                .name("Entretenimiento")
                .type(TransactionType.EXPENSE)
                .user(user)
                .build();

        Category educacion = Category.builder()
                .name("Educación")
                .type(TransactionType.EXPENSE)
                .user(user)
                .build();

        // Guardar primero categorías padre
        categoryRepository.saveAll(List.of(
                sueldo, otrosIngresos,
                alimentacion, transporte, servicios,
                vivienda, salud, entretenimiento, educacion
        ));

        // Subcategorías
        List<Category> subcategories = List.of(
                Category.builder()
                        .name("Supermercado")
                        .type(TransactionType.EXPENSE)
                        .user(user)
                        .parent(alimentacion)
                        .build(),
                Category.builder()
                        .name("Delivery")
                        .type(TransactionType.EXPENSE)
                        .user(user)
                        .parent(alimentacion)
                        .build(),
                Category.builder()
                        .name("Transporte público")
                        .type(TransactionType.EXPENSE)
                        .user(user)
                        .parent(transporte)
                        .build(),
                Category.builder()
                        .name("Combustible")
                        .type(TransactionType.EXPENSE)
                        .user(user)
                        .parent(transporte)
                        .build(),
                Category.builder()
                        .name("Luz")
                        .type(TransactionType.EXPENSE)
                        .user(user)
                        .parent(servicios)
                        .build(),
                Category.builder()
                        .name("Agua")
                        .type(TransactionType.EXPENSE)
                        .user(user)
                        .parent(servicios)
                        .build(),
                Category.builder()
                        .name("Internet")
                        .type(TransactionType.EXPENSE)
                        .user(user)
                        .parent(servicios)
                        .build(),
                Category.builder()
                        .name("Arriendo")
                        .type(TransactionType.EXPENSE)
                        .user(user)
                        .parent(vivienda)
                        .build(),
                Category.builder()
                        .name("Mantenimiento hogar")
                        .type(TransactionType.EXPENSE)
                        .user(user)
                        .parent(vivienda)
                        .build(),
                Category.builder()
                        .name("Medicamentos")
                        .type(TransactionType.EXPENSE)
                        .user(user)
                        .parent(salud)
                        .build(),
                Category.builder()
                        .name("Consulta médica")
                        .type(TransactionType.EXPENSE)
                        .user(user)
                        .parent(salud)
                        .build(),
                Category.builder()
                        .name("Cine / Series")
                        .type(TransactionType.EXPENSE)
                        .user(user)
                        .parent(entretenimiento)
                        .build(),
                Category.builder()
                        .name("Juegos / Apps")
                        .type(TransactionType.EXPENSE)
                        .user(user)
                        .parent(entretenimiento)
                        .build(),
                Category.builder()
                        .name("Libros")
                        .type(TransactionType.EXPENSE)
                        .user(user)
                        .parent(educacion)
                        .build(),
                Category.builder()
                        .name("Cursos / Talleres")
                        .type(TransactionType.EXPENSE)
                        .user(user)
                        .parent(educacion)
                        .build()
        );

        categoryRepository.saveAll(subcategories);


    }
}
