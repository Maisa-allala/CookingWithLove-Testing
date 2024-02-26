<?php if(! empty($userInfo->errors)):?>
    <ul>
        <?php foreach ($userInfo->errors as $error): ?>
            <li> <?= $error?></li>
            <?php endforeach; ?>
</ul>
<?php endif; ?>

<div class="form-container">

<?php require 'view/welcomeuser.php'; ?>
    <form class="form_element" method="post">
      
        <div class="row">
             <div class="col-25">
            <label for="first_name">First Name:</label>
            </div>
            <div class="row-75">
            <input type="text" name="first_name" id="first_name" class="" value="<?= htmlspecialchars($user->firstName); ?>">
            <span class = "error-message"> <?php echo  $errorfname; ?></span>
        </div>
        </div>
        <div class="row">
            <div class="col-25">
            <label for="last_name">Last Name:</label>
            </div>
            <div class="row-75">
          
            <input type="text" name="last_name" id="last_name" class=""   value="<?= htmlspecialchars($user->lastName); ?>">
            <span class = "error-message"> <?php echo  $errorlname; ?></span>
        </div>
       
        </div>

        <div class="row">
            <div class="col-25">
                <label for="dob">DOB:</label>
            </div>
            <div class="row-75">
                <input type="text" id="dob" name="dob"  class=""  value="<?= htmlspecialchars($user->DOB); ?>">
                <span class = "error-message"> <?php echo   $errordob; ?></span>
            </div>
          </div>

          <div class="row">
            <div class="col-25">
                <label for="address">Address:</label>
            </div>
            <div class="row-75">
                <input type="text" id="address" name="address" class=""  value="<?= htmlspecialchars($user->street); ?>">
                <span class = "error-message"> <?php echo   $erroraddress; ?></span>
            </div>
        </div>


        <div class="row">
            <div class="col-25">
            <label for="zip">Zip Code:</label>
            </div>
            <div class="row-75">
            <input name="zip" id="zip"  type="number"  value="<?= htmlspecialchars($user->zip); ?>">
            <span class = "error-message"> <?php echo   $errorzip; ?></span>
        </div>
        </div> 
       
        <div class="row">
            <div class="col-25">
            <label for="email_address">Email:</label>
            </div>
            <div class="row-75">
            <input name="email_address" id="email" type="text"  value="<?= htmlspecialchars($user->email); ?>">
            <span class = "error-message"> <?php echo  $erroremail; ?></span>    
        </div>
        </div> 
         
        <div class="row">
            <div class="col-25">
            <label for="phone_number">Phone Number:</label>
            </div>
            <div class="row-75">
            <input name="phone_number" id="phone"  type="text"  value="<?= htmlspecialchars( $user->phone); ?>">
            <span class = "error-message"> <?php echo  $errorphone; ?></span>   
       
        </div>
        </div> 
 
       

       
       
        <div class="row">
            <input type="submit" value="Update Account" >
          </div>
       


</form>


</div> 