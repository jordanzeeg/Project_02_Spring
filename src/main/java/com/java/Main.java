package com.java;

import com.java.dao.FriendDao;
import com.java.dto.Friend;

public class Main {
    public static void main(String[] args) {
        FriendDao friendDao = new FriendDao();
        Friend friend = new Friend();
        friend.setUsername("Tester");
        friend.setPassword("Testpwe");
        friend.setLastName("will");
        friend.setFirstName("dyl");
        friend.setEmail("geg@gmail.com");

        friendDao.save(friend);
    }
}
