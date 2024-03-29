package com.example.orderService.service;

import com.example.orderService.model.BookOrders;
import com.example.orderService.model.Status;
import com.example.orderService.repository.BookOrdersRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final BookOrdersRepository bookOrdersRepository;

    public OrderService(BookOrdersRepository bookOrdersRepository) {
        this.bookOrdersRepository = bookOrdersRepository;
    }

    public List<BookOrders> getBookOrder(Integer client_id){
        bookOrdersRepository.setCollectionName("client."+client_id);
        return bookOrdersRepository.findAll();
    }

    public Boolean addBookOrder(BookOrders booksOrder, Integer client_id) {
        bookOrdersRepository.setCollectionName("client." + client_id);
        UriComponentsBuilder renewURIBuilder= UriComponentsBuilder.fromHttpUrl("http://127.0.0.1:8090").path("/api/bookcollection/books/").path("/stockChange");
        UriComponents uriComponent=renewURIBuilder.build(true);
        URI uri=uriComponent.toUri();
        Boolean x = new RestTemplate().postForObject(uri,booksOrder.getItems(), Boolean.class);
        if (Boolean.TRUE.equals(x))
            bookOrdersRepository.save(booksOrder);
        return x;
    }

    public Boolean cancelOrder(Integer client_id, String id){
        bookOrdersRepository.setCollectionName("client." + client_id);
        BookOrders book = bookOrdersRepository.getById(id);
        if(book !=null)
        {
            if(book.getStatus()==Status.ACTIVA)
            {
                book.setStatus(Status.ANULATA);
                bookOrdersRepository.save(book);
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }
}

