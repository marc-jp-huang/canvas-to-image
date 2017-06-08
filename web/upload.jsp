<%@ page import="toolkit.UploadTool" %>
<%--
  Created by IntelliJ IDEA.
  User: Marc
  Date: 2017/6/8
  Time: 上午 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    toolkit.UploadTool upload = new toolkit.UploadTool(request);
    String msg = upload.checkUpload();
    String filename = upload.getParameter("_filename");
    if (msg.length() > 0) {
        out.println(msg);
    } else {
        //set upload path
        upload.setUploadDir(request.getSession().getServletContext().getRealPath("/uploads")+"\\");
        //out.println("Upload to:"+request.getSession().getServletContext().getRealPath("/uploads")+"\\"+" <br/>");
        //get text field
        //out.println("Text field:" + upload.getParameter("_filename")+ "<br/>");
        //start upload
        if (upload.isExtUpload("fileToUpload")){
            msg = upload.doUpload(upload.getUploadParameter("fileToUpload"),filename.substring(0, filename.lastIndexOf('.')));
        }

        if (msg.length() > 0) {
            out.println(msg);
        }else {
            out.println(filename);
        }
    }
%>