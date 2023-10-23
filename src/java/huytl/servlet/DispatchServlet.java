/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytl.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author huytr
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_CONTROLLER = "LoginServlet"; // 3. 
    private final String SEARCH_CONTROLLER = "SearchLastnameServlet";
    private final String DELETE_CONTROLLER = "DeleteAccountServlet";
    private final String UPDATE_CONTROLLER = "UpdateAccountServlet";
    private final String STARTUP_CONTROLLER = "StartUpServlet";
    private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemToCartServlet";
    private final String VIEW_CART_CONTROLLER = "cart.jsp";
    private final String REMOVE_ITEM_CONTROLLER = "RemoveItemsFromCartServlet";
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
        //1. Which button user clicked
        String button = request.getParameter("btAction");
        String url = LOGIN_PAGE;
        try {
            if (null == button) {//1. welcome-file trigger
                url = STARTUP_CONTROLLER;
            } else switch (button) {
                case "Login":
                    //2. mapping (user clicked Login button)
                    url = LOGIN_CONTROLLER;
                    break;
                case "Search":
                    url = SEARCH_CONTROLLER;
                    break;
                case "delete":
                    url = DELETE_CONTROLLER;
                    break;
                case "Update":
                    url = UPDATE_CONTROLLER;
                    break;
                case "Add item to cart":
                    url = ADD_ITEM_TO_CART_CONTROLLER;
                    break;
                case "View cart":
                    url = VIEW_CART_CONTROLLER;
                    break;
                case "Remove selected item":
                    url = REMOVE_ITEM_CONTROLLER;
                    break;    
                default:
                    break;
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
