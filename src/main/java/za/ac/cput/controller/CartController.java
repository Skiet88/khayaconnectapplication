package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import za.ac.cput.api.AddToCartApi;
import za.ac.cput.api.AssignCartToCustomerApi;
import za.ac.cput.api.RemoveFromCartApi;
import za.ac.cput.domain.Cart;
import za.ac.cput.service.cartService.CartService;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;
    private AddToCartApi addToCartApi;
    private RemoveFromCartApi removeFromCartApi;
    private AssignCartToCustomerApi assignCartToCustomerApi;

    @Autowired
    public CartController(CartService cartService,AddToCartApi addToCartApi,RemoveFromCartApi removeFromCartApi,AssignCartToCustomerApi assignCartToCustomerApi) {
        this.cartService = cartService;
        this.addToCartApi=addToCartApi;
        this.removeFromCartApi=removeFromCartApi;
        this.assignCartToCustomerApi=assignCartToCustomerApi;
    }


    @PostMapping("/create")
    public Cart create(@RequestBody Cart cart) {
        return cartService.create(cart);
    }

    @GetMapping("/read/{cartId}")
    public Cart read(@PathVariable("cartId") Long cartId) {
        System.out.println("Entered read method");
        return cartService.read(cartId);
    }

    @PostMapping("/update")
    public Cart update(@RequestBody Cart cart) {
        return cartService.update(cart);
    }

    @DeleteMapping("/delete/{cartId}")
    public boolean delete(@PathVariable("cartId") Long cartId) {
        return cartService.delete(cartId);
    }

    @GetMapping("/getall")
    public List<Cart> getall() {
        return cartService.getall();
    }

    @GetMapping("/totalPrice/{cartId}")

    public double getCartTotalPrice(@PathVariable("cartId") Long cartId) {
        return cartService.getCartTotalPrice(cartId);
    }

    @GetMapping("/quantity/{cartId}")
    public int quantity(@PathVariable("cartId") Long cartId) {
        return cartService.quantity(cartId);

    }
    @PostMapping("/{cartId}/addComicBook/{sku}")
    public Cart addToCart(@PathVariable("cartId") Long cartId,@PathVariable("sku") Long sku){
        return addToCartApi.addProductToCart(cartId,sku);
    }
    @PostMapping("/{cartId}/removeComicBook/{sku}")
    public Cart removeFromCart(@PathVariable("cartId") Long cartId,@PathVariable("sku") Long sku){
        return removeFromCartApi.removeBookFromCart(cartId,sku);
    }
    @GetMapping("/getCustomerCart/{email}")
    public Cart getCartWithCustomerEmail(@PathVariable("email") String email){
        System.out.println("eamil recieved"+email);
        return cartService.getCartByCustomerEmail(email);
    }
@GetMapping("/assignCartToCustomer/{customerId}")
    public Cart assignCartToCustomer(@PathVariable("customerId") Long customerId){
    System.out.println("Arrived at Controller method with Id:"+customerId);
        return assignCartToCustomerApi.assignCartToCustomer(customerId);
}

}


