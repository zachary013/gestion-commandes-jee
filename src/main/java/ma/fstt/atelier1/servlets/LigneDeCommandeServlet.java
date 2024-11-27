package ma.fstt.atelier1.servlets;

import ma.fstt.atelier1.entities.LigneDeCommande;
import ma.fstt.atelier1.service.LigneDeCommandeService;
import ma.fstt.atelier1.service.CommandeService;
import ma.fstt.atelier1.service.ProduitService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/lignes-de-commande/*")
public class LigneDeCommandeServlet extends HttpServlet {

    @Inject
    private LigneDeCommandeService ligneDeCommandeService;

    @Inject
    private CommandeService commandeService;

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
                request.setAttribute("lignesDeCommande", ligneDeCommandeService.getAllLignesDeCommande());
                request.getRequestDispatcher("/WEB-INF/get-lignes-de-commande.jsp").forward(request, response);
                break;
            case "/add":
                request.setAttribute("commandes", commandeService.getAllCommandes());
                request.setAttribute("produits", produitService.getAllProduits());
                request.getRequestDispatcher("/WEB-INF/add-ligne-de-commande.jsp").forward(request, response);
                break;
            case "/edit":
                Long id = Long.parseLong(request.getParameter("id"));
                request.setAttribute("ligneDeCommande", ligneDeCommandeService.getLigneDeCommandeById(id));
                request.setAttribute("commandes", commandeService.getAllCommandes());
                request.setAttribute("produits", produitService.getAllProduits());
                request.getRequestDispatcher("/WEB-INF/edit-ligne-de-commande.jsp").forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        LigneDeCommande ligneDeCommande = new LigneDeCommande();
        ligneDeCommande.setCommande(commandeService.getCommandeById(Long.parseLong(request.getParameter("commandeId"))));
        ligneDeCommande.setProduit(produitService.getProduitById(Long.parseLong(request.getParameter("produitId"))));
        ligneDeCommande.setQuantite(Integer.parseInt(request.getParameter("quantite")));
        ligneDeCommande.setPrix(Double.parseDouble(request.getParameter("prix")));

        try {
            if ("/edit".equals(action)) {
                ligneDeCommande.setId(Long.parseLong(request.getParameter("id")));
                ligneDeCommandeService.updateLigneDeCommande(ligneDeCommande);
            } else {
                ligneDeCommandeService.saveLigneDeCommande(ligneDeCommande);
            }
            response.sendRedirect(request.getContextPath() + "/lignes-de-commande/list");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("ligneDeCommande", ligneDeCommande);
            request.setAttribute("commandes", commandeService.getAllCommandes());
            request.setAttribute("produits", produitService.getAllProduits());
            request.getRequestDispatcher("/WEB-INF/" + ("/edit".equals(action) ? "edit" : "add") + "-ligne-de-commande.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            ligneDeCommandeService.deleteLigneDeCommande(id);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}