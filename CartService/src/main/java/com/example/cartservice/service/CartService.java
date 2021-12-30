package com.example.cartservice.service;

import com.example.cartservice.model.Cart;
import com.example.cartservice.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getCartOfClient(Integer clientid)
    {
        return cartRepository.getByClientid(clientid);
    }

    public Cart addItem(Cart item)
    {
        Cart x = cartRepository.getCartByClientidAndISBN(item.getClientid(), item.getISBN());
        if(x==null)
        {
            return cartRepository.save(item);
        }
        else
        {
            x.setQuantity(x.getQuantity()+item.getQuantity());
            return cartRepository.save(x);
        }
    }

    public void deleteItem(Cart item){
        cartRepository.delete(item);
    }

    public void deleteAllCartOfClient(Integer clientid)
    {
        List<Cart> items = cartRepository.getByClientid(clientid);
        cartRepository.deleteAll(items);
    }
}