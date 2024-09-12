package com.example.emazon.domain.api.usecase;

import com.example.emazon.configuration.exceptionhandler.ControllerAdvisor;
import com.example.emazon.domain.exception.EmptyFieldException;
import com.example.emazon.domain.exception.InvalidNumberPageException;
import com.example.emazon.domain.model.Category;
import com.example.emazon.domain.model.PaginatedResponse;
import com.example.emazon.domain.model.PaginationRequest;
import com.example.emazon.domain.model.SortDirection;
import com.example.emazon.domain.spi.ICategoryPersistentPort;
import com.example.emazon.domain.util.PaginationConstants;
import com.example.emazon.domain.util.PaginationValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(CategoryUseCase.class)
@Import(ControllerAdvisor.class)
class CategoryUseCaseTest {


    @MockBean //Crea instancias simuladas (mock) de las interfaces que se inyectan en el controlador.
    private ICategoryPersistentPort categoryPersistentPort;

    @MockBean
    private PaginationValidator paginationValidator;

    @InjectMocks // Inyecta los mocks en la clase que se va a probar
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializamos los mocks antes de cada test
        categoryUseCase = new CategoryUseCase(categoryPersistentPort,paginationValidator);// Inicializamos el caso de uso
    }

    @Test
    void getListOfCategoriesWithPagination() {
        // Datos de prueba
        List<Category> categories = Arrays.asList(
                new Category(1, "Electrónico", "Dispositivos y gadgets"),
                new Category(2, "Juguetes", "Juguetes para niños")
        );
        PaginatedResponse<Category> paginatedResponse = new PaginatedResponse<>(categories, 1, 0, 10);

        // Configurar el mock para que devuelva la respuesta paginada
        PaginationRequest paginationRequest = new PaginationRequest(0, 10, "name", SortDirection.ASC);
        doReturn(paginatedResponse).when(categoryPersistentPort).getAllCategoriesPaginated(any(PaginationRequest.class));
        System.out.println("categoryPersistentPort: " + categoryPersistentPort);

        // Ejecuta el caso de uso
        System.out.println("Ejecutando el caso de uso...");
        PaginatedResponse<Category> result = categoryUseCase.getAllCategoriesPaginated(paginationRequest);
        System.out.println("Resultado: " + result);

        // Imprimir detalles del objeto paginationRequest
        System.out.println("Página: " + paginationRequest.getPage());
        System.out.println("Tamaño: " + paginationRequest.getSize());
        System.out.println("Ordenar por: " + paginationRequest.getSortBy());
        System.out.println("Dirección de orden: " + paginationRequest.getSortDirection());

        // Verificar que el resultado sea el esperado
        assertNotNull(result);
        assertEquals(10, result.getTotalItems());
        assertEquals("Electrónico", result.getItems().get(0).getName());
        assertEquals("Juguetes", result.getItems().get(1).getName());

        // Verificar que el puerto persistente fue llamado una vez
        verify(categoryPersistentPort, times(1)).getAllCategoriesPaginated(paginationRequest);

        System.out.println("categoryUseCase: " + categoryUseCase);
        System.out.println("categoryPersistentPort: " + categoryPersistentPort);
    }


    @Test
    void testGetAllCategoriesPaginated_NullPaginationRequest() {
        // Given
        PaginationRequest paginationRequest = null;

        // When & Then
        Exception exception = assertThrows(EmptyFieldException.class, () -> categoryUseCase.getAllCategoriesPaginated(paginationRequest));

        assertEquals("Pagination request cannot have null fields", exception.getMessage());
    }

    @Test
    void testGetAllCategoriesPaginated_InvalidPageNumber() {
        Exception exception = assertThrows(InvalidNumberPageException.class, () -> {
            new PaginationRequest(-1, 10, "name", SortDirection.ASC);
        });

        assertEquals(PaginationConstants.INVALID_NUMBER_PAGE_EXCEPTION_MESSAGE, exception.getMessage());
    }


}
