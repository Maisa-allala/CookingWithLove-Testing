<?php

class Order{
    public $orderNum;
    public $date;
    public $deliveryType;
    public $price;
    public $totalPrice;
    public $status;
    public $dishname;
    public $dishID;
    public $cancelled;
    public $quantity;
    public $accountID;
    public $delivery_complete;
    public $photoPath;
    public $order_complete;
    
    public function createOrder($conn)
    {
        try {
            $conn->beginTransaction();

            // Insert into orders table
            $sql = "INSERT INTO orders (date, deliveryType, totalPrice, cancelled, accountID, delivery_complete, order_complete)
                    VALUES (NOW(), :deliveryType, :totalPrice, 'N', :accountID, 'N' , 'N')";

            $stmt = $conn->prepare($sql);
            $stmt->bindValue(':deliveryType', $this->deliveryType, PDO::PARAM_STR);
            // $stmt->bindValue(':totalPrice', $this->calculateTotalPrice($conn, $this->accountID), PDO::PARAM_INT);
            $stmt->bindValue(':totalPrice', $this->totalPrice, PDO::PARAM_INT);
            $stmt->bindValue(':accountID', $this->accountID, PDO::PARAM_INT);

            if ($stmt->execute()) {
                $this->orderNum = $conn->lastInsertId();

                // Insert into orderItems table
//Loop through each item in the cart and create order items
                foreach ($_SESSION['cart'] as $cartItem) {
                    $this->dishID = $cartItem['dishID'];
                   
                    $this->dishname = $cartItem['dishName'];
                    $this->quantity = $cartItem['quantity'];
                    $this->price = $cartItem['price'];
                    $this->createOrderItem($conn);
                  
                  
                }



              

                $conn->commit();
                return true;
            } else {
                $conn->rollBack();
                return false;
            }
        } catch (PDOException $e) {
            $conn->rollBack();
            echo "Error: " . $e->getMessage();
            return false;
        }
    }

    private function createOrderItem($conn)
    {
        $sql = 'INSERT INTO orderItems (orderNum, dishID, dishName, quantity, price)
                VALUES (:orderNum, :dishID, :dishName, :quantity, :price)';

        $stmt = $conn->prepare($sql);
        $stmt->bindValue(':orderNum', $this->orderNum, PDO::PARAM_INT);
        $stmt->bindValue(':dishID', $this->dishID, PDO::PARAM_INT);
        $stmt->bindValue(':dishName', $this->dishname, PDO::PARAM_STR);
        $stmt->bindValue(':quantity', $this->quantity, PDO::PARAM_INT);
        $stmt->bindValue(':price', $this->price, PDO::PARAM_INT);

        return $stmt->execute();
    }
      

    function calculateTotalPrice($conn, $accountID) {
      try {
          $stmt = $conn->prepare("
              SELECT SUM(price * quantity) AS total
              FROM orderItems
              WHERE orderNum IN (SELECT orderNum FROM orders WHERE accountID = :accountID)
          ");
  
          $stmt->bindParam(':accountID', $accountID);
          $stmt->execute();
          
          $result = $stmt->fetch(PDO::FETCH_ASSOC);
          return $result['total'] ?? 0;
      } catch (PDOException $e) {
          // Handle the exception, log it, or return an error message
          echo "Error: " . $e->getMessage();
          return 0; // or handle the error in a way that makes sense for your application
      }
  }
    public static function displayAllOrders($conn){

      $sql = 'SELECT * FROM orders;';
        
      $stmt = $conn->prepare($sql);       
      $stmt->execute();

        // Fetch all rows as objects of the Orders class     
      $orders = $stmt->fetchAll(PDO::FETCH_CLASS, 'Orders');
       
      return $orders;
    
  }

  public static function getByOrderNum($conn, $orderNum){

    $sql = "SELECT * FROM orderItems 
     INNER JOIN dishes on orderItems.dishID= dishes.dishID
    WHERE orderNum = :orderNum";
  
  
    $stmt = $conn->prepare($sql);
  
    $stmt->bindValue(':orderNum', $orderNum, PDO::PARAM_INT);
   
  
  
    $stmt->execute();
     
  
    
  return $stmt->fetchAll(PDO::FETCH_ASSOC);
  
  
  }


  public static function getBuyerByOrderNum($conn, $orderNum){

    
        $sql =' SELECT DISTINCT *
        FROM orders as a
        INNER JOIN orderItems as b ON a.orderNum = b.orderNum
        INNER JOIN dishes as c ON b.dishID = c.dishID
        INNER JOIN user_account u ON a.accountID = u.accountID
        WHERE a.orderNum = :orderNum';
            $stmt = $conn->prepare($sql);
  
    $stmt->bindValue(':orderNum', $orderNum, PDO::PARAM_INT);
   
  
  
    $stmt->execute();
     
  
    
  return $stmt->fetchAll(PDO::FETCH_ASSOC);
  
  
  }

  public static function buyerDisplayOrders($conn,  $accountID, $cancelled, $deliveryComplete ){
        
  

    $sql = 'SELECT a.orderNum, a.date, a.deliveryType, a.totalPrice, a.cancelled, a.delivery_complete
          FROM orders as a
                   WHERE a.accountID = :accountID and a.cancelled = :cancelled and a.delivery_complete = :deliverycomplete';
      
    $stmt = $conn->prepare($sql);        
    $stmt->bindParam(':accountID', $accountID, PDO::PARAM_INT);   
    $stmt->bindValue(':cancelled', $cancelled, PDO::PARAM_STR);
    $stmt->bindValue(':deliverycomplete', $deliveryComplete, PDO::PARAM_STR);
    $stmt->execute();

      // Fetch all rows as objects of the Orders class     
    $orders = $stmt->fetchAll(PDO::FETCH_CLASS, 'Order');
     
    return $orders;
    
  
}
    


     
public static function sellerDisplayOrders($conn,  $accountID, $cancelled, $deliveryComplete, $orderComplete) {
  
  $sql = 'SELECT u.userName, a.orderNum, a.date, b.dishName, b.quantity, a.deliveryType, a.delivery_complete, a.totalPrice, a.cancelled, c.accountID
          FROM orders as a
          INNER JOIN orderItems as b ON a.orderNum = b.orderNum
          INNER JOIN dishes as c ON b.dishID = c.dishID
          INNER JOIN user_account u ON a.accountID = u.accountID
          WHERE c.accountID = :accountID and a.cancelled = :cancelled and a.delivery_complete = :deliverycomplete and a.order_complete = :orderComplete
          group by a.orderNum';

  $stmt = $conn->prepare($sql);
  $stmt->bindParam(':accountID', $accountID, PDO::PARAM_INT);   
  $stmt->bindValue(':cancelled', $cancelled, PDO::PARAM_STR);
  $stmt->bindValue(':deliverycomplete', $deliveryComplete, PDO::PARAM_STR);
  $stmt->bindValue(':orderComplete', $orderComplete, PDO::PARAM_STR);
  $stmt->execute();

  // Fetch all rows as objects of the stdClass (built-in class)     
  $orders = $stmt->fetchAll(PDO::FETCH_CLASS, 'Order');

  return $orders;
}
  
public static function updateOrders($conn, $orderNum, $cancelled){
        
      $sql = "UPDATE orders SET  
      cancelled = :cancelled where orderNum = :orderNum";
      $stmt = $conn->prepare($sql);
      $stmt->bindValue(':orderNum', $orderNum, PDO::PARAM_INT);
      $stmt->bindValue(':cancelled', $cancelled, PDO::PARAM_STR);
     
       return $stmt->execute();
    
        
    }


    public static function sellerupdateOrders($conn, $orderNum, $orderComplete){
        
        $sql = "UPDATE orders SET  
        order_complete = :orderComplete where orderNum = :orderNum";
        $stmt = $conn->prepare($sql);
        $stmt->bindValue(':orderNum', $orderNum, PDO::PARAM_INT);
        $stmt->bindValue(':orderComplete', $orderComplete, PDO::PARAM_STR);
       
         return $stmt->execute();
      
          
      }



     
      public static function  sellerupdatedeliveryCompletedOrders($conn, $orderNum, $deliveryComplete){
        
        $sql = "UPDATE orders SET  
        delivery_complete = :deliveryComplete where orderNum = :orderNum";
        $stmt = $conn->prepare($sql);
        $stmt->bindValue(':orderNum', $orderNum, PDO::PARAM_INT);
        $stmt->bindValue(':deliveryComplete', $deliveryComplete, PDO::PARAM_STR);
       
         return $stmt->execute();
      
          
      }

}



?>
