<?php

class Dish{
  public $dishID;
  public $dishName;
  public $description;
  public $price;
  public $status;
  public $dishType;
  public $accountID;
  public $publishDate;
  public $photoPath;   
  public $errors = [];

 function uploadImage(){
    //check if the Upload_pic file input in the dish form has an empty name aka checks if a file has been selected
  if (!empty($_FILES['Upload_pic']['name'])) {
   
    //where we will upload the photo to on our server
    $uploadDir = '../Uploads/';
    // $uploadDir = 'Uploads/';
    //constructs the full path by appending the original file name to the upload directory
    $uploadFile = $uploadDir . basename($_FILES['Upload_pic']['name']);
  
     // Check if the uploaded file is an image
     $imageInfo = getimagesize($_FILES['Upload_pic']['tmp_name']);
     if (!$imageInfo) {
         $this->errors[] = 'Uploaded file is not an image!';
         return false;
     }
  
   if (move_uploaded_file($_FILES['Upload_pic']['tmp_name'], $uploadFile)) {
      // if file was uploaded successfully, save the file path to the database
      // $this->photoPath = $uploadFile;
     
 return ltrim($uploadFile, '../');
    } else {
        $this->errors[] = 'File upload failed!';
        return false;
    }
  }
  }

  public function createDish($conn)  {
   
    $sql = "INSERT INTO dishes(dishName, description, price, status, dishType, accountID, publishDate, photoPath, DishRequestedDate, is_published)
    VALUES(:dishName, :description, :price, :status, :dishType, :accountID, Null, :photoPath, DATE_FORMAT(NOW(), '%Y-%m-%d'),  'N')";


$photoPath = $this->uploadImage();


$stmt = $conn->prepare($sql);
$stmt->bindValue(':dishName', $this->dishName, PDO::PARAM_STR);
$stmt->bindValue(':description', $this->description, PDO::PARAM_STR);
$stmt->bindValue(':price', $this->price, PDO::PARAM_INT);
$stmt->bindValue(':status', $this->status, PDO::PARAM_STR);
$stmt->bindValue(':dishType', $this->dishType, PDO::PARAM_STR);
$stmt->bindValue(':accountID', $this->accountID, PDO::PARAM_INT);
// $stmt->bindValue(':publishDate', $this->publishDate, PDO::PARAM_STR);
$stmt->bindValue(':photoPath', $photoPath , PDO::PARAM_STR);
    if($stmt->execute()){
     
        return true;
     }
 
   
   else {
       return false;
   }
    
  }


/**
 * Get the User record based on the accountID and dishID
 * @param object $conn Connection to database
 * @param integer $accountID the user ID and dish ID

 * @return mixed An object of this class, or null if not found
 */
public static function getByDishIDAccountID($conn, $accountid, $dishid){

  $sql = "SELECT *
  FROM dishes
  WHERE accountID = :accountID and dishID = :dishID";

$stmt = $conn->prepare($sql);
$stmt->bindValue(':dishID', $dishid, PDO::PARAM_INT);
$stmt->bindValue(':accountID', $accountid, PDO::PARAM_INT);
$stmt->setFetchMode(PDO::FETCH_CLASS, 'Dish');

if ($stmt->execute()) {

return $stmt->fetch();
}


}




/**
 * Get the User record based on the accountID and dishID
 * @param object $conn Connection to database
 * @param integer $accountID the user ID and dish ID

 * @return mixed An object of this class, or null if not found
 */
public static function getAll($conn, $accountid){

  $sql = "SELECT * FROM dishes WHERE accountID = :accountID";


  $stmt = $conn->prepare($sql);

  $stmt->bindValue(':accountID', $accountid, PDO::PARAM_INT);
 


  $stmt->execute();
   

  
return $stmt->fetchAll(PDO::FETCH_ASSOC);


}

/**
 * Get the Dish record based on the Dish Type
 * @param object $conn Connection to database
 * @param integer $dishType

 * @return mixed An object of this class, or null if not found
 */
public static function getByDishType($conn, $dishType){

  // $sql = "SELECT * FROM dishes WHERE dishType = :dishType";
  $sql = "SELECT * FROM dishes 
  inner join user_account on dishes.accountID = user_account.accountID
  WHERE dishType = :dishType
  and dishes.status = 'available' and dishes.is_published ='Y'  and  user_account.is_active = 'Y'";
  $stmt = $conn->prepare($sql);

  $stmt->bindValue(':dishType', $dishType, PDO::PARAM_STR);
 


  $stmt->execute();
   

  
return $stmt->fetchAll(PDO::FETCH_ASSOC);


}


public static function getByDishID($conn, $dishID){

  $sql = "SELECT * FROM dishes WHERE dishID = :dishID";


  $stmt = $conn->prepare($sql);

  $stmt->bindValue(':dishID', $dishID, PDO::PARAM_INT);
 


  $stmt->execute();
   

  
return $stmt->fetch(PDO::FETCH_ASSOC);


}
/**
 * Update the dish with its current property values
 * @param object $conn Connection to the database
 * @return boolean True if the update was successful, false otherwise
 */

 public function updatedish($conn){


  $sql = "UPDATE dishes SET  
  dishName = :dishName, description = :description,  price = :price, status = :status, dishType = :dishType, photoPath = :photoPath where accountID = :accountID and dishID = :dishID";
  $photoPath = $this->uploadImage();
  $stmt = $conn->prepare($sql);

  $stmt->bindValue(':dishName', $this->dishName, PDO::PARAM_STR);
  $stmt->bindValue(':description', $this->description, PDO::PARAM_STR);
  $stmt->bindValue(':price', $this->price, PDO::PARAM_INT);
  $stmt->bindValue(':status', $this->status, PDO::PARAM_STR);
  $stmt->bindValue(':dishType', $this->dishType, PDO::PARAM_STR);
  $stmt->bindValue(':accountID', $this->accountID, PDO::PARAM_INT);
  // $stmt->bindValue(':publishDate', $this->publishDate, PDO::PARAM_STR);
  $stmt->bindValue(':photoPath',  $photoPath, PDO::PARAM_STR);
  $stmt->bindValue(':dishID', $this->dishID, PDO::PARAM_INT);
   return $stmt->execute();



}



/**
 * Delete the dish with its current property values
 * @param object $conn Connection to the database
 * @return boolean True if the update was successful, false otherwise
 */

 public function deletedish($conn){


  $sql = "DELETE FROM dishes  where accountID = :accountID and dishID = :dishID";
  $stmt = $conn->prepare($sql);

 
  $stmt->bindValue(':accountID', $this->accountID, PDO::PARAM_INT);
 
  $stmt->bindValue(':dishID', $this->dishID, PDO::PARAM_INT);

   return $stmt->execute();



}


/**
 * Get All Dish info
 * @param object $conn Connection to database
 *

 * @return mixed An object of this class, or null if not found
 */
public static function getCategories($conn){

  $sql = "SELECT * FROM categories ORDER BY description ASC";


  $stmt = $conn->prepare($sql);


 


  $stmt->execute();
   

  
return $stmt->fetchAll(PDO::FETCH_ASSOC);


}



public static function adminDisplayDishes($conn,  $is_published ){
        
  

  $sql = 'SELECT *
        FROM dishes
        inner join user_account on dishes.accountID = user_account.accountID
                 WHERE dishes.is_published = :is_published and user_account.accountType != "Admin"';
    
  $stmt = $conn->prepare($sql);        

  $stmt->bindValue(':is_published', $is_published, PDO::PARAM_STR);

  $stmt->execute();

    // Fetch all rows as objects of the Dishes class     
    $dishes = $stmt->fetchAll(PDO::FETCH_CLASS, 'Dish');
   
  return $dishes;
  

}


public static function publishDish($conn, $dishID,  $is_published){
 
  $sql = "UPDATE dishes SET  
 is_published= :is_published, publishDate = DATE_FORMAT(NOW(), '%Y-%m-%d') where dishID = :dishID";
  $stmt = $conn->prepare($sql);
  $stmt->bindValue(':dishID', $dishID, PDO::PARAM_INT);
  $stmt->bindValue(':is_published', $is_published, PDO::PARAM_STR);

 
   return $stmt->execute();

    
}


public static function getDishBydishID($conn, $dishid){

  $sql = "SELECT *
  FROM dishes
  inner join user_account on dishes.accountID = user_account.accountID
  WHERE dishID = :dishID and accountType != 'Admin'";

$stmt = $conn->prepare($sql);
$stmt->bindValue(':dishID', $dishid, PDO::PARAM_INT);

$stmt->execute();
   
  
return $stmt->fetchAll(PDO::FETCH_ASSOC);


}

}



?>


