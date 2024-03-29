import React from "react";
import { Redirect } from "react-router-dom";
import check_expired from "../utils/useToken";

export default function PageHeader(props) {
    
  if (!localStorage.getItem("token")) {
    return <Redirect to="/login" />;
  } else {
    if (check_expired()) {
      return <Redirect to="/login" />;
    }
  }

  return (
    <div>
      <div className="logo">
        <a href="/">
          <img
            id="longLogo"
            src="longLogo.png"
            alt="Long Logo"
            width="15%"
            height="auto"
          ></img>
          <img
            id="shortLogo"
            src="shortLogo.png"
            alt="Continental Logo"
            width="20%"
            height="auto"
          ></img>
        </a>
      </div>
      <ul>
        <li>
          <a href="#/">Home</a>
        </li>
        <li>
          <a href="#/books">Books</a>
        </li>
        <li>
          <a href="#/orders">Orders</a>
        </li>
        <li>
          <a href="#/cart">Cart</a>
        </li>
        <li>
          <a href="#/wishlist">Wishlist</a>
        </li>
        <li>
          <a href="#/logout">Logout</a>
        </li>
      </ul>
    </div>
  );
}
