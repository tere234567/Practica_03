
package com.emergentes.coleccion_de_libros;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controller_categoria", urlPatterns = {"/Controller_categoria"})
public class Controller_categoria extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        categoria objcategoria = new categoria();
        int id;
        int pos;
        String opcion = request.getParameter("op");
        String op = (opcion != null)? request.getParameter("op"):"view";
        if (op.equals("nuevo")){
            HttpSession ses = request.getSession();
            Gestorcategorias agenda = (Gestorcategorias) ses.getAttribute("agenda");
            objcategoria.setId(agenda.obtieneIdcategoria());
            request.setAttribute("op", op);
            request.setAttribute("miTarea", objcategoria);
            request.getRequestDispatcher("editarcategoria.jsp").forward(request, response);
            
        }
        if (op.equals("modificar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            Gestorcategorias agenda = (Gestorcategorias) ses.getAttribute("agenda");
            pos = agenda.ubicarcategoria(id);
            objcategoria = agenda.getLista().get(pos);
            request.setAttribute("op", op);
            request.setAttribute("miTarea", objcategoria);
            request.getRequestDispatcher("editarcategoria.jsp").forward(request, response);
        }
        if (op.equals("eliminar")){
            id = Integer.parseInt(request.getParameter("id"));
             HttpSession ses = request.getSession();
            Gestorcategorias agenda = (Gestorcategorias) ses.getAttribute("agenda");
            pos = agenda.ubicarcategoria(id);
            agenda.eliminarcategoria(pos);
            ses.setAttribute("agenda", agenda);
            response.sendRedirect("categorias.jsp");
        }
        if(op.equals("view")){
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        categoria objcategoria = new categoria();
        int pos;
        String op = request.getParameter("op");
        if(op.equals("grabar")){
            objcategoria.setId(Integer.parseInt(request.getParameter("id")));
            objcategoria.setCategoria(request.getParameter("categoria"));
            HttpSession ses = request.getSession();
            Gestorcategorias agenda = (Gestorcategorias) ses.getAttribute("agenda");
            String opg=request.getParameter("opg");
            if (opg.equals("nuevo")){
                agenda.insertarcategoria(objcategoria);
            }else{
                pos=agenda.ubicarcategoria(objcategoria.getId());
                agenda.modificarcategoria(pos, objcategoria);
            }
            ses.setAttribute("agenda", agenda);
            response.sendRedirect("categorias.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
