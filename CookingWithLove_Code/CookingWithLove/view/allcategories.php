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
           
        
          
<?php endif;?>