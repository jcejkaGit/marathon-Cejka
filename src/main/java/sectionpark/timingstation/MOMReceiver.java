package sectionpark.timingstation;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class MOMReceiver {

    private static String user = ActiveMQConnection.DEFAULT_USER;
    private static String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

    public String thisisatest() {
        return "Test";
    }

    public MOMReceiver() {

        System.out.println("Receiver started.");

        // Create the connection.
        Session session = null;
        ActiveMQConnection connection = null;
        MessageConsumer consumer = null;

        try {

            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
            connection = (ActiveMQConnection) connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination des1 = session.createTopic("Timingstation: 1");
            Destination des2 = session.createTopic("Timingstation: 2");
            Destination des3 = session.createTopic("Timingstation: 3");

            MessageConsumer timing1 = session.createConsumer(des1);
            MessageConsumer timing2 = session.createConsumer(des2);
            MessageConsumer timing3 = session.createConsumer(des3);

            while(true) {

                TextMessage message = (TextMessage) timing1.receiveNoWait();

                if(message != null && !message.getText().equals(null)) {
                    System.out.println(message.getText().toString());
                    message.acknowledge();
                }


                message = (TextMessage) timing2.receiveNoWait();

                if(message != null && !message.getText().equals(null)) {
                    System.out.println(message.getText().toString());
                    message.acknowledge();
                }


                message = (TextMessage) timing3.receiveNoWait();

                if(message != null && !message.getText().equals(null)) {
                    System.out.println(message.getText().toString());
                    message.acknowledge();
                }

            }

        } catch (Exception e) {

            System.out.println("[MessageConsumer] Caught: " + e);
            e.printStackTrace();

        } finally {

            try { consumer.close(); } catch ( Exception e ) {}
            try { session.close(); } catch ( Exception e ) {}
            try { connection.close(); } catch ( Exception e ) {}

        }
        System.out.println("Receiver finished.");

    }

}