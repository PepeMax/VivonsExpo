<?php

class DBConnex extends PDO
{
    private static $instance;

    public static function getInstance()
    {
        if (!self::$instance) {
            self::$instance = new DBConnex();
        }
        return self::$instance;
    }

    public function __construct()
    {
        try {
            parent::__construct(Param::$dsn, Param::$user, Param::$pass);
        } catch (Exception $e) {
            echo $e->getMessage();
            die("Impossible de se connecter. ");
        }
    }
}
