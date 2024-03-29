/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytl.servlet;

import huytl.cart.CartObject;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author huytr
 */
public class RemoveItemsFromCartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            //1. Customer goes to the cart place
            HttpSession session = request.getSession(false);
            if (session!=null){
                //2. Customer takes his/her cart (
                CartObject cart = (CartObject)session.getAttribute("CART");
                if (cart!= null) {
                    //3. Customer gets items (ngan chua do)
                    Map<String, Integer> items = cart.getItems();
                    if (items!=null) {
                        //4. Cust chooses all removing items
                        String[] selectedItems = request.getParameterValues("chkItem");
                        if (selectedItems != null){
                            //5. remove all items
                            for (String item: selectedItems){
                                cart.removeItemFromCart(item);
                            }
                            session.setAttribute("CART",cart);
                        }
                    }//end item have existed
                }//end cart has exist
            }//end cart place must exist
        } finally{
            //6. refresh --> call previous function again (View Your Cart)
            //-->
            String urlRewriting = "DispatchServlet"
                    + "?btAction=View cart";
            //trung ten parameter btAction
            response.sendRedirect(urlRewriting);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
