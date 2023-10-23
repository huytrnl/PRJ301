<%-- 
    Document   : cart
    Created on : Oct 13, 2023, 5:18:19 AM
    Author     : huytr
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="huytl.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            //1. Customer goes to cart place
            if (session != null) {//explicit
                //2. Customer takes his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Customer gets items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
        %>
        <form action="DispatchServlet">

            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (String key : items.keySet()) {
                    %>
                    <tr>
                        <td>
                            <%= ++count%>
                        </td>
                        <td>
                            <%= key%>
                        </td>
                        <td>
                            <%= items.get(key)%>
                        </td>
                        <td>
                            <input type="checkbox" name="chkItem"
                                   value="<%= key%>"/>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    <tr>
                        <td colspan="3">
                            <a href="bookStore.html">Add more book to your cart</a>
                            <input type="submit" name="btAction" value="Remove selected item"/>
                        </td>
                    </tr>




                </tbody>
            </table>
            <%
                        }//end items 
                    }//end
                }//end
%>
        </form>

        <h2>
            No exist
        </h2>
    </body>
</html>
