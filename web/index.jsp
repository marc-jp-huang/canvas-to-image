<%--
  Created by IntelliJ IDEA.
  User: Marc
  Date: 2017/6/8
  Time: 上午 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>canvas-to-image</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
     <link href="css/style.css" rel="stylesheet">
  </head>
  <body>
  <div class="container">
      <div class="row">
          <div class="col-xs-6 col-centered">
              <canvas id="myCanvas">
              </canvas>
          </div>
      </div>
      <div class="row">
          <div class="col-xs-12">
              <h2>Upload an image</h2>
              <div class="progress">
                  <div class="progress-bar" role="progressbar"></div>
              </div>
              <div class="panel panel-default">
                  <div class="panel-body">
                      <button class="btn btn-lg upload-btn" type="button">
                          <span class="glyphicon glyphicon-cloud-upload"></span>Upload Image
                      </button>
                      <input id="fileToUpload" type="file" name="fileToUpload">
                  </div>
              </div>
          </div>
      </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="js/script.js"></script>
  </body>
</html>
