<?php
class Validate{

 

  
      public function validateemail($email){
    
        if($email == ''){
          $erroremail = "Email Address is required";
        }
        elseif(!filter_var($email, FILTER_VALIDATE_EMAIL)){
          $erroremail = "Invaild Email Address";
        }
        else {
          $erroremail = ""; 
        }
            
      
        return  $erroremail;
      
      }

      public function validatephone($phone){
    
        if($phone == ''){
          $errorphone = "Phone Number is required";
        }
        elseif(!preg_match('/^[[:digit:]]{3}-[[:digit:]]{3}-[[:digit:]]{4}$/' , $phone)){
          $errorphone = "Invalid phone number";
        }
        else {
          $errorphone= ""; 
        }
            
      
        return  $errorphone;
      
      }

      


      public function validatefname($fname){
    
        if($fname == ''){
          $errorfname = "First Name is required";
        }
        elseif(!preg_match("/^[a-zA-Z-']*$/" , $fname)){
          $errorfname = "Only letters";
        }
        else {
          $errorfname= ""; 
        }
            
      
        return  $errorfname;
      
      }

      public function validatelname($lname){
    
        if($lname== ''){
          $errorlname = "Last Name is required";
        }
        elseif(!preg_match("/^[a-zA-Z-']*$/" , $lname)){
          $errorlname = "Only letters";
        }
        else {
          $errorlname= ""; 
        }
            
      
        return  $errorlname;
      
      }

      public function validateaccounttype($acounttype){
    
        if($acounttype== "none"){
          $erroracounttype = "Acount Type is required";
        }
        
        else {
         $erroracounttype= ""; 
        }
            
      
        return   $erroracounttype;
      
      }    

 

      public function validateselect($select, $fieldname){
    
        if($select== "none"){
          $error = $fieldname. " is required";
        }
        
        else {
          $error = ""; 
        }
            
      
        return  $error ;
      
      }   

      public function validateaddress($address){
    
        if($address== ''){
          $erroraddress = "Address is required";
        }
        
        else {
         $erroraddress= ""; 
        }
            
      
        return   $erroraddress;
      
      }    

      public function validatezip($zip){
    
        if($zip== ''){
          $errorzip = "Zip code is required";
        }
        elseif(!preg_match("/^[[:digit:]]{5}(-[[:digit:]]{4})?$/" , $zip)){
          $errorzip = "Invalid zip code.";
        }
        else {
          $errorzip = ""; 
        }
            
      
        return $errorzip;
      
      }
      public function validatedob($dob){
    
        if($dob== ''){
          $errordob = "DOB is required";
        }
        // elseif(!preg_match("/^\d{4}-\d{2}-\d{2}$/" , $dob)){
          elseif(!preg_match("/^\d{4}-\d{2}-\d{2}$/" , $dob)){
          $errordob = "Invalid DOB code.";
        }
        else {
          $errordob = ""; 
        }
            
      
        return $errordob;
      
      }   

      public function validatepassword($password){
        $pattern = "/^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+{}\[\]:;<>,.?~\\-])([a-zA-Z0-9!@#$%^&*()_+{}\[\]:;<>,.?~\\-]){6,}$/";
        if($password== ''){
          $errorpassword = "Password is required";
        }
       elseif(!preg_match( $pattern , $password)){
         $errorpassword  = "Invalid password code. Password can be 6 to 8  characters in length, with at least one lowercase letter, one uppercase letter, and one symbol.";
       }
        else {
          $errorpassword  = ""; 
        }
            
      
        return  $errorpassword ;
      
      } 
      public function validatepasswordlogin($password){
        $pattern = "/^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+{}\[\]:;<>,.?~\\-])([a-zA-Z0-9!@#$%^&*()_+{}\[\]:;<>,.?~\\-]){6,}$/";
        if($password== ''){
          $errorpassword = "Password is required";
        }
      
        else {
          $errorpassword  = ""; 
        }
            
      
        return  $errorpassword ;
      
      }   

      public function validateconfirmpassword($confirmpassword, $password){
    
        if($confirmpassword== ''){
          $errorconfirmpassword = "Confirm password is required";
        }
        elseif($password !== $confirmpassword){
          $errorconfirmpassword = "Password and confirm password do not match.";
        }
        else {
          $errorconfirmpassword = ""; 
        }
            
      
        return $errorconfirmpassword;
      
      }   
      public function validateusernamelogin($username){
     
        if($username== ''){
          $errorusername = "Username is required";
        }
      
        else {
          $errorusername  = ""; 
        }
            
      
        return   $errorusername ;
      
      }   
      public function validateusername($username){
        $pattern = "/^[a-zA-Z0-9!@#$%^&*()-_=+{};:'\",.<>\/?\\\\|]{1,20}$/";
        if($username== ''){
          $errorusername = "Username is required";
        }
        elseif(!preg_match( $pattern , $username)){
          $errorusername  = "Invalid username code. Username can be a maximum of 20 characters and can include letters, digits, and symbols.";
          
        }
        else {
          $errorusername  = ""; 
        }
            
      
        return   $errorusername ;
      
      }   

      public function validatedishname($dishname){
        $input = $dishname;
        $pattern = "/^[a-zA-Z\s]{1,30}$/";
        if($dishname == ''){
          $errordishname = "Dish Name is required";
        }
        elseif(!preg_match($pattern, $input)){
          $errordishname = "Only letters and spaces, with a maximum length of 30 characters.";
        }
        else {
          $errordishname = ""; 
        }
            
      
        return  $errordishname;
      
      }    


      public function validatepublishDate($publishDate){
    
        if($publishDate== ''){
          $errorpublishDate = "Publish Date is required";
        }
        elseif(!preg_match("/^\d{4}-\d{2}-\d{2}$/" , $publishDate)){
          $errorpublishDate = "Invalid Publish Date code.";
        }
        else {
          $errorpublishDate = ""; 
        }
            
      
        return $errorpublishDate;
      
      }   
      public function validatedescription($description){
        $input = $description;
        // $pattern = '/^.{1,2000}$/';
        if($input == ''){
          $errordescription = "Description is required";
        }
        // elseif(!preg_match($pattern, $input)){
        //   $errordescription = "Maximum length of 250 characters.";
        // }
        else {
          $errordescription = ""; 
        }
            
      
        return  $errordescription;
      
      }    

      public function validateprice($price){
        $input = $price;
        $pattern = "/^\d+(\.\d{1,2})?$/";
        if($input == ''){
          $errorprice = "Price is required";
        }
        elseif(!preg_match($pattern, $input)){
          $errorprice = "Invalid price.";
        }
        else {
          $errorprice = ""; 
        }
            
      
        return  $errorprice;
      
      }    
      
}
?>