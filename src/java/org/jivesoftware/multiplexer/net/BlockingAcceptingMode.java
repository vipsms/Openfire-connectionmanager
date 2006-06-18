/**
 * $RCSfile$
 * $Revision: $
 * $Date: $
 *
 * Copyright (C) 2006 Jive Software. All rights reserved.
 *
 * This software is published under the terms of the GNU Public License (GPL),
 * a copy of which is included in this distribution.
 */

package org.jivesoftware.multiplexer.net;

import org.jivesoftware.util.LocaleUtils;
import org.jivesoftware.util.Log;
import org.jivesoftware.multiplexer.ServerPort;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Accepts new socket connections and uses a thread for each new connection.
 *
 * @author Gaston Dombiak
 */
class BlockingAcceptingMode extends SocketAcceptingMode {

    protected BlockingAcceptingMode(ServerPort serverPort,
            InetAddress bindInterface) throws IOException {
        super(serverPort);
        serverSocket = new ServerSocket(serverPort.getPort(), -1, bindInterface);
    }

    /**
     * About as simple as it gets.  The thread spins around an accept
     * call getting sockets and creating new reading threads for each new connection.
     */
    public void run() {
        while (notTerminated) {
            try {
                Socket sock = serverSocket.accept();
                if (sock != null) {
                    Log.debug("Connect " + sock.toString());
                    SocketReader reader =
                            SocketReaderFactory.createSocketReader(sock, false, serverPort, true);
                    Thread thread = new Thread(reader, reader.getName());
                    thread.setDaemon(true);
                    thread.setPriority(Thread.NORM_PRIORITY);
                    thread.start();
                }
            }
            catch (IOException ie) {
                if (notTerminated) {
                    Log.error(LocaleUtils.getLocalizedString("admin.error.accept"),
                            ie);
                }
            }
            catch (Throwable e) {
                Log.error(LocaleUtils.getLocalizedString("admin.error.accept"), e);
            }
        }
    }
}
