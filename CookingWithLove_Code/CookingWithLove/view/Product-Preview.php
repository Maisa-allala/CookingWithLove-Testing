
  <div class="product">
    <div class="popup-view active">
      <div class="popup-card">
        <a><i class="fas fa-times close-btn"></i></a>
        <div class="product-img">
          <img src="Dish_Image/FattoushSalad_S.jpg" alt="">
        </div>
        <div class="info">
          <h2>Fattoush salad </h2><br>
          
          <p> cucumbers, tomatoes, purslane leaves (or lettuce), radish, and green onions. Fresh herbs like parsely or mint, or
            both
          </p>
        
            <div class="row">
              <div class="col-10">
                <label for="dish_price" class="dish_price">Price:</label>
              </div>
              <div class="row-15">
                <span> $ <?=  htmlspecialchars($dish['price']); ?></span>
              </div>
          </div>
          <div class="row">
            <div class="col-10">
              <label for="quantity" class="dish_quantity">Quantity:</label>
            </div>
            <div class="row-15">
              <input type="number" name="quantity" value="1" min="1" max="10">
            </div>
        </div>
          
  

        <div class="row">
          <div class="col-10">
            <label for="contact_seller" class="contact_seller">Contact Seller:</label> 
          </div>
          <div class="row-15">
            Email: <a href="mailto:atasnim@umich.edu"><?=  htmlspecialchars($dish['email']); ?></a>
            <span class="seller_phone">Phone: <?=  htmlspecialchars($dish['phone']); ?></span>
          </div>
      </div>
          
            
            <div class="row">
              <br>
              <a href="#" class="add-cart-btn">Add to Cart</a>
            </div>  
       
      </div>
    </div>
  </div>

 