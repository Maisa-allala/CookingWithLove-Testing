

<?php
 require '../model/init.php';


$conn = require '../model/db.php';


$dishes = Dish::getAll($conn, $_SESSION['accountID']);




?>




<div>

<?php require '../view/header.php'; ?>
<?php require '../view/welcomeuser.php'; ?>
<div class = "order-container">
<div class="instructions"><span style="color: blue; font-style: italic; font-size: large; font-weight: bold; margin-left: 30%;">Click on the image to edit or delete the dish.</span></div>
<?php if(empty($dishes)): ?>
    <p>No dishes found.</p>
    <?php else: ?>
        <div class="card-container">
     
            <?php foreach($dishes as $dish): ?>
            
                <div class="card">
               

                <a href="/cookingwithlove/seller/UpdateDish.php?dishID=<?= $dish['dishID']; ?>" class="card-link">
                  <div class="image">
                  <img src="../<?=  htmlspecialchars($dish['photoPath']); ?>">
       <!-- <img src="../images/salad.jpg"> -->
    </div>
                        
    <div class="dish_Name">
      <h3> <?=  htmlspecialchars($dish['dishName']); ?></h3>
      <p>Availability: <?=  htmlspecialchars($dish['status']); ?><p>
      <p>Status: <?=  htmlspecialchars($dish['is_published'])=== 'Y' ? "Published" : "UnPublished"; ?><p>
    </div>
    </a>      
            </div>
       
<?php endforeach; ?>
           
            </div>
<?php endif;?>
            </div>
</div>
<?php require '../view/footer.php'; ?>