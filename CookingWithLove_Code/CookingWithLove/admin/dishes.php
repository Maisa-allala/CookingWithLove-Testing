<?php
require '../model/init.php';
$conn = require '../model/db.php';

$title = $_GET['title'];
$is_published = $_GET['is_published']; 
$status = isset($_GET['status']) ? $_GET['status'] : '';



$dishes = Dish::adminDisplayDishes($conn, $is_published);

?>




<?php require '../view/header.php'; ?>
<?php require '../view/welcomeuser.php'; ?>


<?php require '../view/dishes.php'; ?>

<?php require '../view/footer.php'; ?>