<%-- 
    Document   : search
    Created on : Sep 29, 2023, 8:58:13 AM
    Author     : huytr
--%>

<%@page import="java.util.List"%>
<%@page import="huytl.registration.RegistrationDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <%
            Cookie[] cookies = request.getCookies();
            if (cookies != null){
                Cookie newestCookie = cookies[cookies.length-1];
                String username = newestCookie.getName();
                //String password = newestCookie.getValue();
                %>
                
                    <%= "Welcome, " + username%>
                <%
            }
        %>
        <h1>Search Page</h1>
        <form action="DispatchServlet">
            Search Value <input type="text" name="txtSearchValue"
                                value="<%=request.getParameter("txtSearchValue")%>" /><br/>
            <input type="submit" value="Search" name="btAction" />
        </form><br/>
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result
                        = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
                //render
                if (result != null) {// has one or more records
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full name</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "DispatchServlet"
                                + "?btAction=delete"
                                + "&pk=" + dto.getUsername()
                                + "&lastSearchValue=" + searchValue;
                %>
            <form action="DispatchServlet" method="POST">
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUsername" 
                               value="<%=dto.getUsername()%>" />
                    </td>
                    <td>
                        <input type="text" name="txtPassword" 
                               value="<%=dto.getPassword()%>" />
                    </td>
                    <td>
                        <%= dto.getFullname()%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkAdmin" value="ON"
                               <%
                                   if (dto.isRole()){
                                       %>
                                       checked="checked"
                                       
                                       <%
                                   }
                               %>
                               />
                    </td>
                    <td>
                        <a href="<%= urlRewriting %>">Delete</a>
                    </td>
                    <td>
                        <input type="hidden" name="lastSearchValue"
                               value="<%=searchValue%>" />
                        <input type="submit" name="btAction" value="Update" />
                    </td>
                </tr>
                            </form>

                <%
                    }
                %>
            </tbody>
        </table>

        <%
        } else {//no record
        %>
        <h2>
            No record is matched!
        </h2>
        <%
                }
            }//end searchValue did not trigger from previous form
        %>
    </body>
</html>
