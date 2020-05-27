# Api
My first simple rest-api project

**Before use API _read the documentation_, create 2 tables in your DB and edit config file (src/yardani/config/Config.java)**

```
package yardani.config;

public class Config {
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3307/api?serverTimezone=Europe/Moscow&useSSL=false";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "12345";

    public static final String ENCRYPT_KEY = "MyKey";
}
```

Queries (**Do not change names**):
```
CREATE TABLE `users` (
`id` int NOT NULL,
`username` VARCHAR(45) NOT NULL,
`password` VARCHAR(60) NOT NULL,
`hasaccess` int NOT NULL,
`token` VARCHAR(120) NOT NULL,
PRIMARY KEY (`token`));
```
```
CREATE TABLE `api_table` (
`id` VARCHAR(45) NOT NULL,
`firstname` VARCHAR(45) NOT NULL,
`lastname` VARCHAR(45) NOT NULL,
`country` VARCHAR(45) NOT NULL,
`city` VARCHAR(45) NOT NULL,
`street` VARCHAR(45) NOT NULL,
`housenum` VARCHAR(45) NOT NULL,
`email` VARCHAR(60) NOT NULL,
PRIMARY KEY (`id`));
```