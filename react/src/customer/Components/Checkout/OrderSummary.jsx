import React from 'react'
import AdreessCard from '../adreess/AdreessCard'
import CartItem from '../Cart/CartItem'
import { Badge, Button } from "@mui/material";

const OrderSummary = () => {
  return (
    <div className="space-y-5">
        <div className="p-5 shadow-lg rounded-md border ">
            <AdreessCard/>
        </div>
      
        <div>

<div className="lg:grid grid-cols-3 relative">
<div className="col-span-2 lg:px-5 bg-white">

{[1,1,1].map((item)=><CartItem/>)}
</div>
<div className="px-5 sticky top-0 h-[100vh] mt-5 lg:mt-0 ">
<div className="border p-5 bg-white shadow-lg rounded-md">
  <p className="font-bold opacity-60 pb-4">PRICE DETAILS</p>
  <hr />
  <div className="space-y-3 font-semibold">
    <div className="flex justify-between pt-3 text-black ">
      <span>Price </span>
      <span>₹1000</span>
    </div>
    <div className="flex justify-between">
      <span>Discount</span>
      <span className="text-green-700">-₹234567</span>
    </div>
    <div className="flex justify-between">
      <span>Delivery Charges</span>
      <span className="text-green-700">Free</span>
    </div>
    <hr />
    <div className="flex justify-between font-bold text-lg">
      <span>Total Amount</span>
      <span className="text-green-700">₹0000000</span>
    </div>
  </div>

  <Button
    //onClick={() => navigate("/checkout?step=2")}
    variant="contained"
    type="submit"
    sx={{ padding: ".8rem 2rem", marginTop: "2rem", width: "100%", bgcolor:"#9155fd" }}
  >
    Check Out
  </Button>

</div>

</div>
</div>

</div>




    </div>
  )
}

export default OrderSummary