package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// MyBatis -> JPA
@Repository  // JpaRepository -> CRUD Method 정의(Table, PK)
public interface BookRepository extends JpaRepository<Book,   Long> {
    //  CRUD 기능정의 --> X
    // 1. JPA에서 제공해주는 메서드를 사용
    // - 전체리스트 가져오기 - findAll()....
    // - 데이터 저장하기 - save()
    // - 특정 데이터 가져오기 - findById(Long id)
    // + 트렌젝션 처리(All or Nothing)()
    // - 특정데이터 수정하기 - save() : 기존의 PK 값이 존재하면  -> update SQL
    // 데이터가 저장되는 공간 : 영속성(일관성,정보가 항상 일치)메모리(자동더티체킹)
    // Book(Object)(수정)<--------------> book(Table)
    // - 특정데이터 삭제하기 - deleteById(Long id)
}
// interface BookRepository
/*
   public class EntityManagerFactory implements BookRepository{


   }
   findAll() : SQL
   Hibernate:
    select
        b1_0.id,
        b1_0.author,
        b1_0.page,
        b1_0.price,
        b1_0.title
    from
        book b1_0

        Hibernate:
    select
        b1_0.id,
        b1_0.author,
        b1_0.page,
        b1_0.price,
        b1_0.title
    from
        Book b1_0
    where
        b1_0.id=?
*/
