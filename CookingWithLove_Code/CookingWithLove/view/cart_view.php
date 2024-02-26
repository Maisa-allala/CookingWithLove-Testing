<div class="grid-container">
    <div class = "cartform">
        <h1>Your Cart</h1>
        <?php if (empty($_SESSION['cart']) || count($_SESSION['cart']) == 0) : ?>
            <p>There are no items in your cart.</p>
        <?php else: ?>
            <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
                <input type="hidden" name="action" value="update">
                <table>
                    <tr id="cart_header">
                        <th class="left">Dish</th>
                        <th class="right">Price</th>
                        <th class="right">Quantity</th>
                        <th class="right">Item Total</th>
                    </tr>

                    <?php foreach ($_SESSION['cart'] as $key => $item) :
                        $price = number_format($item['price'], 2);
                        $total = number_format($item['price'] * $item['quantity'], 2);
                    ?>
                        <tr>
                            <td>
                                <?php echo htmlspecialchars($item['dishName']); ?>
                            </td>
                            <td class="right">
                                $<?php echo $price; ?>
                            </td>
                            <td class="right">
                                <input type="text" class="cart_qty" name="newqty[<?php echo $key; ?>]" value="<?php echo $item['quantity']; ?>">
                            </td>
                            <td class="right">
                                $<?php echo $total; ?>
                            </td>
                        </tr>
                    <?php endforeach; ?>
                    <tr id="cart_footer">
                        <td colspan="3"><b>Subtotal</b></td>
                        <td>$<?php echo get_cart_subtotal($_SESSION['cart']); ?></td>
                    </tr>
                  
                    <tr>
                        <td colspan="4" class="right">
                            
    <!-- Update Cart Form -->
                        <form action="cart.php" method="post">
        <!-- Your cart update fields and submit button -->
        <input type="submit" name="updateCart" value="Update Cart">
    </form>
                        </td>
                    </tr>

    
                    <tr>
                        <td colspan="4" class="right">
                       <!-- Checkout Form -->
    <form action="cart.php" method="post">
        <!-- Your checkout fields and submit button -->
        <input type="submit" name="checkout" value="Checkout">
    </form>
                        </td>
                    </tr>




                </table>
                <p>Click "Update Cart" to update quantities in your cart. Enter a quantity of 0 to remove an item.</p>
            </form>
        <?php endif; ?>
        <p><a href="../buyer/AllCategories.php">Add Item</a></p>
        <!-- <p><a href="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>?action=empty_cart">Empty Cart</a></p> -->
                    </div>
                    </div>