<div class = "order-container">
<h2><?= $title ?></h2>
<?php if(empty($users)): ?>
    <p>No Users found.</p>
    <?php else: ?>
        <table class="order-table">
            <thead>
                <th>User #</th>
                <th>UserName</th>
                <th>Account Requested Date</th>
                <th>Status</th>
              
            </thead>
            <tbody>
                <?php foreach($users as $user): ?>
                    <tr>
                    <td><a href="/cookingwithlove/admin/userDetails.php?accountID=<?= $user->accountID; ?>&userName=<?= $user->userName;?>&accountRequestedDate=<?= $user->accountRequestedDate; ?>&status=<?= $user->is_active === 'Y' ? 'Active' : 'Not Active'; ?>"><?= htmlspecialchars($user->accountID); ?>  </a></td>
                  
                     
                        <td><?= htmlspecialchars($user->userName); ?></td>
                        
                        <td><?= htmlspecialchars($user->accountRequestedDate); ?></td>
                        <td><?= htmlspecialchars($status); ?></td>
                    </tr>
                <?php endforeach; ?>
            </tbody>
        </table>
    <?php endif; ?>

                </div>