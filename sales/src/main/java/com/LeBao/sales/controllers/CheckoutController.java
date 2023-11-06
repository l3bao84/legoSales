package com.LeBao.sales.controllers;

import com.LeBao.sales.DTO.OrderDTO;
import com.LeBao.sales.entities.Order;
import com.LeBao.sales.services.CheckoutService;
import com.LeBao.sales.services.PayPalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CheckoutController {

    private static final String CANCEL_URL = "checkout?pay=cancel";

    private static final String SUCCESS_URL = "home?pay=success";


    @Autowired
    private PayPalService payPalService;

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping("/handleCheckout")
    public String handleCheckout(@RequestParam("data") String data) {
        checkoutService.checkoutHandler(data);
        return "redirect:/checkout";
    }

    @GetMapping("/checkout")
    public String checkout(ModelMap modelMap) {
        modelMap.addAttribute("addresses", checkoutService.getShippingAddress());
        modelMap.addAttribute("items", checkoutService.getCartItem());
        modelMap.addAttribute("orderDTO", new OrderDTO());
        return "checkout";
    }

    @PostMapping("/handle-order")
    public String order(ModelMap modelMap,
                        @ModelAttribute("orderDTO") OrderDTO orderDTO,
                        /*RedirectAttributes redirectAttributes*/
                        HttpSession httpSession) {
        if(orderDTO.getPaymentStatus().equalsIgnoreCase("PayPal")) {
            httpSession.setAttribute("orderDTO", orderDTO);
            try {
                Payment payment = payPalService.createPayment(orderDTO.getTotalAmount(), "http://localhost:8080/" + CANCEL_URL, "http://localhost:8080/" + SUCCESS_URL);

                for (Links link:payment.getLinks()) {
                    if(link.getRel().equals("approval_url")) {
                        return "redirect:" + link.getHref();
                    }
                }

            }catch (PayPalRESTException exception) {
                exception.printStackTrace();
            }
            return "redirect:/";
        }else {
            checkoutService.order(orderDTO);
            return "redirect:home?order=success";
        }
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "redirect:" + CANCEL_URL;
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId,
                             @RequestParam("PayerID") String payerId,
                             /*RedirectAttributes redirectAttributes*/
                             HttpSession httpSession) {
        OrderDTO orderDTO = (OrderDTO) httpSession.getAttribute("orderDTO");
        if (orderDTO != null) {
            checkoutService.order(orderDTO);
        }
        try {
            Payment payment = payPalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {

                return "redirect:" + SUCCESS_URL;
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:" + CANCEL_URL;
    }
}
