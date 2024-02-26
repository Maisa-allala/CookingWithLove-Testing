
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   
    
    <link rel="stylesheet" href="/cookingwithlove/css/StylePage12.css">
    <link rel="stylesheet" href="/cookingwithlove/css/menulistStyle.css">
    <link rel="stylesheet" href="/cookingwithlove/css/card.css">
    <link rel="stylesheet" href="/cookingwithlove/css/errorStyle.css">
    <link rel="stylesheet" href="/cookingwithlove/css/cartStyle.css">
    <link rel="stylesheet" href="/cookingwithlove/css/orderStyle.css">
    <link rel="stylesheet" href="/cookingwithlove/css/product-preview.css">
    <link rel="stylesheet" href="/cookingwithlove/css/user-dish-detail.css">
    <link rel="stylesheet" href="/cookingwithlove/css/buttons.css">


  <!-- font awesome cdn link  -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
  
    <title>Cooking With Love</title>
</head>
<body>
    <header>
    
   
 

    <header>
    <div class="header">
        <img src="/cookingwithlove/images/resize_logo.jpg" alt="Logo" width="9%">
        <div class="header-right">
            <?php if(Auth::isLoggedIn()): ?>
                <?php if($_SESSION['accountType'] == "Buyer"): ?>
                    <div><a href="/cookingwithlove/buyer/BuyerMainPage.php">Home</a></div>
                <?php elseif($_SESSION['accountType'] == "Seller"): ?>
                    <div><a href="/cookingwithlove/seller/SellerMainPage.php">Home</a></div>
                <?php elseif($_SESSION['accountType'] == "Admin"): ?>
                    <div><a href="/cookingwithlove/admin/AdminMainPage.php">Home</a></div>
                <?php else: ?>
                    <div><a href="/cookingwithlove/">Home</a></div>
                <?php endif; ?>
                <div><a href="/cookingwithlove/contact.php">Contact Us</a></div>
                <div><a href="/cookingwithlove/about.php">About Us</a></div>
                <div><a href="/cookingwithlove/logout.php">Log out</a></div>
            <?php else: ?>
                <div><a href="/cookingwithlove/">Home</a></div>
                <div><a href="/cookingwithlove/contact.php">Contact Us</a></div>
                <div><a href="/cookingwithlove/about.php">About Us</a></div>
                <div><a href="/cookingwithlove/login.php">Log in</a></div>
            <?php endif; ?>
        </div>
    </div>

    <?php if(isset($_SESSION['is_active']) && $_SESSION['is_active'] === 'N'): ?>
        <span class="error-toast">Your account is currently not active.<br/>
            Please contact our support team for assistance in resolving this issue.<br/>
            For now, you can browse our website as a guest.
        </span>
    <?php endif; ?>
</header>


    <main>
  
  
