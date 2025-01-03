package dw.gameshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/gameshop/index.html")
    public String index() {
        return "forward:/index.html";
    }

    @GetMapping("/gameshop/singleProduct.html")
    public String singleProduct() {
        return "forward:/singleProduct.html";
    }

    @GetMapping("/gameshop/board.html")
    public String board() {
        return "forward:/board.html";
    }

    @GetMapping("/gameshop/login.html")
    public String login() {
        return "forward:/login.html";
    }

    @GetMapping("/gameshop/cart.html")
    public String cart() {
        return "forward:/cart.html";
    }

    @GetMapping("/gameshop/mypage.html")
    public String mypage() {
        return "forward:/mypage.html";
    }

    @GetMapping("/gameshop/video.html")
    public String video() {
        return "forward:/video.html";
    }

    @GetMapping("/gameshop/upload.html")
    public String upload() {
        return "forward:/upload.html";
    }
}
