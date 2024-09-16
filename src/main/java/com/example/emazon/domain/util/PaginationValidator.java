package com.example.emazon.domain.util;

import com.example.emazon.domain.exception.EmptyFieldException;
import com.example.emazon.domain.exception.InvalidNumberPageException;
import com.example.emazon.domain.exception.InvalidSizePageException;
import com.example.emazon.domain.model.PaginationRequest;
import com.example.emazon.domain.model.SortDirection;

public class PaginationValidator {

    public void validatePaginationRequest(PaginationRequest paginationRequest) {
        if(paginationRequest == null) {
            paginationRequest = new PaginationRequest(0, 10, "name", SortDirection.ASC);
        }
        if(paginationRequest.getPage() < 0) {
            throw new InvalidNumberPageException(PaginationConstants.INVALID_NUMBER_PAGE_EXCEPTION_MESSAGE);
        }
        if(paginationRequest.getSize() < 0) {
            throw new InvalidSizePageException(PaginationConstants.INVALID_SIZE_PAGE_EXCEPTION_MESSAGE);
        }
        if(paginationRequest.getSortDirection() == null) {
            throw new EmptyFieldException("Sort direction cannot be null");
        }
        if(paginationRequest.getSortBy() == null) {
            throw new EmptyFieldException("Sort by cannot be null");
        }
        if(paginationRequest.getSortBy().isBlank()) {
            throw new EmptyFieldException("Sort by cannot be empty");
        }
        if (paginationRequest.getSortDirection() != SortDirection.ASC && paginationRequest.getSortDirection() != SortDirection.DESC) {
            paginationRequest.setSortDirection(SortDirection.ASC);
        }
    }
}
