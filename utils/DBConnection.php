<?php

class DBConnection {

    public static function getConnection() {

        $host = "localhost";
        $db = "academia";
        $user = "root";
        $pass = "";

        try {
            $conn = new PDO("mysql:host=$host;dbname=$db", $user, $pass);
            return $conn;
        } catch (PDOException $e) {
            echo "Error: " . $e->getMessage();
        }
    }
}