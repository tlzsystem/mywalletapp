package cl.samueltoloza.mywalletapp.controller;

import cl.samueltoloza.mywalletapp.model.Category;
import cl.samueltoloza.mywalletapp.model.User;
import cl.samueltoloza.mywalletapp.payload.ApiResponse;
import cl.samueltoloza.mywalletapp.security.UserPrincipal;
import cl.samueltoloza.mywalletapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Category>>> getAll(@AuthenticationPrincipal UserPrincipal user) {
        List<Category> categories = categoryService.getCategoriesForUser(user.getUser());
        return ResponseEntity.ok(new ApiResponse<>(true, "Categories retrieved", categories));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Category>> create(@RequestBody Category category,
                                                        @AuthenticationPrincipal UserPrincipal user) {
        Category saved = categoryService.createCategoryForUser(category, user.getUser());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Category created", saved));
    }



}
