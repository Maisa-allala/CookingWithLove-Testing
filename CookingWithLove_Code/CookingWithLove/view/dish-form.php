<div class="form-container">
<form class="updateACC_form_element" method="post"  enctype="multipart/form-data">
        <div class="row">
            <div class="col-25">
                <label for="dis_name">Dish Name:</label>
            </div>
            <div class="row-75">
                <input type="text" id="dis_name" name="dis_name" value="<?= htmlspecialchars($dishInfo->dishName); ?>">
                <span class = "error-message"> <?php echo  $errordishname; ?></span>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="dis_catagory">Dish Type:</label>
            </div>
            <div class="row-75">
                <select name="dis_catagory" id="dis_catagory">
                <option value="none"> </option>
                <option value="salad"  <?= ($dishInfo->dishType == 'salad') ? 'selected' : ''; ?>>Salad</option>
                    <option value="soup"  <?= ($dishInfo->dishType == 'soup') ? 'selected' : ''; ?>>Soups</option>
                    <option value="Fish" <?= ($dishInfo->dishType == 'Fish') ? 'selected' : ''; ?>>Seafood</option>
                    <option value="chicken" <?= ($dishInfo->dishType == 'chicken') ? 'selected' : ''; ?>>Chicken</option>
                    <option value="beef" <?= ($dishInfo->dishType == 'beef') ? 'selected' : ''; ?>>Beef</option>
                    <option value="desserts" <?= ($dishInfo->dishType == 'desserts') ? 'selected' : ''; ?>>Desserts & Sweets</option>
                    <option value="sandwiches" <?= ($dishInfo->dishType == 'sandwiches') ? 'selected' : ''; ?>>Sandwiches</option>
                    <option value="cake" <?= ($dishInfo->dishType == 'cake') ? 'selected' : ''; ?>>Cake</option>
                    <option value="pasta" <?= ($dishInfo->dishType == 'pasta') ? 'selected' : ''; ?>>Pasta</option>
                    <option value="cookies" <?= ($dishInfo->dishType == 'cookies') ? 'selected' : ''; ?>>Cookies</option>
                    <option value="pizza" <?= ($dishInfo->dishType == 'pizza') ? 'selected' : ''; ?>>Pizza</option>
                    <option value="vegetable" <?= ($dishInfo->dishType == 'vegetable') ? 'selected' : ''; ?>>Vegetables</option>
                </select>
                <span class = "error-message"> <?php echo  $errordishType; ?></span>   
            </div>
        </div>

        <div class="row">
            <div class="col-25">
                <label for="dish_detailes" >Dish Details: </label>
            </div>
           
            <div class="row-75">
         
                <textarea id="dish_detailes" name="dish_detailes"  placeholder="ingredients.." style="height:250px"><?= htmlspecialchars($dishInfo->description); ?></textarea>
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
                    <option value="available" <?= ($dishInfo->status == 'available') ? 'selected' : ''; ?>>Available</option>
                    <option value="not_avail" <?= ($dishInfo->status == 'not_avail') ? 'selected' : ''; ?>>Not Available</option>
                </select>
                <span class = "error-message"> <?php echo  $errorstatus; ?></span>   
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="food_price">Price:</label>
            </div>
            <div class="row-75">
                <input type="number" id="food_price" name="food_price" min= "1" value="<?= htmlspecialchars($dishInfo->price); ?>">
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
            <input type="submit" value="Add Dish" >
          </div>
       
      </form>
    </div> 