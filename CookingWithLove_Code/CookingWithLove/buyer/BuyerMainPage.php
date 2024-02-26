
<?php
require '../model/init.php';


if (isset($_SESSION['accountID']) && isset($_SESSION['accountType']) && isset($_SESSION['userName'])) {
    $accountID = $_SESSION['accountID'];
    $accountType = $_SESSION['accountType'];
    $userName = $_SESSION['userName'];
 } 
?>
<?php require '../view/header.php'; ?>


<?php require '../view/buyermainpage.php'; ?>
<?php require '../view/footer.php'; ?>