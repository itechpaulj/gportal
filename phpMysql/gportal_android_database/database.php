<?php
	class Database
	{
		private static $dbName = 'gportal' ;
		private static $dbHost = 'localhost';
		//private static $dbUsername = 'root';
		//private static $dbUserPassword = '';
		private static $dbUsername = 'mobiledev'; // credentials for web hosting
		private static $dbUserPassword = 'S#f[QHgTBlm&'; // credentials for web hosting
		 
		private static $cont  = null;
		 
		public function __construct() {
			die('Init function is not allowed');
		}
		 
		public static function connect()
		{
		   // One connection through whole application
		   if ( null == self::$cont )
		   {     
                try
                {
                self::$cont =  new PDO( "mysql:host=".self::$dbHost.";"."dbname=".self::$dbName, self::$dbUsername, self::$dbUserPassword); 
                //echo 'connect';
                }
                catch(PDOException $e)
                {
                die($e->getMessage()); 
                }
		   }
		   return self::$cont;
		}
		 
		public static function disconnect()
		{
			self::$cont = null;
		}
	}
?>