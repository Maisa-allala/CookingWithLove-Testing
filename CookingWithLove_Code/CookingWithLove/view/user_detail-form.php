
    <div class="form-container">   
        <table class="detail_info_table1">
            <tr>
              <td>
                <span class="ID_info">User ID # </span><span class="account_info"><?= htmlspecialchars($user['accountID']); ?></span>
                
            </td>
              
              <td><span class="ID_info">User Information</span> <br>
                <table class="detail_info_table2">
                  <tr>
                    <td><span class="account_info">Account Status</span></td>
                    <td><?= $status; ?></td>
                  </tr>
                  <tr>
                    <td><span class="account_info">Name</span></td>
                    <td> <?= htmlspecialchars($user['firstName']); ?> <?= htmlspecialchars($user['lastName']); ?></td>
                  </tr>
                  <tr>
                    <td><span class="account_info">Account Requested Date</span></td>
                    <td><?= htmlspecialchars($user['accountRequestedDate']); ?></td>
                  </tr>
                  <tr>
                    <td><span class="account_info">Account Type:</span></td>
                    <td><?= htmlspecialchars($user['accountType']); ?></td>
                  </tr>
                  <tr>
                    <td><span class="account_info">Email:</span></td>
                    <td><?= htmlspecialchars($user['email']); ?></td>
                  </tr>
                  <tr>
                    <td><span class="account_info">Phone:</span></td>
                    <td><?= htmlspecialchars($user['phone']); ?></td>
                  </tr>
                  <tr>
                    <td><span class="account_info">DOB:</span></td>
                    <td><?= htmlspecialchars($user['DOB']); ?></td>
                  </tr>
                  <tr>
                    <td><span class="account_info">Address:</span></td>
                    <td><?= htmlspecialchars($user['street']); ?><?= htmlspecialchars($user['zip']); ?></td>
                  </tr>
                  <tr>
                    <td><span class="account_info">Username:</span></td>
                    <td><?= htmlspecialchars($user['userName']); ?></td>
                  </tr>
                </table>   
              </td>
            </tr>
            
        </table>    
        <div class="row">
            <input type="submit" class="approve_btn" value="Approve" >
          
        </div>  
       
   