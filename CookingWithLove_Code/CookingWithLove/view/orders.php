<div class = "order-container">
<h2><?= $title ?></h2>
<?php if(empty($currentOrders)): ?>
    <p>No Orders found.</p>
    <?php else: ?>
        <table class="order-table">
            <thead>
                <th>Order #</th>
                <th>Date</th>
                <th>Total Price</th>
              
                <th>Delivery Type</th>
                <th>Status</th>
            </thead>
            <tbody>
                <?php foreach($currentOrders as $currentOrder): ?>
                    <tr>
                  
                
                        <td><a href="/cookingwithlove/buyer/orderItems.php?orderNum=<?= $currentOrder->orderNum; ?>&date=<?= urlencode($currentOrder->date);?>&totalPrice=<?= $currentOrder->totalPrice; ?>&deliveryType=<?= $currentOrder->deliveryType; ?>&status=<?= substr($title, 0, strpos($title, ' ') ?: strlen($title)); ?>"><?= htmlspecialchars($currentOrder->orderNum); ?>  </a></td>
                        <td><?= htmlspecialchars($currentOrder->date); ?></td>
                        <td>$<?= number_format($currentOrder->totalPrice, 2); ?></td>
                        <td><?= htmlspecialchars($currentOrder->deliveryType); ?></td>
                        <td><?= substr($title, 0, strpos($title, ' ') ?: strlen($title)); ?></td>
                    </tr>
                <?php endforeach; ?>
            </tbody>
        </table>
    <?php endif; ?>

                </div>