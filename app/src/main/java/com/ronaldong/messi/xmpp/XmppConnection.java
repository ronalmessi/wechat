package com.ronaldong.messi.xmpp;

import android.os.AsyncTask;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.ronaldong.messi.app.Constants;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Packet;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class XmppConnection {

    private static XmppConnection instance = new XmppConnection();
    private XMPPConnection xmppConnection;
    private XmppConnectionListener connectionListener;
    private XmppPacketListener packetListener;
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            1, 1, 300, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    /**
     * 单例模式
     *
     * @return
     */
    synchronized public static XmppConnection getInstance() {
        return instance;
    }

    /**
     * 创建XMPP连接
     */
    public XMPPConnection getConnection() {
        if (xmppConnection == null) {
            openConnection();
        }
        return xmppConnection;
    }

    /**
     * 打开XMPP连接
     */
    private boolean openConnection() {
        try {
            if (null == xmppConnection || !xmppConnection.isAuthenticated()) {
                // 配置连接
                ConnectionConfiguration config = new ConnectionConfiguration(
                        Constants.XMPP_SERVER_IP, Constants.XMPP_SERVER_PORT, Constants.XMPP_SERVER_DOMAIN);
                config.setCompressionEnabled(true);
//                XMPPConnection.DEBUG_ENABLED = true;
                config.setSASLAuthenticationEnabled(true);
                config.setReconnectionAllowed(true);
                config.setTruststoreType("AndroidCAStore");
                config.setTruststorePassword(null);
                config.setTruststorePath(null);
                xmppConnection = new XMPPConnection(config);
                // 添加连接监听
                connectionListener = new XmppConnectionListener();
                // 添加数据包监听
                packetListener = new XmppPacketListener();
                xmppConnection.addPacketListener(packetListener, new PacketFilter() {
                    @Override
                    public boolean accept(Packet packet) {
                        return true;
                    }
                });
                xmppConnection.addConnectionListener(connectionListener);
                xmppConnection.connect();// 连接到XMPP服务器
                return true;
            }
        } catch (XMPPException xe) {
            xe.printStackTrace();
            xmppConnection = null;
        }
        return false;
    }

    /**
     * 关闭XMPP连接
     */
    public void closeConnection() {
        if (xmppConnection != null) {
            //移除连接监听
            xmppConnection.removeConnectionListener(connectionListener);
            xmppConnection.removePacketListener(packetListener);
            if (xmppConnection.isConnected())
                xmppConnection.disconnect();
            xmppConnection = null;
        }
        Log.i("XmppConnection", "关闭XMPP连接");
    }


    /**
     * 登录
     *
     * @param account    登录帐号
     * @param password   登录密码:登陆账号后加上“iclass”
     * @param servername 服务器名
     * @return
     */
    public void login(final String account, final String password, final String servername) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    if (getConnection() == null&&getConnection().getUser()==null)
                        return false;
                    getConnection().login(account, password, servername);
                    Logger.d("Xmpp成功登录");
                    return true;
                } catch (XMPPException xe) {
                    xe.printStackTrace();
                }catch (IllegalStateException e){
                    Logger.d("MessagingService"+"---------"+"Already Logged in as " + xmppConnection.getUser());
                }

                return false;
            }
        }.executeOnExecutor(threadPoolExecutor);
    }

}