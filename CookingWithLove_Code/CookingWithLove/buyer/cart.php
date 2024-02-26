
<?php
$lifetime = 60 * 60 * 24 * 14;
session_set_cookie_params($lifetime, '/');
require '../model/init.php';
require 'cart_functions.php'; 
$conn = require '../model/db.php';
// Instantiate Order object
$order = new Order();


$cartData = isset($_SESSION['cart']) ? $_SESSION['cart'] : getCartDataFromCookie();
function getCartDataFromCookie() {
    $cookieName = 'user_cart';
    
    if (isset($_COOKIE[$cookieName])) {
        $cartData = unserialize($_COOKIE[$cookieName]);
        return is_array($cartData) ? $cartData : array();
    } else {
        return array();
    }
}

function setCartDataToCookie($cartData) {
    $cookieName = 'user_cart';
    $cartDataSerialized = serialize($cartData);

   // Set cart data to the cookie
   $cookieSet = setcookie($cookieName, $cartDataSerialized, time() + 60 * 60 * 24 * 14, '/'); // 14 days
   return $cookieSet;
}
if ($_SERVER['REQUEST_METHOD'] == "POST") {
    if (isset($_POST['updateCart'])) {
        // Check if new quantities are set in the submitted form
        if (isset($_POST['newqty']) && is_array($_POST['newqty'])) {
            // Loop through the new quantities
            foreach ($_POST['newqty'] as $key => $qty) {
                // Check if the quantity has changed
                if ($cartData[$key]['quantity'] != $qty) {
                    // Update the item in the cart
                    update_cart_item($key, $qty, $cartData);
                }
            }
        }
    } elseif (isset($_POST['checkout'])) {
        if(!empty($cartData)){
        // Add items from the cart to the order
        foreach ($cartData as $cartItem) {
            $order->dishID = $cartItem['dishID'];
            $order->deliveryType = "PickUp";
            $order->dishname = $cartItem['dishName'];
            $order->quantity = $cartItem['quantity'];
            $order->price = $cartItem['price'];
            $order->accountID = $_SESSION['accountID'];
            $order->totalPrice = get_cart_subtotal($cartData);
          
        }

        // Complete the order creation
        if ($order->createOrder($conn)) {
            echo '<script>alert("Order has been added successfully.") ;</script>';
          // Clear the cart after successful checkout
          $cartData = array();
        setCartDataToCookie($cartData);
        } else {
            echo '<script>alert("Failed to add order.") ;</script>';
        }
        
       
    }}
}
// Set $_SESSION['cart'] after processing
$_SESSION['cart'] = $cartData;
// Update the cookie with the latest cart data
setCartDataToCookie($_SESSION['cart']);

?>


<?php require '../view/header.php'; ?>
<?php require '../view/welcomeuser.php'; ?>

<?php require '../view/cart_view.php'; ?>
<?php require '../view/footer.php'; ?>