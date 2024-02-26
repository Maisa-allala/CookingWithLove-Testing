<div class="Login_form">   
    <form method="POST">
        
        <div class="a-box">
            <h2>Create New Password</h2>
            <p>Please create a new password that you don't use on any other site</p>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="username">Username:</label>
            </div>
            <div class="row-75">
                <input type="text" id="username" name="username" value="<?= htmlspecialchars($enteredUsername); ?>">
              
                <?php 
 $text = $errorusername;
 // Explode the text by the first period
$parts = explode('.', $text, 2);

// Save the parts into separate variables
$erroruser = isset($parts[0]) ? trim($parts[0]) : '';
$errorclarification = isset($parts[1]) ? trim($parts[1]) : '';
?>
<span class = "error-message"> <?php echo   $erroruser; ?></span>   
<span class = "error-toast"> <?php echo   $errorclarification; ?></span>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="new_Password">New Password:</label>
            </div>
            <div class="row-75">
                <input type="password" id="new_Password" name="new_Password" placeholder="Create New Password.." value="<?= htmlspecialchars($enteredpassword); ?>">
                <?php 
 $text = $errorpassword;
 // Explode the text by the first period
$parts = explode('.', $text, 2);

// Save the parts into separate variables
$errorpass = isset($parts[0]) ? trim($parts[0]) : '';
$errorclarification = isset($parts[1]) ? trim($parts[1]) : '';
?>
<span class = "error-message"> <?php echo   $errorpass; ?></span>   
<span class = "error-toast"> <?php echo   $errorclarification; ?></span>
            </div>
          </div>
      
        <div class="row">
            <div class="col-25">
              <label for="con_pass">Confirm Password:</label>
            </div>
            <div class="row-75">
              <input type="password" id="con_pass" name="con_pass"  placeholder="Confirm New Password .." value="<?= htmlspecialchars($enteredConfpassword); ?>">
              <span class = "error-message"> <?php echo   $errorconfirmpassword; ?></span>   
            </div>
        </div>
        <div class="row">
            <input type="submit" value="Save Password" >
          </div>
       
      </form>
      <div>
<?php if(! empty($error)): ?>
    <p class="styled-text-error"><?= $error?></p>
    <?php endif; ?>
</div>
</div>
      </div> 

      