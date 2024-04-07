mainController，ToolUtil中配置shell路徑

文件存放在/home/data/user/userid/中，userid每次查询时生成，并返回给用户，用户用此ID可重新查看结果

mainController中使用shell,R命令

source /etc/profile

download page:
/home/tomcat/apache-tomcat-8.5.35/webapps/TFTGdbfile
tar -zxvf download.tar.gz
cp -r home /home/tomcat/apache-tomcat-8.5.35/webapps/TFTG/WEB-INF/classes/static/file/home