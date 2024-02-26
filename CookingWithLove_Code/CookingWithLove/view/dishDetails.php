<div class = "order-container">
<div class="order-header">
    <h2>Dish #: <?= $dishID; ?></h2>
    
    <h2>Dish Requested Date: <?= $dishRequestedDate; ?></h2>
    <h2>Status: <?= $status; ?></h2>



</div>
    <?php if (empty($dishInfo)): ?>
        <p>No items found for this dish.</p>
    <?php else: ?>
        <form method="post">
            <?php foreach ($dishInfo as $dish): ?>
                <div class="order-item">
                
                <img src="../<?=  htmlspecialchars($dish['photoPath']); ?>" alt="<?= htmlspecialchars($dish['dishName']); ?>" width="200" height="auto">
                    <div class="order-details">
                    <h2>Dish Name: <?= $dishName; ?></h2>
                        <p>Description: <?= htmlspecialchars($dish['description']); ?></p>
                        <p>Dish Type: <?= htmlspecialchars($dish['dishType']); ?></p>
                        <p>Price: <?= htmlspecialchars($dish['price']); ?></p>
                        <p>Seller Name: <?= htmlspecialchars($dish['userName']); ?><p>
                    </div>
                </div>
            <?php endforeach; ?>
            
    <input type="hidden" name="dishID" value="<?= $dishID; ?>">
    <?php if ($status === "UnPublished"): ?>
    <input type="submit" name="ApproveforPublishing" value= "Publish">
    <?php endif; ?>
        </form>
    <?php endif; ?>
  
   
</div>


            </div>