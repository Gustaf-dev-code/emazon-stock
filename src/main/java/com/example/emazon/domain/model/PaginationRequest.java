package com.example.emazon.domain.model;

import com.example.emazon.domain.exception.InvalidNumberPageException;
import com.example.emazon.domain.exception.InvalidSizePageException;
import com.example.emazon.domain.util.PaginationConstants;

public class PaginationRequest {
    private final int page;
    private final int size;
    private final String sortBy;
    private SortDirection sortDirection;

    public PaginationRequest(Integer page, Integer size, String sortBy, SortDirection sortDirection) {

        if(page == null) {
            page = PaginationConstants.DEFAULT_PAGE;
        }

        if(size == null) {
            size = PaginationConstants.DEFAULT_SIZE;
        }

        if(sortBy == null) {
            sortBy = PaginationConstants.DEFAULT_SORT_BY;
        }

        if(sortDirection == null) {
            sortDirection = SortDirection.valueOf(PaginationConstants.DEFAULT_SORT_DIRECTION);
        }

        if(page < 0) {
            throw new InvalidNumberPageException(PaginationConstants.INVALID_NUMBER_PAGE_EXCEPTION_MESSAGE);
        }

        if(size < 0) {
            throw new InvalidSizePageException(PaginationConstants.INVALID_SIZE_PAGE_EXCEPTION_MESSAGE);
        }

        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getSortBy() {
        return sortBy;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }
}
