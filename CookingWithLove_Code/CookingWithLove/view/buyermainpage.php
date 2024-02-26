<div class="grid-container">
<?php require 'welcomeuser.php'; ?>
<?php

$currentOrderTitle = "Current Orders";
$currentOrderCancelled = 'N';
$currentOrderDeliveryComplete = 'N';
$currentOrderStatus = "Current";

$previousOrderTitle = "Previous Orders";
$previousOrderCancelled = 'N';
$previousOrderDeliveryComplete = 'Y';
$previousOrderStatus = "Previous";

$cancelOrderTitle = "Cancelled Orders";
$cancelOrderCancelled = 'Y';
$cancelOrderDeliveryComplete = 'N';
$cancelOrderStatus = "Cancelled";
?>

<a href="/cookingwithlove/update_user.php" button class="button update">Update Account</button></a>
<a href="/cookingwithlove/buyer/cart.php" button class="button check_order">View Cart</button></a>
<a href="/cookingwithlove/buyer/orders.php?cancelled=<?= $previousOrderCancelled; ?>&deliveryComplete=<?= $previousOrderDeliveryComplete; ?>&title=<?= $previousOrderTitle; ?>&status=<?= $previousOrderStatus; ?>" button class="button pre_order">Previous Orders</button></a>
<a href="/cookingwithlove/buyer/orders.php?cancelled=<?= $currentOrderCancelled; ?>&deliveryComplete=<?= $currentOrderDeliveryComplete; ?>&title=<?= $currentOrderTitle; ?>&status=<?= $currentOrderStatus; ?>" button class="button buyer_cur_order">Current Orders</button></a>
<a href="/cookingwithlove/buyer/orders.php?cancelled=<?= $cancelOrderCancelled; ?>&deliveryComplete=<?= $cancelOrderDeliveryComplete; ?>&title=<?= $cancelOrderTitle; ?>&status=<?= $cancelOrderStatus; ?>" button class="button seller_cancel_order">Cancelled Orders</button></a>
<a href="/cookingwithlove/buyer/AllCategories.php" button class="button search">Search</button></a>
</div> 