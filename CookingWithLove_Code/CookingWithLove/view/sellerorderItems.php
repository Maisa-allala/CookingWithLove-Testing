<div class = "order-container">
<div class="order-header">
<h2>Buyer: <?= $buyer; ?></h2>
<h2>Order #: <?= $orderNum; ?></h2>
    <h2>Order Date: <?= $date; ?></h2>
    <h2>Total Price: $<?= $totalPrice; ?></h2>
    <h2>Status: <?= $status; ?></h2>

</div>
    <?php if (empty($sellerOrderItems)): ?>
        <p>No items found for this order.</p>
    <?php else: ?>
        <form method="post">
            <?php foreach ($sellerOrderItems as $sellerOrderItem): ?>
                <div class="order-item">
                
                <img src="../<?=  htmlspecialchars($sellerOrderItem['photoPath']); ?>" alt="<?= htmlspecialchars($sellerOrderItem['dishName']); ?>" width="200" height="auto">
                    <div class="order-details">
                      
                        <p>Dish Name: <?= htmlspecialchars($sellerOrderItem['dishName']); ?></p>
                        <p>Quantity: <?= htmlspecialchars($sellerOrderItem['quantity']); ?></p>
                        <p>Price: $<?= number_format($sellerOrderItem['price'], 2); ?></p>
                        <p>Total Price: $<?= number_format($sellerOrderItem['price'] * $sellerOrderItem['quantity'] , 2); ?></p>
                    </div>
                </div>
            <?php endforeach; ?>
            <?php if ($status === "PickReady"): ?>
        <input type="submit" name="MarkasPickedUp" value="Mark as Picked Up">
    <?php elseif ($status === "Completed" || $status === "Cancelled"): ?>
        <input type="hidden" name="MarkasComplete" value="Mark as Complete">
    <?php else: ?>
        <input type="submit" name="MarkasComplete" value="Mark as Complete">
    <?php endif; ?>
</form>
<?php endif; ?>
   
</div>


            </div>