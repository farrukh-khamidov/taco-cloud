package tacos.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.entities.Order;
import tacos.entities.User;
import tacos.repositories.OrderRepository;
import tacos.util.SecurityContextUtil;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@AllArgsConstructor
@SessionAttributes("order")
public class OrderController {


    private OrderRepository orderRepository;

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
        log.info("Order submitted: " + order);

        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(SecurityContextUtil.getUser());



        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
