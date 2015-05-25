<%-- 
    Document   : table
    Created on : 2015-5-23, 21:57:55
    Author     : apple
--%>

<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="./image/analysis.jpg">

        <title>Programmer Assistor</title>

        <!-- Bootstrap core CSS -->
        <link href="./css/bootstrap.min.css" rel="stylesheet">

        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <script src="./js/ie-emulation-modes-warning.js"></script>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Custom styles for this template -->
        <link href="./css/show.css" rel="stylesheet">
    </head>
    <body>
        <div class="navbar-wrapper">
            <div class="container">
                <nav class="navbar navbar-inverse navbar-static-top" role="navigation">
                    <div class="container">
                        <div class="navbar-header">
                            <a class="navbar-brand" href="#">Programmer Assistor</a>
                        </div>
                        <div id="navbar" class="navbar-collapse collapse">
                            <ul class="nav navbar-nav">
                                <li><a href="home.html">Home </a></li>
                                <li><a href="#about">Help </a></li>
                                <li><a href="#contact">About </a></li>

                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Styles <span class="caret"></span></a>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="#">Table</a></li>
                                        <li><a href="show_frequency.jsp?topicKeys=<%= request.getParameter("topicKeys")%>&wordCounts=<%= request.getParameter("wordCounts")%>">Topics Frequency</a></li>
                                        <li><a href="style_bubble.jsp?topicKeys=<%= request.getParameter("topicKeys")%>&wordCounts=<%= request.getParameter("wordCounts")%>">Bubble Chart</a></li>
                                    </ul>
                                </li>

                            </ul>
                        </div>
                    </div>
                </nav>

            </div>
        </div>
        <!-- Marketing messaging and featurettes
           ================================================== -->
        <!-- Wrap the rest of the page in another container to center all the content. -->

        <div class="container marketing">
            <div class="row featurette show_divider">
                <h4>Your input path of file that generated by MALLET:</h4>
                <p>--output-topic-keys: <%= request.getParameter("topicKeys")%></p>
                <p>--word-topic-counts-file: <%= request.getParameter("wordCounts")%></p>
                <p class="illustrate">If you want to input again, please click <a href="show.html">here</a></p>
                <br/>
                <h3>Table:</h3>
                <br/>
                <table class="table table-striped">
                    <tr>
                        <th>No.</th>
                        <th>Topic</th>
                    </tr>
                    <%
                        int index = -1;
                        JSONObject json = new JSONObject(request.getAttribute("topicsJson").toString());
                        JSONArray parent = json.getJSONArray("parent");

                        for (int i = 0; i < parent.length(); ++i) {
                            ++index;
                            JSONObject topicGroup = parent.getJSONObject(i);
                            JSONArray children = topicGroup.getJSONArray("children");
                    %>
                    <tr>
                        <td>
                            <%=index%>
                        </td>
                        <td>
                            <%

                                for (int j = 0; j < children.length(); ++j) {
                                    JSONObject topic = children.getJSONObject(j);
                                    String name = topic.getString("name");
                            %>

                            <%=name%> &nbsp;
                            <%
                                }
                            %>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>


        </div><!-- /.container -->

        <!-- Bootstrap core JavaScript
================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="./js/jquery.min.js"></script>
        <script src="./js/bootstrap.min.js"></script>
        <script src="./js/docs.min.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="./js/ie10-viewport-bug-workaround.js"></script>
        <!--custom js -->
        <script src="./js/home.js"></script>
    </body>
</html>
