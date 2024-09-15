import React from 'react'
import RemoveCircleOutlineIcon from "@mui/icons-material/RemoveCircleOutline";
import { Button, IconButton } from "@mui/material";
import AddCircleOutlineIcon from "@mui/icons-material/AddCircleOutline";



const CartItem = () => {
  return (
    <div className="p-5 shadow-lg border rounded-md">
      <div className="flex items-center">
        <div className="w-[5rem] h-[5rem] lg:w-[9rem] lg:h-[9rem] ">
          <img
            className="w-full h-full object-cover object-top"
            src="https://s3.amazonaws.com/bookspan-media/covers/full/1425662.jpg"
            alt=""
          />
        </div>

        <div className="ml-5 space-y-1">
          <p className="font-semibold">SSSSSS</p>
          <p className="opacity-70 mt-2">Seller: XYZ</p>
          <div className="flex space-x-2 items-center pt-6">
            <p className="opacity-50 line-through">₹1050</p>
            <p className="font-semibold text-lg">₹500</p>
            <p className="text-green-600 font-semibold">20% off</p>
            
          </div>
        </div>
 
      </div>

      <div className="lg:flex items-center lg:space-x-10 pt-4">
            <div className="flex items-center space-x-2 ">
              <IconButton color="primary" aria-label="add an alarm">
                <RemoveCircleOutlineIcon />
              </IconButton>
              <span className="py-1 px-7 border rounded-sm">3</span>
                <IconButton sx={{color:"RGB(145 85 253)"}} color="primary" aria-label="add an alarm">
                  <AddCircleOutlineIcon />
                </IconButton>
              </div>

            <div className="flex text-sm lg:text-base mt-5 lg:mt-0">
              <Button  sx={{color:"RGB(145 85 253)"}}>Remove</Button>
            </div>


        </div>
    </div>
  )
}

export default CartItem