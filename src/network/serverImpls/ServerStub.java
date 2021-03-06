package network.serverImpls;

import java.util.Scanner;

import bomberman.protocol.message.client.JoinGame;
import network.Message;
import network.server.Server;
import network.server.ServerApplicationInterface;

/**
 * Die Klasse ServerStub dient dazu die Teilkomponente application.server isoliert von der
 * Netzwerkschicht testen zu k�nnen. Hierzu muss ein Objekt von dieser Klasse erzeugt werden.
 * Dabei wird ein separater Thread gestartet, welche den Empfang von Meldungen von den Clients
 * simuliert. In der Methode deliverMessageToServer() kann programmiert werden, welche Meldungen
 * dies konkret sind. Die Methode wartet mit der Auslieferung, bis der Benutzer die Taste
 * ENTER dr�ckt. Dies kann je nach Situation hilfreich sein beim Testen. Die Methode kann aber
 * beliebig an die Bed�rfnisse des Tests angepasst werden.
 * Der serverImpls-Komponente selber stehen die send-Methode und die broadcast-Methode zur Verf�gung,
 * um eine Antwort an einen oder alle Clients zu simulieren. Die Meldung wird lediglich auf der
 * Konsole ausgegeben.
 *
 * @author Andres Scheidegger
 *
 */
public class ServerStub extends Server {

  /**
   * Konstruktor. Muss von der serverImpls-Komponente aufgerufen werden. Startet einen separaten Thread
   * zum Simulieren der Meldungen, welche von den Clients empfangen werden.
   * @see #deliverMessagesToServer()
   * @param serverApplication Eine Referenz auf ein Objekt der serverImpls-Komponente, welches das
   *                          ServerApplicationInterface implementiert.
   */
  public ServerStub(ServerApplicationInterface serverApplication) {
    super(serverApplication);
    Thread clientThread = new Thread() {
      @Override
      public void run() {
        deliverMessagesToServer();
      }
    };
    clientThread.start();
  }

  /**
   * Kann von der serverImpls-Komponente aufgerufen werden, um den Versand einer Meldung an
   * einen bestimmten Client zu simulieren.
   */
  @Override
  public void send(Message message, String connectionId) {
    System.out.println(connectionId + '\n' + message);
  }

  /**
   * Kann von der serverImpls-Komponente aufgerufen werden, um den Versand einer Meldung an
   * alle Clients zu simulieren.
   */
  @Override
  public void broadcast(Message message) {
    System.out.println(message);
  }

  /**
   * Wartet auf eine Eingabe von der Konsole (ENTER-Taste). Anschliessend werden die weiteren
   * Anweisungen ausgef�hrt. Z.B. k�nnen Meldungen an den serverImpls ausgeliefert werden, indem die Methode
   * handleMessage() bei der serverImpls-Komponente aufgerufen wird. Diese Methode kann den Bed�rfnissen
   * des Tests angepasst werden.
   */
  private void deliverMessagesToServer() {
    System.out.println("Dr�cken Sie ENTER, um die Auslieferung der Meldungen an den serverImpls zu starten.");
    // Hier k�nnen Sie die Meldungen, welche nach dem Dr�cken der ENTER-Taste an Ihren serverImpls gesendet
    // werden sollen programmieren. Z.B.:
    Message message = new JoinGame("Player1");
    serverApplication.handleMessage(message, "connection1");
    Message message1 = new JoinGame("Player2");
    Message message2 = new JoinGame("Player3");
    Message message3 = new JoinGame("Player4");
    Message message4 = new JoinGame("Player5");

    serverApplication.handleMessage(message4,"connection2");
    serverApplication.handleMessage(message1, "connection3");
    serverApplication.handleMessage(message2, "connection4");
    serverApplication.handleMessage(message3, "connection5");
    System.out.println("haaallooo");
  }
}
