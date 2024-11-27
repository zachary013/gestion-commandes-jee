package ma.fstt.atelier1.servlets;

import ma.fstt.atelier1.entities.Commande;
import ma.fstt.atelier1.entities.LigneDeCommande;
import ma.fstt.atelier1.service.CommandeService;
import ma.fstt.atelier1.service.ClientService;
import ma.fstt.atelier1.service.ProduitService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/commandes/*")
public class CommandeServlet extends HttpServlet {

    @Inject
    private CommandeService commandeService;

    @Inject
    private ClientService clientService;

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
                request.setAttribute("commandes", commandeService.getAllCommandes());
                request.getRequestDispatcher("/WEB-INF/get-commandes.jsp").forward(request, response);
                break;
            case "/add":
                request.setAttribute("clients", clientService.getAllClients());
                request.setAttribute("produits", produitService.getAllProduits());
                request.getRequestDispatcher("/WEB-INF/add-commande.jsp").forward(request, response);
                break;
            case "/edit":
                Long id = Long.parseLong(request.getParameter("id"));
                request.setAttribute("commande", commandeService.getCommandeById(id));
                request.setAttribute("clients", clientService.getAllClients());
                request.setAttribute("produits", produitService.getAllProduits());
                request.getRequestDispatcher("/WEB-INF/edit-commande.jsp").forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        Commande commande = new Commande();
        commande.setDateCommande(new Date());
        commande.setClient(clientService.getClientById(Long.parseLong(request.getParameter("clientId"))));

        String[] produitIds = request.getParameterValues("produitId");
        String[] quantites = request.getParameterValues("quantite");

        ArrayList<LigneDeCommande> lignes = new ArrayList<>();
        for (int i = 0; i < produitIds.length; i++) {
            LigneDeCommande ligne = new LigneDeCommande();
            ligne.setProduit(produitService.getProduitById(Long.parseLong(produitIds[i])));
            ligne.setQuantite(Integer.parseInt(quantites[i]));
            ligne.setPrix(ligne.getProduit().getPrix());
            ligne.setCommande(commande);
            lignes.add(ligne);
        }
        commande.setLignes(lignes);

        try {
            if ("/edit".equals(action)) {
                commande.setId(Long.parseLong(request.getParameter("id")));
                commandeService.updateCommande(commande);
            } else {
                commandeService.saveCommande(commande);
            }
            response.sendRedirect(request.getContextPath() + "/commandes/list");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("commande", commande);
            request.setAttribute("clients", clientService.getAllClients());
            request.setAttribute("produits", produitService.getAllProduits());
            request.getRequestDispatcher("/WEB-INF/" + ("/edit".equals(action) ? "edit" : "add") + "-commande.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            commandeService.deleteCommande(id);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}