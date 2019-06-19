package configration;

import com.nixsolutions.ppp.model.entity.*;
import com.nixsolutions.ppp.service.*;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import service.*;

import java.time.LocalDate;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;
    @Autowired
    private RentedBookService rentedBookService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().permitAll()
                .and();

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/login?error")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll();

        http.logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true);

        roleService.create(Role.getAdminRole());
        roleService.create(Role.getUserRole());
        User user = new User("admin","admin",Role.getAdminRole());
        userService.create(user);
        userService.create(new User("user","user",Role.getUserRole()));
        Genre genre =  new Genre("genre", "genre");
        genreService.create(genre);
        Author author = new Author("fn","ln","bio");
        authorService.create(author);
        Book book = new Book("b",1,1, author, genre);
        bookService.create(book);
        rentedBookService.create(new RentedBook(user, book, false, LocalDate.now(), LocalDate.now()));
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}