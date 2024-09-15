import React from 'react'
import AdreessCard from '../adreess/AdreessCard'
import OrderTraker from './OrderTraker'
import { Box, Button, Grid, Typography } from "@mui/material";
import StarBorderIcon from "@mui/icons-material/StarBorder";
import { deepPurple } from "@mui/material/colors";


const OrderDetails = () => {
  return (
    <div>
      <div className=" px-2 lg:px-36 space-y-7 ">
        <h1 className='font-bold text-x1 py-7'>Delivery Address</h1>
      <AdreessCard/>
      </div>

      <div className='py-10'>
        <OrderTraker activeStep={3}/>
      </div>


      <Grid className='space-y-5' container>
        {[1,1,1,1,1].map((item)=> <Grid container
            item
            className="shadow-xl rounded-md p-5 border"
            sx={{ alignItems: "center", justifyContent: "space-between" }}>

              <Grid item xs={6}>
                <div className='flex items-center space-x-4'>
                  <img className="w-[5rem] h-[5rem] object-cover object-top"
                    src="https://d2g9wbak88g7ch.cloudfront.net/productimages/images200/170/9780143459170.jpg" 
                    alt=''/>

                    <div className='space-y-2 ml-5'>
                      <p className='font-semibold'>Alok Kejriwal</p>
                      <p>Get Dressed And Parking Cars</p>
                      <p>Rs.600</p>
                    </div>
                </div>
             
             
             
              


              </Grid>

              <Grid item>

                <Box sx={{color:deepPurple[500]}}>
                <StarBorderIcon
                    sx={{ fontSize: "2rem" }}
                    className="px-2 text-5xl"
                  />
                  <span>Rate & Review Product</span>
                </Box>

              </Grid>



        </Grid>)}
        



      </Grid>





    </div>
  )
}

export default OrderDetails