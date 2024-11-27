package ma.fstt.atelier1.servlets;

import ma.fstt.atelier1.entities.Client;
import ma.fstt.atelier1.service.ClientService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/clients/*")
public class ClientServlet extends HttpServlet {

    @Inject
    private ClientService clientService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/list";
        }

        switch (action) {
            case "/list":
                request.setAttribute("clients", clientService.getAllClients());
                request.getRequestDispatcher("/WEB-INF/get-clients.jsp").forward(request, response);
                break;
            case "/add":
                request.getRequestDispatcher("/WEB-INF/add-client.jsp").forward(request, response);
                break;
            case "/edit":
                Long id = Long.parseLong(request.getParameter("id"));
                request.setAttribute("client", clientService.getClientById(id));
                request.getRequestDispatcher("/WEB-INF/edit-client.jsp").forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();

        Client client = new Client();
        client.setNom(request.getParameter("nom"));
        client.setPrenom(request.getParameter("prenom"));
        client.setTelephone(request.getParameter("telephone"));
        client.setEmail(request.getParameter("email"));

        try {
            if ("/edit".equals(action)) {
                client.setId(Long.parseLong(request.getParameter("id")));
                clientService.updateClient(client);
            } else {
                clientService.saveClient(client);
            }
            response.sendRedirect(request.getContextPath() + "/clients/list");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("client", client);
            request.getRequestDispatcher("/WEB-INF/" + ("/edit".equals(action) ? "edit" : "add") + "-client.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            clientService.deleteClient(id);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}