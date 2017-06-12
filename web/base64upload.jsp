<%--
  Created by IntelliJ IDEA.
  User: Marc
  Date: 2017/6/12
  Time: 下午 03:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String upload_dir = request.getSession().getServletContext().getRealPath("/uploads")+"\\";
    try{
        toolkit.Base64UploadTool base64upload = new toolkit.Base64UploadTool(request,upload_dir);
    }catch(Exception ex){
        ex.printStackTrace();
    }
%>
