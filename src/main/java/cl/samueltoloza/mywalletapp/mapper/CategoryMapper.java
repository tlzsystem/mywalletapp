package cl.samueltoloza.mywalletapp.mapper;

import cl.samueltoloza.mywalletapp.dto.CategoryDTO;
import cl.samueltoloza.mywalletapp.model.Category;

public class CategoryMapper {

    public static CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .type(category.getType().name())
                .parentId(category.getParent() != null ? category.getParent().getId() : null)
                .build();
    }

}
