package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repository; // EntityManagerFactory

    // 1. 전체리스트 가져오기 비즈니스 메서드
    public List<Book> getAllList(){
          return repository.findAll(); // select SQL
    }

    // 2. 데이터 등록(저장)하기
    public Book register(Book book){
          return repository.save(book); // insert SQL
    }

    // 3. 특정 데이터 가져오기(PK)
    public Book getById(Long id){
            Optional<Book> optional=repository.findById(id); // select SQL where ~
            if(optional.isPresent()){ // true
                  return optional.get(); // Book
            }else{
                throw new RuntimeException("Book not found with id:" + id);
            }
    }

    @Transactional
    // 4. 특정 데이터 수정하기
    public Book update(Long id, Book reqBook){
          Optional<Book> optional=repository.findById(id); // select SQL
          if(optional.isPresent()){ //  수정하기
              Book book=optional.get(); // 영속성메모리에 있는 Book<----------->book
              book.setTitle(reqBook.getTitle()); //  변경
              book.setPrice(reqBook.getPrice());// 변경
              //return book; //  자동더티체킹을한 후 update SQL 실행(속도지연, 구현쉽다)
              return repository.save(book); // update SQL 생성
          }else{
              throw new RuntimeException("Book not found with id:" + id);
          }
    }
    // 5. 특정 데이터 삭제하기
    public void deleteById(Long id){
          repository.deleteById(id); // delete SQL
    }
}
