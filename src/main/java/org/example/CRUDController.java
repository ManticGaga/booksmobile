package org.example;

import org.example.CRUD;
import org.example.CRUDService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class CRUDController {
    public CRUDService crudService;

    public CRUDController(CRUDService crudService) {
        this.crudService = crudService;
    }

    @PostMapping("/create")
    public String createCRUD(@RequestBody CRUD crud) throws InterruptedException, ExecutionException{
        return crudService.createCRUD(crud);
    }

    @GetMapping("/query")
    public CRUD getCRUD2(@RequestParam String documentId) throws InterruptedException, ExecutionException{
        return crudService.getCRUD_Document(documentId);
    }

    @GetMapping("/get")
    public Map<String, List<CRUD>> getCRUD() throws InterruptedException, ExecutionException {
        List<CRUD> crudList = crudService.getCRUD_Collection();
        Map<String, List<CRUD>> response = new HashMap<>();
        response.put("countries", crudList);
        return response;
    }

    @PutMapping("/update")
    public String updateCRUD(@RequestBody CRUD crud) throws InterruptedException, ExecutionException{
        return crudService.updateCRUD(crud);
    }

    @PutMapping("/delete")
    public String deleteCRUD(@RequestParam String documentId) throws InterruptedException, ExecutionException{
        return crudService.deleteCRUD(documentId);
    }

    @GetMapping("test")
    public ResponseEntity<String> testGetEndpoint(){
        return ResponseEntity.ok("Test Get Endpoint is working");
    }
}