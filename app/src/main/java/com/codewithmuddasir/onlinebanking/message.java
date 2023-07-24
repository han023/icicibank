package com.codewithmuddasir.onlinebanking;

class message{
    String userid;
    String sender;
    String msg;
    String time;
    String type;

    message(String userId, String message, String sender, String time, String type){
        this.userid = userId;
        this.msg = message;
        this.sender = sender;
        this.time = time;
        this.type = type;
    }
}
