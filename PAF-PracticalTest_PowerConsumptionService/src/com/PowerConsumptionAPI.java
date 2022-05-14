package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class ItemsAPI
 */
@WebServlet("/PowerCon")
public class PowerConsumptionAPI extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static PowerConsumption PowerConObj;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PowerConsumptionAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /* create method */
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String acc_no = request.getParameter("accountNo");
        String inc_no = request.getParameter("invoiceNo");
        String name = request.getParameter("userName");
        int units = Integer.parseInt(request.getParameter("usedUnits"));
        float tot = Float.parseFloat(request.getParameter("totalCost"));

        String output = PowerConObj.createPowerConsumption(id, acc_no, inc_no, name, units, tot);

        response.getWriter().write(output);
    }

    /* update method */
    /**
     * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
     */
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map paras = getParasMap(request);

        int id = Integer.parseInt(paras.get("id").toString());
        String acc_no = paras.get("accountNo").toString();
        String inc_no = paras.get("invoiceNo").toString();
        String name = paras.get("userName").toString();
        int units = Integer.parseInt(paras.get("usedUnits").toString());
        float tot = Float.parseFloat(paras.get("totalCost").toString());

        String output = PowerConObj.updatePowerConsumption(id, acc_no, inc_no, name, units, tot);

        response.getWriter().write(output);

    }

    /* delete method */

    /**
     * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
     */
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map paras = getParasMap(request);
        String output = PowerConObj.deletePowerConsumption(Integer.parseInt(paras.get("id").toString()));
        response.getWriter().write(output);
    }

    /* get method */
    private static Map getParasMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
            String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
            scanner.close();
            String[] params = queryString.split("&");
            for (String param : params) {

                String[] p = param.split("=");
                map.put(p[0], p[1]);
            }
        } catch (Exception e) {
            System.out.print("Error!!");
        }
        return map;
    }
}
