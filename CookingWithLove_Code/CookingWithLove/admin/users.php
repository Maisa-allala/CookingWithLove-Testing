<?php
require '../model/init.php';
$conn = require '../model/db.php';
$accountID = $_SESSION['accountID'];
$title = $_GET['title'];
$is_active = $_GET['is_active']; 
$status = isset($_GET['status']) ? $_GET['status'] : '';



$users = User::adminDisplayUsers($conn, $is_active);

?>




<?php require '../view/header.php'; ?>
<?php require '../view/welcomeuser.php'; ?>


<?php require '../view/users.php'; ?>

<?php require '../view/footer.php'; ?>