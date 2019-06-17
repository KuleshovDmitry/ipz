package com.nixsolutions.ppp.controller;

import com.nixsolutions.ppp.model.entity.Book;
import com.nixsolutions.ppp.model.entity.Reservation;
import com.nixsolutions.ppp.model.entity.User;
import com.nixsolutions.ppp.service.BookService;
import com.nixsolutions.ppp.service.ReservationService;
import com.nixsolutions.ppp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;
    @RequestMapping(method = RequestMethod.GET)
    public String getAvailableBooks(Model model,
                                      @RequestParam(value = "authorFirstName") String authorFirstName,
                                      @RequestParam(value = "authorLastName") String authorLastName,
                                      @RequestParam(value = "bookName") String bookName) {

        model.addAttribute("bookList", bookService.findAvailable(authorFirstName, authorLastName, bookName));
        return "reservation";
    }
    @RequestMapping(method = RequestMethod.POST)
    public void selectBook(@RequestParam(value = "bookId") String bookId){
        long id = Long.parseLong(bookId);
        Book book = bookService.getBook(id);
        if (book.isAvailable()){
            User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            reservationService.create(new Reservation(LocalDate.now(), book, user));
            book.setAvailable(false);
            bookService.update(book);
        }
    }
}