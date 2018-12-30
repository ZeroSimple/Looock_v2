package com.zerosimple.looock;

import android.app.Activity;

import java.io.DataOutputStream;


public class RootLock {
        public static boolean upgradeRootPermission(String pkgCodePath) {

            Process process = null;
        DataOutputStream os = null;
        try {
            String cmd="chmod 26 " + pkgCodePath;
            process = Runtime.getRuntime().exec("su"); //切换到root帐号
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }
        try {
            return process.waitFor()==0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}

