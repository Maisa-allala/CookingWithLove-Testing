
<?php  require 'model/init.php'; 





$conn = require 'model/db.php';


$categories= Dish::getCategories($conn);

$dishType = isset($_GET['description']) ? $_GET['description'] : "Salad";


if ($dishType) {
    $dishes = Dish::getByDishType($conn, $dishType);
}


?>




<div>

<?php require 'view/header.php'; ?>

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


  <?php if(empty($dishes)): ?>
      <p>No dishes found.</p>
      <?php else: ?>
          <div class="tab-content">
      
  
              <?php foreach($dishes as $dish): ?>
                  <!-- <a href="/cookingwithlove/allDishes.php?dishID=<?= $dish['dishID']; ?>" class="card-link"> -->
                  <div class="card">
               
                  <div class="image">
       <!-- <img src="images/salad.jpg"> -->
       <img src="<?=  htmlspecialchars($dish['photoPath']); ?>">
    </div>
                  <div class="dish_Name">
      <h3> <?=  htmlspecialchars($dish['dishName']); ?></h3>
      <span class="item_price"> Price : $ <?=  htmlspecialchars($dish['price']); ?></span>
      
    </div>      
   
                           </div>
              <!-- </a> -->
  <?php endforeach; ?>
             
              </div>
  <?php endif;?>
  
  </div>


<?php require 'view/footer.php'; ?>