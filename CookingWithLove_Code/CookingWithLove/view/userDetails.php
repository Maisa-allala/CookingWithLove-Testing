
    <?php if (empty($userInfo)): ?>
        <p>No items found for this user.</p>
    <?php else: ?>
        <form method="post">
            <?php foreach ($userInfo as $user): ?>
               
    <div class="user_detailForm">  
        <div class="row-heading">
            <div class="col-5">
               <label for="user_id">User ID# <?= htmlspecialchars($user['accountID']); ?></label>
            </div>
             <div class="row-10">
               <label for="status">Status: <?= $status; ?></label>
             </div>
        </div> 
        <br><br>
        <div class="row">
            <div class="col-5">
               <label for="Name"><span class="account_info">Name</span></label>
            </div>
             <div class="row-10">
               <label for="Name"><?= htmlspecialchars($user['firstName']); ?> <?= htmlspecialchars($user['lastName']); ?></label>
             </div>
        </div>  
        <br>
        <div class="row">
            <div class="col-5">
               <label for="request-date"><span class="account_info">Requested Date</span></label>
            </div>
             <div class="row-10">
               <label for="request-date"><?= htmlspecialchars($user['accountRequestedDate']); ?></label>
             </div>
        </div>   
        <br><br>
        <div class="row">
            <div class="col-5">
               <label for="Account Type:"><span class="account_info">Account Type:</span></label>
            </div>
             <div class="row-10">
               <label for="Account Type:"><?= htmlspecialchars($user['accountType']); ?></label>
             </div>
        </div> 
        <br>
        <div class="row">
            <div class="col-5">
               <label for="email"><span class="account_info">Email:</span></label>
            </div>
             <div class="row-10">
               <label for="email"><?= htmlspecialchars($user['email']); ?></label>
             </div>
        </div>  
        <br>
        <div class="row">
            <div class="col-5">
               <label for="phone"><span class="account_info">Phone:</span></label>
            </div>
             <div class="row-10">
               <label for="phone"><?= htmlspecialchars($user['phone']); ?></label>
             </div>
        </div>  
        <br>
        <div class="row">
            <div class="col-5">
               <label for="dob"><span class="account_info">DOB:</span></label>
            </div>
             <div class="row-10">
               <label for="dob"><?= htmlspecialchars($user['DOB']); ?></label>
             </div>
        </div>  
        <br><br>
        <div class="row">
            <div class="col-5">
               <label for="address"><span class="account_info">Address:</span></label>
            </div>
             <div class="row-10">
               <label for="address"><?= htmlspecialchars($user['street']); ?>  <?= htmlspecialchars($user['zip']); ?></label>
             </div>
        </div>  
        <br>
        <div class="row">
            <div class="col-5">
               <label for="username"><span class="account_info">Username: </span></label>
            </div>
             <div class="row-10">
               <label for="username"><?= htmlspecialchars($user['userName']); ?></label>
             </div>
        </div>  
        <br><br>
             
            <?php endforeach; ?>
            
    <input type="hidden" name="accountID" value="<?= $accountID; ?>">
    <?php if ($status === "Not Active"): ?>
    <div class="row">
    <input type="submit" name="MarkasActive"   class="approve_btn" value= "Approve">
    </div>  
    <?php endif; ?>
        </form>
    <?php endif; ?>
   
