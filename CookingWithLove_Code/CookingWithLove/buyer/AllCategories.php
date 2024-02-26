
<?php  require '../model/init.php'; 
require 'cart_functions.php';




$conn = require '../model/db.php';
$order = new Order();

$categories= Dish::getCategories($conn);

$dishType = isset($_GET['description']) ? $_GET['description'] : "Salad";


if ($dishType) {
    $dishes = Dish::getByDishType($conn, $dishType);
}





if($_SERVER['REQUEST_METHOD'] == "POST"){

   
    
    if(isset($_POST['addToCart'])){
       
// Check if the dishID is set in the submitted form

if (isset($_POST['dishID'])) {

    $selectedDish = Dish::getByDishID($conn, $_POST['dishID']);
   
if($selectedDish){

    $order->deliveryType = $_POST['deliveryType'];
    $order->quantity = trim(filter_input(INPUT_POST, 'quantity'));
    $order->accountID = $_SESSION['accountID'];
    $order->dishID = $selectedDish['dishID'];
    $order->dishName = $selectedDish['dishName'];
     $order->price = $selectedDish['price'];

   
    
     // Create a cart array if needed
     if (empty($_SESSION['cart'])) {
        $_SESSION['cart'] = array();
    }

   
  

    if (add_to_cart($order, $_SESSION['cart'])) {
        echo '<script>alert("Item added to cart successfully."); window.location.href = "/cookingwithlove/buyer/cart.php";</script>';

      
    } else {
        echo '<script>alert("Failed to add item to cart.") ;</script>';
    }
} else {
    echo '<script>alert("Failed to add item to cart.") ;</script>';
}




}
}
}

?>




<div>

<?php require '../view/header.php'; ?>
<?php require '../view/footer.php'; ?>
<?php if(empty($categories)): ?>
    <p>No categories found.</p>
    <?php else: ?>
        
        <div class="nav-container">
        <div class="nav nav-tabs menuList ml-auto" id="nav-tab">

            <?php foreach($categories as $category): ?>
                <?php
        // Check if the current category is the first one in the loop
       
        $isActive = isset($_GET['description']) ? ($category['description'] === $_GET['description']) : ($category['description'] === "Salad");
    
        ?> 
                <a href="?description=<?= $category['description']; ?>" class="card-link">
           
             
               
                <button class="menuList nav-link <?= ($isActive ? 'active' : ''); ?>" >  
                        
                <?=  htmlspecialchars($category['description']); ?></button>
             
           
           
            </a>
<?php endforeach; ?>
           
            </div>   </div>
          
<?php endif;?>

</div>

<?php require '../view/welcomeuser.php'; ?>
  <?php if(empty($dishes)): ?>
      <p>No dishes found.</p>
      <?php else: ?>
          <div class="tab-content">
      
  
              <?php foreach($dishes as $dish): ?>
                 
                  <div class="card">
               
                  <div class="image">
     
       <img src="../<?=  htmlspecialchars($dish['photoPath']); ?>">
    </div>
                  <div class="dish_Name">
      <h3> <?=  htmlspecialchars($dish['dishName']); ?></h3>
    
      <span class="item_price"> Price : $ <?=  htmlspecialchars($dish['price']); ?></span>
      
    </div>   
  
    

                    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>"  method="post">
                    <div class="Add_item_cart">
                    <label for="quantity">Quantity:</label>
                    <input type="number" name="quantity" value="1" min="1" max="10"> 
                    </div>
                    <div class="Add_item_cart">
  
<label for="sellerName">Seller Name:</label>
<?=  htmlspecialchars($dish['userName']); ?>
</div>

                    <input type="hidden" name="dishID" value="<?= $dish['dishID']; ?>">
                    <button type="submit"name="addToCart" class="cart_button">Add to Cart</button>
                    </form>
  
                           </div>
             
  <?php endforeach; ?>
             
              </div>
  <?php endif;?>
  
  </div>

 
<?php require '../view/footer.php'; ?>