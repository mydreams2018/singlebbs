package cn.kungreat.singlebbs.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class InvalidUserCacle {
    public static final ReentrantReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();
    public static final ReentrantReadWriteLock.ReadLock READ_LOCK = READ_WRITE_LOCK.readLock();
    public static final ReentrantReadWriteLock.WriteLock WRITE_LOCK = READ_WRITE_LOCK.writeLock();

    public static final List<String> invalidUser = new ArrayList<>(64);

    public static boolean userIsInvalid(String account){
        boolean rt = false;
        READ_LOCK.lock();
        try {
            rt = invalidUser.contains(account);
        } finally {
            READ_LOCK.unlock();
        }
        return rt;
    }

    public static void addUserInvalid(String account){
        WRITE_LOCK.lock();
        try {
            if(!invalidUser.contains(account)){
                invalidUser.add(account);
            }
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    public static void addUsersInvalid(List<String> accounts){
        WRITE_LOCK.lock();
        try {
            for (String account : accounts) {
                if(!invalidUser.contains(account)){
                    invalidUser.add(account);
                }
            }
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    public static void removeUserInvalid(String account){
        WRITE_LOCK.lock();
        try {
            invalidUser.remove(account);
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    public static void clearUserInvalid(){
        WRITE_LOCK.lock();
        try {
            invalidUser.clear();
        } finally {
            WRITE_LOCK.unlock();
        }
    }

}
