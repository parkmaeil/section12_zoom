package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import com.example.demo.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// R -> S -> C
@RestController
@RequestMapping("/api")
public class BookRestController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private BookService service;

    @Autowired
    private FileUploadService fileUploadService;

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
    // 이미지 업로드 추가 부분...
    @PostMapping("/books/save")
    public ResponseEntity<?> saveBook(@RequestParam("title") String title,
                                      @RequestParam("price") int price,
                                      @RequestParam("author") String author,
                                      @RequestParam("page") int page,
                                      @RequestParam("image_path") MultipartFile file) {
        try {
            String uniqueFilename = fileUploadService.saveFile(file);
            Book book = new Book();
            book.setTitle(title);
            book.setPrice(price);
            book.setAuthor(author);
            book.setPage(page);
            book.setOriginalFileName(uniqueFilename);
            book = service.register(book);
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // 이미를 클라이언트로 내려보내기
    @GetMapping("/image/{filename:.+}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) throws IOException {
        Path imagePath = Paths.get(uploadPath, filename);
        byte[] imageBytes = Files.readAllBytes(imagePath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Set appropriate content type based on image format

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}
