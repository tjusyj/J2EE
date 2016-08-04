<?php  
  
$conn = oci_connect("system","123456","localhost/XE");
  
if (!$conn) {  
  
    $e = oci_error();  

    print htmlentities($e['message']);  
  
    exit;  
  
}else {  
  
    echo "连接oracle成功！";  
  
}  
  
?>  