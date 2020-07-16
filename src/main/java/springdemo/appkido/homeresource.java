package springdemo.appkido;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

@Controller
public class homeresource {

    // @GetMapping("/")
    // public String home() {
    //     return ("<h1>Welcome</h1>");
    // }

    // @GetMapping("/user")
    // public String user() {
    //     return ("<h1>Welcome User</h1>");
    // }

    // @GetMapping("/admin")
    // public String admin() {
    //     return ("<h1>Welcome Admin</h1>");
    //}
    @RequestMapping("/")
    public String home() {
        return "index.html" ;
    }

}