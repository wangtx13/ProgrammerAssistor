<%-- 
    Document   : index
    Created on : 2015-5-20, 9:53:57
    Author     : apple
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
    </head>
    <body>
        <form action="DemoServlet" method="POST" id="upload_form"
              enctype="multipart/form-data">
            <input type="file" id="files" name="files" webkitdirectory/>
            <br />          
            <input type="checkbox" name="common">去除普遍的无用基础类库信息，如util、lang等关键词（建议去除）<br />
            <input type="checkbox" onclick="enableList(this)" name="special">去除该源码所属类型的无用基础类库信息，如JHotDraw与图形相关，选择后将去除swing等关键词（建议去除）<br />
            <select id="list" name="library_type" disabled="true">
                <option value="null" selected="">no</option>
                <option value="draw">draw</option>                   
            </select>
            <br>
            <input type="button" onclick="checkFiles()" value="处理" />
        </form>
         <script src="./js/home.js"></script>
    </body>
</html>
