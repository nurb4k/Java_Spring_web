package kz.narxoz.nurbeksessionproject.sessionProject.controller;


import kz.narxoz.nurbeksessionproject.sessionProject.Model.*;
import kz.narxoz.nurbeksessionproject.sessionProject.repository.*;
import kz.narxoz.nurbeksessionproject.sessionProject.services.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private CourseRepository courseRepository;


    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private InfoPanelRepository infoPanelRepository;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String indexPage(Model model) {
        model.addAttribute("currentUser",getCurrentUser());

        List<Lesson> lessonList = lessonRepository.findAll();
        model.addAttribute("lessonList", lessonList);

        List<Course> courseList = courseRepository.findAll();
        model.addAttribute("courseList", courseList);

        List<infoPanel> infoPanels = infoPanelRepository.findAll();
        model.addAttribute("infoPanels", infoPanels);

        return "index";
    }
    @GetMapping(value = "/cart")
    @PreAuthorize("isAuthenticated()")
    public String myCart(Model model){
        model.addAttribute("currentUser",getCurrentUser());
        List<Cart> cartList = cartRepository.findAll();

        Cart cart = new Cart();
        int summa = 0;

        for(Long i=0L;i<99L;i++){
            cart = cartRepository.findById(i).orElse(null);
            if(cart!=null){
                summa = summa + cart.getC_price();
            }
        }
        model.addAttribute("prices",summa);

        model.addAttribute("items",cartList);
        return "cart";
    }

    @GetMapping(value = "/register")
    public String register(Model model){
        model.addAttribute("currentUser",getCurrentUser());
        return "register";
    }
    @PostMapping(value = "/register")
    public String toRegister(@RequestParam(name = "user_email") String email,
                             @RequestParam(name = "user_password") String password,
                             @RequestParam(name = "re_user_password") String rePassword,
                             @RequestParam(name = "user_full_name") String fullName){
        if (password.equals(rePassword)){
            Users newUser = new Users();
            newUser.setFull_name(fullName);
            newUser.setPassword(password);
            newUser.setEmail(email);
            if (userService.createUser(newUser)!=null){
                return "redirect:/register?success";
            }
        }
        return "redirect:/register?error";
    }

    @PostMapping(value = "/addToCart")
    @PreAuthorize("isAuthenticated()")
    public String addToCartPage(@RequestParam(name = "name") String name,
                                @RequestParam(name = "price")String price,
                                @RequestParam(name = "image")String image){
        int priceInt = Integer.parseInt(price);
        Cart cart = new Cart();
        cart.setC_name(name);
        cart.setC_price(priceInt);
        cart.setC_image(image);
        cartRepository.save(cart);
        return "redirect:/";
    }


    @GetMapping(value = "/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public String detailCoursePage(Model model, @PathVariable(name = "id") Long id) {
        Course course = courseRepository.findById(id).orElse(null);

        model.addAttribute("course", course);
        return "detail";
    }



    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/lesson/{id}")
    public String lessonPage(Model model, @PathVariable(name = "id") Long id) {
        model.addAttribute("currentUser",getCurrentUser());

        Course course = courseRepository.findById(id).orElse(null);
        model.addAttribute("course", course);

        List<Lesson> lessonList = lessonRepository.findAll();
        model.addAttribute("lessonList", lessonList);

        return "lesson";
    }

    @PostMapping(value = "/deleteCart")
    @PreAuthorize("isAuthenticated()")
    public String deleteCart(@RequestParam(name = "course_id")Long course_id){
        Cart cart = cartRepository.findById(course_id).orElse(null);
        if(cart !=null){
            cartRepository.delete(cart);
        }

        return "redirect:cart";

    }
    @PostMapping(value = "/deleteCourse")
    @PreAuthorize("isAuthenticated()")
    public String deleteCourse(@RequestParam(name = "id")Long id){

        Course course = courseRepository.findById(id).orElse(null);
        if(course!=null){
           courseRepository.delete(course);
        }

        return "redirect:/";
    }
    @PostMapping(value = "/addCourse")
    public String addCoursePage(@RequestParam(name = "name")String name,
                              @RequestParam(name = "imageLink")String image,
                              @RequestParam(name = "price")int price,
                              @RequestParam(name = "description")String description,
                              @RequestParam(name = "info")String info){
        Course course =  new Course();
        course.setName(name);
        course.setLogo(image);
        course.setDescription(description);
        course.setInfo_course(info);
        course.setPrice(price);
        courseRepository.save(course);

        return "redirect:adminpanel";
    }

    @PostMapping(value = "/saveCourse")
    public String saveCourse(  @RequestParam(name = "id") Long id,
                               @RequestParam(name = "name")String name,
                                @RequestParam(name = "imageLink")String image,
                                @RequestParam(name = "price")int price,
                                @RequestParam(name = "description")String description,
                                @RequestParam(name = "info")String info){
        Course course = courseRepository.findById(id).orElse(null);
        if(course != null) {
            course.setName(name);
            course.setLogo(image);
            course.setDescription(description);
            course.setInfo_course(info);
            course.setPrice(price);
            courseRepository.save(course);
        }
        return "redirect:adminpanel";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profilePage(Model model) {
        model.addAttribute("currentUser", getCurrentUser());
        return "profile";
    }
    @GetMapping(value = "/myCourse")
    @PreAuthorize("isAuthenticated()")
    public String myCourse(Model model) {
        List<Lesson> lessonList = lessonRepository.findAll();
        model.addAttribute("lessonList", lessonList);
        List<Course> courseList = courseRepository.findAll();
        model.addAttribute("courseList", courseList);

        return "myCourses";
    }
    @GetMapping(value = "/course/{id}")
    @PreAuthorize("isAuthenticated()")
    public String watchCoursePage(Model model, @PathVariable(name = "id") Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        Long idCourse = course.getId();
        if(course!=null){
            model.addAttribute("course_id",idCourse);
            List<Lesson> lessonList = lessonRepository.findAll();
            model.addAttribute("lessonList", lessonList);
        }

        return "WatchCourse";
    }

    @GetMapping(value = "/edit/{id}")
    public String editData(Model model,@PathVariable(name = "id")Long id){
        model.addAttribute("currentUser",getCurrentUser());


        Course course = courseRepository.findById(id).orElse(null);

        model.addAttribute("course",course);

        return "edit";
    }

    @GetMapping(value = "/adminpanel")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String adminpanelPage(Model model) {
        model.addAttribute("currentUser", getCurrentUser());
        List<Course> courseList = courseRepository.findAll();
        model.addAttribute("courseList", courseList);

        return "adminpanel";
    }

    @GetMapping(value = "/moderatorpanel")
    @PreAuthorize("hasAnyRole('ROLE_MODER')")
    public String moderpanelPage(Model model) {
        model.addAttribute("currentUser", getCurrentUser());
        return "moderpanel";
    }

    @GetMapping(value = "/403")
    public String error403(Model model) {

        model.addAttribute("currentUser",getCurrentUser());
        return "403";
    }

    private Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User secUser = (User) authentication.getPrincipal();
            Users currentUser = userService.getUserByEmail(secUser.getUsername());
            return currentUser;
        }
        return null;
    }

}
