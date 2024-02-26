<?php

class User{

  public $accountID;
  public $first_name;
  public $last_name;
  public $phone;
  public $email;
  public $street;
  public $zip;
  public $accountType;
  public $username;
  public $password;
  public $confirm_password;
  public $dob;
  public $errors = [];



/**
 * Get the User record based on the accountID
 * @param object $conn Connection to database
 * @param integer $accountID the user ID

 * @return mixed An object of this class, or null if not found
 */
public static function getByAccountID($conn, $accountid){

  $sql = "SELECT *
  FROM user_account
  WHERE accountID = :accountID";

$stmt = $conn->prepare($sql);

$stmt->bindValue(':accountID', $accountid, PDO::PARAM_INT);
$stmt->setFetchMode(PDO::FETCH_CLASS, 'User');

if ($stmt->execute()) {

return $stmt->fetch();
}


}




/**
 * Update the user with its current property values
 * @param object $conn Connection to the database
 * @return boolean True if the update was successful, false otherwise
 */

 public function updateuser($conn){
 

  $sql = "UPDATE user_account SET  
  firstName = :first_name, lastName = :last_name, email = :email, phone = :phone, street = :street, zip = :zip, DOB = :DOB where accountID = :accountID ";
  $stmt = $conn->prepare($sql);

  $stmt->bindValue(':first_name', $this->first_name, PDO::PARAM_STR);
  $stmt->bindValue(':last_name', $this->last_name, PDO::PARAM_STR);
  $stmt->bindValue(':DOB', $this->dob, PDO::PARAM_STR); 
  $stmt->bindValue(':street', $this->street, PDO::PARAM_STR); 
  $stmt->bindValue(':email', $this->email, PDO::PARAM_STR);
  $stmt->bindValue(':phone', $this->phone, PDO::PARAM_STR);
  $stmt->bindValue(':zip', $this->zip, PDO::PARAM_STR); 
  $stmt->bindValue(':accountID', $this->accountID, PDO::PARAM_INT); 
   return $stmt->execute();



}


 /**
     * Authenticate a user by username and password
     *
     * @param object $conn Connection to the database
     * @param string $username Username
     * @param string $password Password
     *
     * @return boolean True if the credentials are correct, null otherwise
     */
   
    public static function authenticate($conn, $username, $password, $newPassword = null)
    {
        $sql = "SELECT *
                FROM user_account
                WHERE username = :username";

        $stmt = $conn->prepare($sql);
        $stmt->bindValue(':username', $username, PDO::PARAM_STR);

        $stmt->setFetchMode(PDO::FETCH_CLASS, 'User');

        $stmt->execute();

        if ($user = $stmt->fetch()) {
           if($newPassword !== null){
            //Update the password
            $hashPassword = password_hash($newPassword, PASSWORD_DEFAULT);
            $updateSql = "UPDATE user_account SET password = :password WHERE userName = :username";
            $updateStmt = $conn->prepare($updateSql);
            $updateStmt->bindValue(':username', $username, PDO::PARAM_STR);
            $updateStmt->bindValue(':password', $hashPassword, PDO::PARAM_STR);
            $updateStmt->execute();
            return 'Password updated successfully';
           }

            elseif(password_verify($password, $user->password)){
              return $user;
            }
            else {
              // Password doesn't match
              return 'Password does not match';
          }

        }
        else {
          // User not found
          return 'User not found';
      }

        return null;
    }



   


/**
 * Insert a new user with its current property values
 * @param object $conn Connection to the database
 * @return boolean True if the insert was successful, false otherwise
 */

 public function create($conn){
  if($this->validate()){
    $this->password = password_hash($this->password, PASSWORD_DEFAULT);
   
    $sql = "INSERT INTO user_account (userName, password, 	accountType, firstName, lastName, email, phone, street, zip, DOB, accountRequestedDate, accountActiveDate, is_active  )
 VALUES(  :username,   :password , :accountType, :first_name, :last_name, :email, :phone, :street, :zip , DATE_FORMAT(:DOB, '%Y-%m-%d'), DATE_FORMAT(NOW(), '%Y-%m-%d'), Null, 'N'	)";
      $stmt = $conn->prepare($sql);
   

 $stmt->bindValue(':first_name', $this->first_name, PDO::PARAM_STR);    
 $stmt->bindValue(':last_name', $this->last_name, PDO::PARAM_STR);
   $stmt->bindValue(':username',  $this->username, PDO::PARAM_STR);
   $stmt->bindValue(':password',  $this->password, PDO::PARAM_STR);
   $stmt->bindValue(':accountType',  $this->accountType, PDO::PARAM_STR);
   $stmt->bindValue(':email', $this->email, PDO::PARAM_STR);
   $stmt->bindValue(':phone', $this->phone, PDO::PARAM_STR);
   $stmt->bindValue(':street', $this->street, PDO::PARAM_STR); 
   $stmt->bindValue(':zip', $this->zip, PDO::PARAM_STR); 
   $stmt->bindValue(':DOB', $this->dob, PDO::PARAM_STR); 
    


    if($stmt->execute()){
     
       return true;
    }

  }
  else {
      return false;
  }

}














protected function validate(){
  
  if(!(($this->password)  === ($this->confirm_password))){
    $this->errors[]= "Passwords do no match";
  }

     

 
  
  


  

  return empty($this->errors);

}


public static function adminDisplayUsers($conn,  $is_active ){
        
  

  $sql = 'SELECT *
        FROM user_account
                 WHERE is_active = :is_active and accountType != "Admin"';
    
  $stmt = $conn->prepare($sql);        

  $stmt->bindValue(':is_active', $is_active, PDO::PARAM_STR);

  $stmt->execute();

    // Fetch all rows as objects of the Orders class     
  $users = $stmt->fetchAll(PDO::FETCH_CLASS, 'User');
   
  return $users;
  

}


public static function ActiveUserAccount($conn, $accountID,  $is_active){
 
  $sql = "UPDATE user_account SET  
  is_active = :is_active, accountActiveDate = DATE_FORMAT(NOW(), '%Y-%m-%d') where accountID = :accountID";
  $stmt = $conn->prepare($sql);
  $stmt->bindValue(':accountID', $accountID, PDO::PARAM_INT);
  $stmt->bindValue(':is_active', $is_active, PDO::PARAM_STR);

 
   return $stmt->execute();

    
}


public static function getUserByAccountID($conn, $accountID){

  $sql = 'SELECT * FROM user_account 
     WHERE accountID = :accountID and accountType != "Admin"';


  $stmt = $conn->prepare($sql);

  $stmt->bindValue(':accountID', $accountID, PDO::PARAM_INT);
 


  $stmt->execute();
   

  
return $stmt->fetchAll(PDO::FETCH_ASSOC);


}

}