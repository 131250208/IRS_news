# IRS_news
国科大信息检索导论大作业

## Usage
1. 先将irs_news.sql导入mysql数据库, 数据库名为irs_news
2. 修改项目中的applicationContex.xml文件中的数据库密码
3. 将IRS_news\src\com\irs_news\service\impl\JcsegServiceImpl.java中的voc_file和dir_path改为你系统下的绝对路径，分别对应项目下的voc.txt文件和lexicon文件夹
4. 部署到并启动tomcat服务器，访问http://localhost:8080/IRS_news/即可进入项目主页

## Team
王雨城  201728018670198 信工所四室物联网组 （队长）
张磊      201728018670262 信工所四室电磁组
杨寿国  201718018670071 信工所四室物联网组
方栋梁  201718018627001 信工所四室物联网组
杨阳      201718018627004 信工所四室电磁组
赵胜伟  201728018670267 信工所四室物联网组
