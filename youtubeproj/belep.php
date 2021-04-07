<?php

$con = mysqli_connect("localhost","youtubefelh","12345","youtube","3306");

if (mysqli_connect_errno())
{
    $json=array("allapot"=>0,"uzenet"=>"Sikertelen kapcsolódás");
}
$adatok =$_POST["json"];
$obj = json_decode($adatok,true);

$email = mysqli_real_escape_string($con,$obj["email"]);
$jelszo = mysqli_real_escape_string($con,$obj["jelszo"]);

$sql="SELECT * FROM `emberek` WHERE email='$email' and jelszo='$jelszo'";

$query=mysqli_query($con,$sql);
$sor=mysqli_fetch_assoc($query);

if (mysqli_num_rows($query)==1){
    $dbnev= $sor["nev"];

    $json=array("allapot"=>1,"uzenet"=>"Sikeres belépés","nev"=>$dbnev);
}else{
    $json=array("allapot"=>0,"uzenet"=>"Sikertelen belépés");
}
mysqli_close($con);
header('Content-type: application/json');
echo json_encode($json);