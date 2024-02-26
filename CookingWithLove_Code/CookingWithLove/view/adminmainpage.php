<div class="grid-container">
<?php require 'welcomeuser.php'; ?>
<?php



$activeUserTitle = "Active Users";
$activeUser_is_active = 'Y';

$notActiveUserTitle = "Not Active Users";
$notActiveUser_is_active = 'N';

$publishedDishTitle = "Published Dishes";
$publishedDish_is_published = 'Y';

$unpublishedDishTitle = "Unpublished Dishes";
$unpublishedDish_is_published = 'N';

?>

<a href="/cookingwithlove/admin/users.php?title=<?= $activeUserTitle; ?>&is_active=<?= $activeUser_is_active ; ?>&status=<?= $activeUserTitle === 'Active Users' ? 'Active' : 'Not Active'; ?>" button class="button active_user">View Active Users</button></a><br>
<a href="/cookingwithlove/admin/users.php?title=<?= $notActiveUserTitle; ?>&is_active=<?= $notActiveUser_is_active; ?>&status=<?= $notActiveUserTitle === 'Not Active Users' ? 'Not Active' : 'Active'; ?>" button class="button active_user_req">User Activation Requests</button></a><br>


<a href="/cookingwithlove/admin/dishes.php?title=<?= $publishedDishTitle; ?>&is_published=<?= $publishedDish_is_published; ?>&status=<?= $publishedDishTitle === 'Published Dishes' ? 'Published' : 'Unpublished'; ?>" button class="button publish_dish">View Published Dishes</button></a><br>
<a href="/cookingwithlove/admin/dishes.php?title=<?= $unpublishedDishTitle; ?>&is_published=<?= $unpublishedDish_is_published ; ?>&status=<?= $unpublishedDishTitle === 'Unpublished Dishes' ? 'UnPublished' : 'Published'; ?>" button class="button unpublish_dish">View Unpublished Dishes</button></a>

</div> 