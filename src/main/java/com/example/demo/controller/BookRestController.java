package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// R -> S -> C
@RestController
@RequestMapping("/api")
public class BookRestController {

    @Autowired
    private BookService service;

    // GET : http://localhost:8081/api/books
    @GetMapping("/books")
    public ResponseEntity<?> getAllList(){
        return new ResponseEntity<>(service.getAllList(), HttpStatus.OK);
    }
    // POST :  http://localhost:8081/api/books
    @PostMapping("/books")
    public ResponseEntity<?> register(@RequestBody Book book){
          return new ResponseEntity<>(service.register(book), HttpStatus.OK);
    }

    // GET :  http://localhost:8081/api/books/{id}
    @GetMapping("/books/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Book not found with id:" + id, HttpStatus.NOT_FOUND);
        }
    }

    // PUT :  http://localhost:8081/api/books/{id}
    @PutMapping("/books/{id}")                               // {  "title" : "자바의 신신", "price" : 20000  }
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Book reqBook){
        try {
            Book book=service.update(id, reqBook);
            return new ResponseEntity<>(book, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Not Updated", HttpStatus.BAD_REQUEST);
        }
    }

    // DELETE : http://localhost:8081/api/books/{id}
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
          try {
              service.deleteById(id);
              return new ResponseEntity<>("Deleted book with id:"+id , HttpStatus.OK);
          }catch(Exception e){
              return new ResponseEntity<>("Failed : " + id,HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
}
