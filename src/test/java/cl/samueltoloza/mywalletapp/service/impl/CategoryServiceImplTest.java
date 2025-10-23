package cl.samueltoloza.mywalletapp.service.impl;

import cl.samueltoloza.mywalletapp.model.Category;
import cl.samueltoloza.mywalletapp.model.User;
import cl.samueltoloza.mywalletapp.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setEmail("user@example.com");
    }

    @Test
    void testGetCategoriesForUser() {
        Category cat = new Category();
        cat.setId(1L);
        cat.setName("Food");
        cat.setUser(user);

        when(categoryRepository.findByUser(any(User.class))).thenReturn(List.of(cat));

        List<Category> result = categoryService.getCategoriesForUser(user);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Food", result.get(0).getName());
        verify(categoryRepository, times(1)).findByUser(any(User.class));
    }

    @Test
    void testCreateCategoryForUser() {
        Category cat = new Category();
        cat.setName("Entertainment");

        when(categoryRepository.save(any(Category.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Category saved = categoryService.createCategoryForUser(cat, user);

        assertNotNull(saved);
        assertEquals("Entertainment", saved.getName());
        assertEquals(user, saved.getUser());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void testGetCategoriesForUser_EmptyList() {
        when(categoryRepository.findByUser(any(User.class))).thenReturn(List.of());

        List<Category> result = categoryService.getCategoriesForUser(user);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(categoryRepository, times(1)).findByUser(user);
    }

    @Test
    void testCreateCategoryForUser_NullCategory() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.createCategoryForUser(null, user);
        });

        assertEquals("Category must not be null", exception.getMessage());
    }

    @Test
    void testCreateCategoryForUser_NullUser() {
        Category category = new Category();
        category.setName("Test");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.createCategoryForUser(category, null);
        });

        assertEquals("User must not be null", exception.getMessage());
    }

}
