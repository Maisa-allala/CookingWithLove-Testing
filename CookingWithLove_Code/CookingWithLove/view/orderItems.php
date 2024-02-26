<div class = "order-container">
<div class="order-header">
    <h2>Order Placed: <?= $date; ?></h2>
    <h2>Total: <?= $totalPrice; ?></h2>
    <h2>Delivery Type: <?= $deliveryType; ?></h2>
    <h2>Order #: <?= $orderNum; ?></h2>
    <h2>Status: <?= $status; ?></h2>
</div>
    <?php if (empty($currentOrderItems)): ?>
        <p>No items found for this order.</p>
    <?php else: ?>
        <form method="post">
            <?php foreach ($currentOrderItems as $currentOrderItem): ?>
                <div class="order-item">
                
                <img src="../<?=  htmlspecialchars($currentOrderItem['photoPath']); ?>" alt="<?= htmlspecialchars($currentOrderItem['dishName']); ?>" width="200" height="auto">
                    <div class="order-details">
                      
                        <p>Dish Name: <?= htmlspecialchars($currentOrderItem['dishName']); ?></p>
                        <p>Quantity: <?= htmlspecialchars($currentOrderItem['quantity']); ?></p>
                        <p>Price: $<?= number_format($currentOrderItem['price'], 2); ?></p>
                        <p>Total Price: $<?= number_format($currentOrderItem['price'] * $currentOrderItem['quantity'] , 2); ?></p>
                    </div>
                </div>
            <?php endforeach; ?>
            <?php if ($status === "Current"): ?>     
    <input type="hidden" name="orderNum" value="<?= $orderNum; ?>">

    <input type="submit" name="CancelOrder" value= "Cancel Order">
    <?php endif; ?>
        </form>
    <?php endif; ?>
  
   
</div>


            </div>