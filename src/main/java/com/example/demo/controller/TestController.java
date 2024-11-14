package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    private final BookService bookService;

    public TestController(BookService bookService) {
        this.bookService = bookService;
    }

    /* @GetMapping("/list")
     public String list(Model model){
         List<String> list=new ArrayList<>();
         list.add("사과");
         list.add("바나바");
         list.add("오랜지");
         list.add("귤");
         model.addAttribute("list", list);  // ${list}
         return "views/list"; // list.html
     }*/
    @GetMapping("/list")
    public String list(Model model){
        List<Book> list=bookService.getAllList();
        model.addAttribute("list", list);
        return "views/list"; // list.html
    }

    @GetMapping("/addBook")
    public String addBook(){
        return "views/register"; // register.html
    }

    @GetMapping("/detail/{id}")
    public String list(@PathVariable Long id, Model model){
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "views/detail"; // detail.html
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        bookService.deleteById(id);
        return "redirect:/list"; // edit.html
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,  Model model){
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "views/edit"; // edit.html
    }

    @PostMapping("/update")
    public String update(Book book){
        Book bk=bookService.update(book.getId(), book);
        return "redirect:/list";
    }
}
