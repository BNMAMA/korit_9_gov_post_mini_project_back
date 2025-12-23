package com.korit.post_mini_project_back.dto.response;

import com.korit.post_mini_project_back.security.PrincipalUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationRespDto<T> {
    private List<T> contents;
    private int totalElements;
    private int totalPages;
    private int currentPage;
    private int size;
    private boolean isLast;

}
