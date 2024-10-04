package com.plavsic.ecommerce.service.cart;

import com.plavsic.ecommerce.model.book.Book;
import com.plavsic.ecommerce.model.cart.Cart;
import com.plavsic.ecommerce.model.cart.CartItem;
import com.plavsic.ecommerce.repository.book.BookRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartService {

    private final BookRepository bookRepository;

    public Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        return cart;
    }

    public void addToCart(HttpSession session,Long bookId,int quantity,String username) {
        Cart userCart;
        Book book = findBookById(bookId);
        boolean bookFound = false;

        if(book == null){
            return;
        }

        if(book.getQuantity() < quantity){
            return;
        }

        if(session.getAttribute("cart") == null) {
            userCart = new Cart();
            userCart.setUsername(username);
            userCart.getCartItems().add(new CartItem(book,quantity));
        }else{
            userCart = (Cart) session.getAttribute("cart");

            for(CartItem cartItem : userCart.getCartItems()) {
                if(cartItem.getBook().getId().equals(bookId)) {
                    bookFound = true;
                    break;
                }
            }
            if(!bookFound) {
                userCart.getCartItems().add(new CartItem(book,quantity));
            }
        }
        session.setAttribute("cart", userCart);
    }

    public void removeFromCart(HttpSession session,Long bookId) {
        Book book = findBookById(bookId);
        if(book == null){
            return;
        }
        if(session.getAttribute("cart") != null) {
            Cart userCart = (Cart) session.getAttribute("cart");
            userCart.getCartItems().removeIf(cartItem -> cartItem.getBook().equals(book));
        }
    }

    private Book findBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        return book;
    }

}
