<div class="form-container">
    <form class="updateACC_form_element" method="post" enctype="multipart/form-data">
        <div class="row">
            <div class="col-25">
                <label for="dis_name">Dish Name:</label>
            </div>
            <div class="row-75">
                <input type="text" id="dis_name" name="dis_name" value="<?= htmlspecialchars($dish->dishName); ?>">
                <span class = "error-message"> <?php echo  $errordishname; ?></span>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="dis_catagory">Dish Type:</label>
            </div>
            <div class="row-75">
                <select name="dis_catagory" id="dis_catagory" >
                <option value="none"> </option>
                    <option value="salad"  <?= ($dish->dishType == 'salad') ? 'selected' : ''; ?>>Salad</option>
                    <option value="soup"  <?= ($dish->dishType == 'soup') ? 'selected' : ''; ?>>Soups</option>
                    <option value="Fish" <?= ($dish->dishType == 'Fish') ? 'selected' : ''; ?>>Seafood</option>
                    <option value="chicken" <?= ($dish->dishType == 'chicken') ? 'selected' : ''; ?>>Chicken</option>
                    <option value="beef" <?= ($dish->dishType == 'beef') ? 'selected' : ''; ?>>Beef</option>
                    <option value="desserts" <?= ($dish->dishType == 'desserts') ? 'selected' : ''; ?>>Desserts & Sweets</option>
                    <option value="sandwiches" <?= ($dish->dishType == 'sandwiches') ? 'selected' : ''; ?>>Sandwiches</option>
                    <option value="cake" <?= ($dish->dishType == 'cake') ? 'selected' : ''; ?>>Cake</option>
                    <option value="pasta" <?= ($dish->dishType == 'pasta') ? 'selected' : ''; ?>>Pasta</option>
                    <option value="cookies" <?= ($dish->dishType == 'cookies') ? 'selected' : ''; ?>>Cookies</option>
                    <option value="pizza" <?= ($dish->dishType == 'pizza') ? 'selected' : ''; ?>>Pizza</option>
                    <option value="vegetable" <?= ($dish->dishType == 'vegetable') ? 'selected' : ''; ?>>Vegetables</option>
                </select>
                <span class = "error-message"> <?php echo  $errordishType; ?></span>   
            </div>
        </div>

        <div class="row">
            <div class="col-25">
                <label for="dish_detailes">Dish Details: </label>
            </div>
            <div class="row-75"> 
                <textarea id="dish_detailes" name="dish_detailes"  placeholder="ingredients.." style="height:250px"  ><?= htmlspecialchars($dish->description); ?></textarea>
                <span class = "error-message"> <?php echo  $errordescription; ?></span> 
            </div>
           
        </div>
        <div class="row">
            <div class="col-25">
                <label for="food_status">Food Status:</label>
            </div>
            <div class="row-75">
                <select name="food_status" id="food_status">
                <option value="none"> </option>
                    <option value="available" <?= ($dish->status == 'available') ? 'selected' : ''; ?>>Available</option>
                    <option value="not_avail" <?= ($dish->status == 'not_avail') ? 'selected' : ''; ?>>Not Available</option>
                </select>
                <span class = "error-message"> <?php echo  $errorstatus; ?></span>   
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="food_price">Price:</label>
            </div>
            <div class="row-75">
                <input type="number" id="food_price" name="food_price" value="<?= htmlspecialchars($dish->price); ?>">
                <span class = "error-message"> <?php echo  $errorprice; ?></span>   
            </div>
        </div>
       
     

        <div class="row">
            <div class="col-25">
                <label for="Upload_pic">Upload Photo:</label>
            </div>
            <div class="row-75">
                  
<input type="file" id="Upload_pic" name="Upload_pic" accept=".jpg, .jpeg, .png" required>

            </div>
        </div>
     
        <div class="row">
        <form method = "post">
           <input type="submit" name="modify" value="Update Dish" >
</form>
           <form method = "post" action ="../seller/DeleteDish.php">
            <input type="hidden" name="dishID" value ="<?= htmlspecialchars($dish->dishID); ?>">
            <input type="submit" name="delete" value="Delete Dish" >
</form>
          </div>
       
      </form>
    </div> 