<?php
$con = mysqli_connect("localhost","youtubefelh","12345","youtube","3306");

if (mysqli_connect_errno())
{
    $json=array("allapot"=>0,"uzenet"=>"Sikertelen kapcsolódás");
}

$adatok =$_POST["json"];
$obj = json_decode($adatok,true);

$nev = mysqli_real_escape_string($con,$obj["nev"]);
$email = mysqli_real_escape_string($con,$obj["email"]);
$jelszo = mysqli_real_escape_string($con,$obj["jelszo"]);

$sql=mysqli_query($con,"INSERT INTO `emberek`(`nev`, `email`, `jelszo`) VALUES ('$nev','$email','$jelszo')");

if ($sql){
    $json=array("allapot"=>1,"uzenet"=>"Sikeres regisztráció");
}else{
    $json=array("allapot"=>0,"uzenet"=>"Sikertelen regisztráció");
}

mysqli_close($con);
header('Content-type: application/json');
echo json_encode($json);