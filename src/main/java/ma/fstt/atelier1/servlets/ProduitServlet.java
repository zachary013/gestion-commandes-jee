package ma.fstt.atelier1.servlets;

import ma.fstt.atelier1.entities.Produit;
import ma.fstt.atelier1.service.ProduitService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/produits/*")
public class ProduitServlet extends HttpServlet {

    @Inject
    private ProduitService produitService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/list";
        }

        switch (action) {
            case "/list":
                request.setAttribute("produits", produitService.getAllProduits());
                request.getRequestDispatcher("/WEB-INF/get-produits.jsp").forward(request, response);
                break;
            case "/add":
                request.getRequestDispatcher("/WEB-INF/add-produit.jsp").forward(request, response);
                break;
            case "/edit":
                Long id = Long.parseLong(request.getParameter("id"));
                request.setAttribute("produit", produitService.getProduitById(id));
                request.getRequestDispatcher("/WEB-INF/edit-produit.jsp").forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        Produit produit = new Produit();
        produit.setNom(request.getParameter("nom"));
        produit.setDescription(request.getParameter("description"));
        produit.setPrix(Double.parseDouble(request.getParameter("prix")));
        produit.setStock(Integer.parseInt(request.getParameter("stock")));

        try {
            if ("/edit".equals(action)) {
                produit.setId(Long.parseLong(request.getParameter("id")));
                produitService.updateProduit(produit);
            } else {
                produitService.saveProduit(produit);
            }
            response.sendRedirect(request.getContextPath() + "/produits/list");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("produit", produit);
            request.getRequestDispatcher("/WEB-INF/" + ("/edit".equals(action) ? "edit" : "add") + "-produit.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            produitService.deleteProduit(id);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}