package com.company;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eladlavi on 26/04/2017.
 */
public class MainServlet extends javax.servlet.http.HttpServlet {


    Map<String, User> users;

    @Override
    public void init() throws ServletException {
        super.init();
        users = new HashMap<>();
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String queryString = request.getQueryString();
        if(queryString == null)
            return;
        Map<String, String> qs = new HashMap<>();
        String[] keyValuePairs = queryString.split("&");
        for(String keyValuePair : keyValuePairs){
            String[] keyValue = keyValuePair.split("=");
            if(keyValue.length != 2)
                continue;
            qs.put(keyValue[0], keyValue[1]);
        }
        String action = qs.get("action");
        if(action == null)
            return;
        String username = qs.get("username");
        String password = qs.get("password");
        if(username == null || password == null)
            return;
        User user;
        switch (action){
            case "makeMove":
                if(!validatedUser(username, password))
                    return;
                user = users.get(username);
                int cell = -1;
                try{
                    cell = Integer.valueOf(qs.get("cell"));
                }catch (Exception ex){
                    return;
                }
                Board.MoveResult moveResult = user.getBoard().makeMove(user, cell);
                String moveResultAsString = "";
                switch (moveResult){
                    case VALID_MOVE:
                        moveResultAsString = "valid_move";
                        break;
                    case INVALID_MOVE:
                        moveResultAsString = "invalid_move";
                        break;
                }
                response.getWriter().write(moveResultAsString);
                break;
            case "getBoard":
                if(!validatedUser(username, password))
                    return;
                user = users.get(username);
                response.getWriter().write(user.getBoard().toString());
                break;
            case "login":
                response.getWriter().write(
                        validatedUser(username,password) ? "ok" : "failure");
                user = users.get(username);
                if(user != null) {
                    user.setInLobby(true);
                    user.setPartner(null);
                }
                break;
            case "signup":
                boolean success = false;
                synchronized (users){
                    if(!users.containsKey(username)){
                        users.put(username, new User(username, password));
                        success = true;
                    }
                }
                user = users.get(username);
                user.setInLobby(true);
                user.setPartner(null);
                response.getWriter().write(success ? "ok" : "failure");
                break;
            case "getPartners":
                if(!validatedUser(username, password))
                    return;
                StringBuilder stringBuilder = new StringBuilder();
                Collection<User> allUsers = users.values();
                User askingUser = users.get(username);
                askingUser.updateTimeStamp();
                if(askingUser.getPartner() != null) {
                    response.getWriter().write("chosen" + askingUser.getPartner().getUserName());
                    return;
                }
                for(User u : allUsers){
                    if(u.getUserName().equals(username))
                        continue;
                    if(u.getPartner()==null && u.isInLobby()) {
                        stringBuilder.append(u.getUserName() + "&");
                    }
                }
                if(stringBuilder.length() > 0){
                    stringBuilder.deleteCharAt(stringBuilder.length()-1);
                }
                response.getWriter().write(stringBuilder.toString());
                break;
            case "choosePartner":
                if(!validatedUser(username, password))
                    return;
                User choosingUser = users.get(username);
                String partnerUserName = qs.get("partner");
                User partner = users.get(partnerUserName);
                if(partner == null)
                    return;
                boolean choosingSuccess = false;
                synchronized (users){
                    if(choosingUser.getPartner() == null
                            && partner.getPartner() == null
                            && choosingUser.isInLobby()
                            && partner.isInLobby()){
                        choosingUser.setPartner(partner);
                        partner.setPartner(choosingUser);
                        choosingUser.setInLobby(false);
                        partner.setInLobby(false);
                        choosingSuccess = true;
                    }
                }
                Board board = new Board(choosingUser, partner);
                choosingUser.setBoard(board);
                partner.setBoard(board);
                response.getWriter().write(choosingSuccess ? "ok" : "failure");
                break;

        }
    }

    private boolean validatedUser(String username, String password){
        User existingUser = users.get(username);
        if(existingUser == null)
            return false;
        return password.equals(existingUser.getPassword());
    }
}
