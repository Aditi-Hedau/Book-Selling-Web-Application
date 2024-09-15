import React from 'react'
import { Route, Routes } from 'react-router-dom'
import Homepage from "../Pages/Homepage";
import Cart from '../customer/Components/Cart/Cart';
import Navigation from '../customer/Components/Navbar/Navigation';
//import Footer from "../customer/Components/footer/Footer";
import Product from "../customer/Components/Product/Product/Product"
import ProductDetails from '../customer/Components/Product/ProductDetails/ProductDetails';
import OrderDetails from '../customer/Components/orders/OrderDetails';
import OrderTraker from '../customer/Components/orders/OrderTraker';
import Checkout from '../customer/Components/Checkout/Checkout';
import Order from "../customer/Components/orders/Order";



const CustomerRoutes = () => {
  return (
    <div>
      <div>
        <Navigation/>

      </div>
      <Routes>
        <Route path='/' element={<Homepage/>}></Route>
        <Route path='/cart' element={<Cart/>}></Route>
        <Route path="/:lavelOne/:lavelTwo/:lavelThree" element={<Product />}></Route>
        <Route path="/product:productId" element={<ProductDetails/>}></Route>
        <Route path="/checkout" element={<Checkout/>}></Route>
        <Route path="/account/order" element={<Order/>}></Route>
        <Route path="/account/order/:orderId" element={<OrderDetails />}></Route>




        





      </Routes>
      
    </div>
  )
}

export default CustomerRoutes