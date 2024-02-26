<div class = "order-container">
<h2><?= $title ?></h2>

<?php if(empty($sellerOrders)): ?>
    <p>No Orders found.</p>
    <?php else: ?>
        <table>
            <thead>
                <th>Buyer</th>
                <th>Order #</th>
                <th>Order Date</th>
                <th>Total Price</th>
                <th>Status</th>
               
            </thead>
            <tbody>
                <?php foreach($sellerOrders as $sellerOrder): ?>
                    <tr>
                  
                    <td><?= htmlspecialchars($sellerOrder->userName); ?></td>
                    <td><a href="/cookingwithlove/seller/orderItems.php?orderNum=<?= $sellerOrder->orderNum; ?>&date=<?= urlencode($sellerOrder->date);?>&totalPrice=<?= $sellerOrder->totalPrice; ?>&status=<?= substr($title, 0, strpos($title, ' ') ?: strlen($title)); ?>&buyer=<?= $sellerOrder->userName; ?>"><?= htmlspecialchars($sellerOrder->orderNum); ?>  </a></td>
                   
                        <td><?= htmlspecialchars($sellerOrder->date); ?></td>
                        <td>$<?= number_format($sellerOrder->totalPrice, 2); ?></td>
                        <td><?= htmlspecialchars($status); ?></td>
              
                    </tr>
                <?php endforeach; ?>
            </tbody>
        </table>
    <?php endif; ?>

                </div>