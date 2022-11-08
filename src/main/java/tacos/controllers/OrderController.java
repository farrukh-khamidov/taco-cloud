package tacos.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.components.OrderProps;
import tacos.entities.Order;
import tacos.entities.User;
import tacos.repositories.OrderRepository;
import tacos.repositories.UserRepository;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/orders")
@AllArgsConstructor
@SessionAttributes("order")
//@ConfigurationProperties(prefix="taco.orders")
public class OrderController {


    private OrderRepository orderRepository;
    private UserRepository userRepository;

    private OrderProps props;

//    private int pageSize = 20;
//    public void setPageSize(int pageSize) {
//        this.pageSize = pageSize;
//    }

//    @GetMapping
//    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
//        model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user));
//        return "orderList";
//    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, props.getPageSize());
        model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, Principal principal) {
        log.info("Order submitted: " + order);

        if (errors.hasErrors()) {
            return "orderForm";
        }

        User user = userRepository.findByUsername(principal.getName()).get();
        order.setUser(user);

        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
