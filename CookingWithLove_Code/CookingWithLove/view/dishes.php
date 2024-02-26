<div class = "order-container">
<h2><?= $title ?></h2>
<?php if(empty($dishes)): ?>
    <p>No Dishes found.</p>
    <?php else: ?>
        <table class="order-table">
            <thead>
                <th>Dish #</th>
                <th>Dish Name</th>
                <th>Account Requested Date</th>
                <th>Status</th>
              
            </thead>
            <tbody>
                <?php foreach($dishes as $dish): ?>
                    <tr>
                    <td><a href="/cookingwithlove/admin/dishDetails.php?dishID=<?= $dish->dishID; ?>&dishName=<?= $dish->dishName;?>&dishRequestedDate=<?= $dish->DishRequestedDate; ?>&status=<?= $status; ?>"><?= htmlspecialchars($dish->dishID); ?>  </a></td>
                  
                     
                        <td><?= htmlspecialchars($dish->dishName); ?></td>
                        
                        <td><?= htmlspecialchars($dish->DishRequestedDate); ?></td>
                        <td><?= htmlspecialchars($status); ?></td>
                    </tr>
                <?php endforeach; ?>
            </tbody>
        </table>
    <?php endif; ?>

                </div>