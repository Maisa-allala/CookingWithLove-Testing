

<div class="grid-container">

<?php require 'welcomeuser.php'; ?>

<?php

$activeOrderTitle = "Active Orders";
$activeOrderCancelled = 'N';
$activeOrderDeliveryComplete = 'N';
$activeOrderComplete = 'N';
$activeOrderStatus = "Active";

$completedOrderTitle = "Completed Orders";
$completedOrderCancelled = 'N';
$completedOrderDeliveryComplete = 'Y';
$completedOrderComplete = 'Y';
$completedOrderStatus = "Completed";

$ReadytoPickUpOrderTitle = "PickReady Orders";
$ReadytoPickUpCancelled = 'N';
$ReadytoPickUpDeliveryComplete = 'N';
$ReadytoPickUpOrderComplete = 'Y';
$ReadytoPickUpStatus = "PickReady";

$cancelOrderTitle = "Cancelled Orders";
$cancelOrderCancelled = 'Y';
$cancelOrderDeliveryComplete = 'N';
$cancelOrderComplete = 'N';
$cancellledOrderStatus = "Cancelled";

?>


<a href="/cookingwithlove/update_user.php" button class="button update">Update Account</button></a>
<a href="AddDish.php" button class="button add_dish">Add New Dish</button></a>
<a href="CurrentDishes.php" button class="button seller_cur_dish">Current Dishes</button></a>

<a href="/cookingwithlove/seller/orders.php?cancelled=<?= $completedOrderCancelled; ?>&deliveryComplete=<?= $completedOrderDeliveryComplete; ?>&title=<?= $completedOrderTitle; ?>&status=<?= $completedOrderStatus; ?>&orderComplete=<?= $completedOrderComplete; ?>" button class="button seller_com_order">Completed Orders</button></a>
<a href="/cookingwithlove/seller/orders.php?cancelled=<?= $activeOrderCancelled; ?>&deliveryComplete=<?= $activeOrderDeliveryComplete; ?>&title=<?= $activeOrderTitle; ?>&status=<?= $activeOrderStatus; ?>&orderComplete=<?= $activeOrderComplete; ?>" button class="button active_order">Active Orders</button></a>
<a href="/cookingwithlove/seller/orders.php?cancelled=<?= $ReadytoPickUpCancelled; ?>&deliveryComplete=<?= $ReadytoPickUpDeliveryComplete; ?>&title=<?= $ReadytoPickUpOrderTitle; ?>&status=<?= urlencode($ReadytoPickUpStatus); ?>&orderComplete=<?= $ReadytoPickUpOrderComplete; ?>" button class="button ready_pickUp">Ready for Pickup</button></a>
<a href="/cookingwithlove/seller/orders.php?cancelled=<?= $cancelOrderCancelled; ?>&deliveryComplete=<?= $cancelOrderDeliveryComplete; ?>&title=<?= $cancelOrderTitle; ?>&status=<?= $cancellledOrderStatus; ?>&orderComplete=<?= $cancelOrderComplete; ?>" button class="button seller_cancel_order">Cancelled Orders</button></a>
<a href="/cookingwithlove/" button class="button search">Search</button></a>


</div> 