

<?php
 require 'model/init.php';

 $conn = require 'model/db.php';

  $dishType = $_GET['description'];
  
  $dishes = Dish::getByDishType($conn,  $dishType);
  
  ?>
  
  <div>
  
  <?php require 'view/header.php'; ?>
  <?php if(empty($dishes)): ?>
      <p>No dishes found.</p>
      <?php else: ?>
          <div class="card-container">
      
  
              <?php foreach($dishes as $dish): ?>
               
                  <div class="card">
               
                 
                  <h3 class="card-heading">  
                          
                  <?=  htmlspecialchars($dish['dishName']); ?></h3>
                  <p class="card-body"> <?=  htmlspecialchars($dish['description']); ?></p>
             
              </div>
          
  <?php endforeach; ?>
             
              </div>
  <?php endif;?>
  
  </div>