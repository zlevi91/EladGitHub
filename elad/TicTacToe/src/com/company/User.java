package com.company;

/**
 * Created by eladlavi on 27/04/2017.
 */
public class User {
    private String userName, password;
    private User partner;
    private boolean inLobby;
    private long timeStamp;
    private Board board;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getPartner() {
        return partner;
    }

    public void setPartner(User partner) {
        this.partner = partner;
    }

    public boolean isInLobby() {
        return inLobby && (System.currentTimeMillis()-timeStamp < 2000L);
    }

    public void setInLobby(boolean inLobby) {
        this.inLobby = inLobby;
    }

    public void updateTimeStamp(){
        timeStamp = System.currentTimeMillis();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
