- win10 新安装的MYSQL8.0.26，使用navicat导入xx.sql数据时报错

  ```
  MySQL server has gone away
  ```

  解决：

  ```
  找到my.ini文件，修改文件中的max_allowed_packet属性值，我修改的是max_allowed_packet=500M，重启MYSQL，再试试导入即可。
  ```

- 
