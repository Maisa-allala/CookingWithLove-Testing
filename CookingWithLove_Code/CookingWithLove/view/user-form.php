<?php if(! empty($userInfo->errors)):?>
    <ul>
        <?php foreach ($userInfo->errors as $error): ?>
            <li> <?= $error?></li>
            <?php endforeach; ?>
</ul>
<?php endif; ?>
<div class="form-container">
    
    <form class="form_element" method="POST">
      
        <div class="row">
             <div class="col-25">
            <label for="first_name">First Name:</label>
            </div>
            <div class="row-75">
            <input type="text" name="first_name" id="first_name" class="" value="<?= htmlspecialchars($userInfo->first_name); ?>">
            <span class = "error-message"> <?php echo  $errorfname; ?></span>
        </div>
        </div>
        <div class="row">
            <div class="col-25">
            <label for="last_name">Last Name:</label>
            </div>
            <div class="row-75">
          
            <input type="text" name="last_name" id="last_name" class=""   value="<?= htmlspecialchars($userInfo->last_name); ?>">
            <span class = "error-message"> <?php echo  $errorlname; ?></span>
        </div>
       
        </div>

        <div class="row">
            <div class="col-25">
                <label for="dob">DOB:</label>
            </div>
            <div class="row-75">
                <input type="text" id="dob" name="dob"  placeholder="YYYY-MM-DD" class=""  value="<?= htmlspecialchars($userInfo->dob); ?>">
                <span class = "error-message"> <?php echo   $errordob; ?></span>
            </div>
          </div>

          <div class="row">
            <div class="col-25">
                <label for="address">Address:</label>
            </div>
            <div class="row-75">
                <input type="text" id="address" name="address" class=""  value="<?= htmlspecialchars($userInfo->street); ?>">
                <span class = "error-message"> <?php echo  $erroraddress; ?></span>
            </div>
        </div>

       
      
            <div class="row">
            <div class="col-25">
            <label for="zip">Zip Code:</label>
            </div>
            <div class="row-75">
            <input name="zip" id="zip"  type="text"  value="<?= htmlspecialchars($userInfo->zip); ?>">
            <span class = "error-message"> <?php echo   $errorzip; ?></span>
        </div>
        </div>  
       

        <div class="row">
            <div class="col-25">
            <label for="phone_number">Phone Number:</label>
            </div>
            <div class="row-75">
            <input name="phone_number" id="phone"  type="text" placeholder="ex: 999-234-0000" value="<?= htmlspecialchars($userInfo->phone); ?>">
            <span class = "error-message"> <?php echo  $errorphone; ?></span>   
        </div>
       
       </div>
       <div class="row">
            <div class="col-25">
            <label for="email_address">Email:</label>
            </div>
            <div class="row-75">
            <input name="email_address" id="email" type="text"  value="<?= htmlspecialchars($userInfo->email); ?>">
            <span class = "error-message"> <?php echo  $erroremail; ?></span>    
        </div>
        </div> 
    

        <div class="row">
            <div class="col-25">
            <label for="accountType">Account Type:</label>
            </div>
            <div class="row-75">
            <select name="accountType">
            <option value="none">   </option>
    <option value="Buyer" <?= ($userInfo->accountType == 'Buyer') ? 'selected' : ''; ?>>BUYER</option>
    <option value="Seller" <?= ($userInfo->accountType == 'Seller') ? 'selected' : ''; ?>>SELLER</option>
   
  
</select>
<span class = "error-message"> <?php echo   $erroracounttype; ?></span> 
</div>  
        </div>

        <div class="row">
            <div class="col-25">
            <label for="username">Username:</label>
            </div>
            <div class="row-75">
            <input name="username" id="username"  type="text"  value="<?= htmlspecialchars($userInfo->username); ?>">
            <span class = "error-message"> <?php echo   $errorusername; ?></span>  
            
            
            
        </div>
        </div>
        <div class="row">
            <div class="col-25">
            <label for="password">Password:</label>
            </div>
            <div class="row-75">
            <input name="password"  type="password" id="password"  class="" placeholder="ex: Project525$" >
           

            <?php 
 $text = $errorpassword;
 // Explode the text by the first period
$parts = explode('.', $text, 2);

// Save the parts into separate variables
$error = isset($parts[0]) ? trim($parts[0]) : '';
$errorclarification = isset($parts[1]) ? trim($parts[1]) : '';
?>
<span class = "error-message"> <?php echo   $error; ?></span>   
<span class = "error-toast"> <?php echo   $errorclarification; ?></span>          


        </div>
        </div>
        <div class="row">
            <div class="col-25">
            <label for="confirm_password">Confirm Password:</label>
            </div>
            <div class="row-75">
            <input name="confirm_password"  type="password" id="confirm_password"  class=""  >
            <span class = "error-message"> <?php echo   $errorconfirmpassword; ?></span>   
        </div>
        </div>
       
        <div class="row">
            <input type="submit" value="Create Account" >
          </div>
       


</form>


</div> 