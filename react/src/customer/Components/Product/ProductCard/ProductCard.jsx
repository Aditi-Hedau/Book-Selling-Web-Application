import React from 'react';
import "./ProductCard.css";
import { useNavigate } from 'react-router-dom';

const ProductCard = ({ product }) => {
  const navigate=useNavigate();
  
  return (
    <div onClick={()=>navigate(`/product/${5}`)} className='productCard w-[15rem] m-3 transition-all cursor-pointer'>
      <div className='h-[20rem]'>
        <img className='h-full w-full object-cover object-center' src={product.imageUrl} alt={product.title} />
      </div>

      <div className='textPart'>
        <div>
          <p className='font-bold opacity-60'>{product.brand}</p>
          <p>{product.title}</p>
        </div>
        <div className='flex space-x-2 items-center'>
          <p className='font-semibold'>{product.selling_price}</p>
          <p className='line-through opacity-50'>{product.price}</p>
          <p className='text-green-600 font-semibold'>{product.disscount}</p>
        </div>
      </div>
    </div>
  );
}

export default ProductCard;
