<?php
// cart_functions.php

function add_to_cart($order, &$cart) {
    // Check if the dishID is set in the submitted form
    if (isset($order->dishID)) {
        // If item already exists in cart, update quantity
        $key = $order->dishID;
        if (isset($cart[$key])) {
            $order->quantity += $cart[$key]['quantity'];
            update_cart_item($key, $order->quantity, $cart);
            return true;
        }

        // Add item
        $cartItem = array(
            'dishID' => $order->dishID,
            'dishName' => $order->dishName,
            'quantity' => $order->quantity,
            'price' => $order->price,
            'total' => $order->price * $order->quantity
        );
        $cart[$key] = $cartItem;
        return true;
    }

    return false;
}

function update_cart_item($key, $quantity, &$cart) {
    $quantity = (int)$quantity;
    if (isset($cart[$key])) {
        if ($quantity <= 0) {
            unset($cart[$key]);
        } else {
            $cart[$key]['quantity'] = $quantity;
            $cart[$key]['total'] = $cart[$key]['price'] * $quantity;
        }
    }
}

function get_cart_subtotal($cart) {
    $subtotal = 0;
    foreach ($cart as $item) {
        $subtotal += $item['total'];
    }
    return number_format($subtotal, 2);
}
?>