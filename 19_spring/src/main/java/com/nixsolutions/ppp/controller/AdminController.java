package com.nixsolutions.ppp.controller;

import com.nixsolutions.ppp.model.entity.*;
import com.nixsolutions.ppp.service.*;
import org.h2.util.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Calendar;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BookService bookService;
    @Autowired
    private RentedBookService rentedBookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private GenreService genreService;

    @RequestMapping(value = "/changeUsers", method = RequestMethod.GET)
    public String changeUsersPageGet(Model model) {
        model.addAttribute("userList", userService.selectAll());
        return "admin/changeUsers";
    }

    @RequestMapping(value = "/changeUsers", method = RequestMethod.POST,
            params = {"login", "password", "role", "action"})
    public String changeUsersPagePost(Model model,
                                      @RequestParam(value = "role") String roleName,
                                      @RequestParam(value = "login") String login,
                                      @RequestParam(value = "password") String password,
                                      @RequestParam(value = "action") String action) {

        long roleId = roleService.getId(Role.getByName(roleName));
        Role userRole = Role.getByName(roleName);
        userRole.setId(roleId);
        User user = new User(login, password, userRole);
        if (action.equals("create")) {
            userService.create(user);
        } else if (action.equals("change")) {
            user = userService.getUser(login);
            user.setPassword(password);
            user.setRole(userRole);
            userService.update(user);
        } else {
            user.setId(userService.getId(user.getLogin()));
            userService.delete(user);
        }
        model.addAttribute("userList", userService.selectAll());
        return "changeUsers";
    }

    @RequestMapping(value = "/updateBooksList", method = RequestMethod.GET)
    public String updateBooksList(Model model) {
        model.addAttribute("bookList", bookService.selectAll());
        return "admin/updateBooksList";
    }

    @RequestMapping(value = "/selectAuthor", method = RequestMethod.GET)
    public String addBook(Model model,
                          @RequestParam(value = "authorFirstName") String firstName,
                          @RequestParam(value = "authorLastName") String lastName) {
        model.addAttribute("authorList", authorService.find(firstName, lastName));
        return "admin/selectAuthor";
    }

    @RequestMapping(value = "/createBook", method = RequestMethod.GET)
    public String createBook(Model model,
                             @RequestParam(value = "authorId") String authorId) {
        model.addAttribute("authorId", authorId);
        return "admin/createBook";
    }

    @RequestMapping(value = "/selectAuthor", method = RequestMethod.POST)
    public String createBook(Model model,
                             @RequestParam(value = "authorFirstName") String authorFirstName,
                             @RequestParam(value = "authorLastName") String authorLastName) {
        Author author = new Author(authorFirstName, authorLastName, "");
        authorService.create(author);
        model.addAttribute("authorId", authorService.getId(author));
        return "admin/createBook";
    }

    @RequestMapping(value = "/createBook", method = RequestMethod.POST)
    public String createBookPost(Model model,
                                 @RequestParam(value = "authorId") String authorIdString,
                                 @RequestParam(value = "name") String name,
                                 @RequestParam(value = "isbn") String isbn,
                                 @RequestParam(value = "year") int year) {
        Author author = authorService.getAuthor(Long.parseLong(authorIdString));
        Genre genre = new Genre("1", "1");
        genreService.create(genre);
        Book book = new Book(name, year, Integer.parseInt(isbn), author, genreService.getGenre(1));
        bookService.create(book);
        model.addAttribute("bookList", bookService.selectAll());
        return "admin/updateBooksList";
    }

    @RequestMapping(value = "/acceptReservation", method = RequestMethod.GET)
    public String getReservationList(Model model) {
        model.addAttribute("reservationList", reservationService.selectAll());
        return "admin/acceptReservation";
    }

    @RequestMapping(value = "/acceptReservation", method = RequestMethod.POST)
    public String acceptReservation(Model model,
                                    @RequestParam(value = "reservationId") String reservationIdString) {
        Reservation reservation = reservationService.getReservation(Long.parseLong(reservationIdString));
        RentedBook rentedBook = new RentedBook(reservation.getUser(),
                reservation.getBook(),
                false,
                LocalDate.now(),
                LocalDate.now());
        rentedBookService.create(rentedBook);
        reservationService.delete(reservation);
        model.addAttribute("reservationList", reservationService.selectAll());
        return "admin/acceptReservation";//TODO string ->void
    }
}