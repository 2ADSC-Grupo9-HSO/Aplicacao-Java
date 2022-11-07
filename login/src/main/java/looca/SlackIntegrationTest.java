/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package looca;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import com.github.britooo.looca.api.group.processos.ProcessosGroup;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.util.Conversor;
import java.util.List;

/**
 *
 * @author User
 */
public class SlackIntegrationTest {

    private static String webHookUrl = "https://hooks.slack.com/services/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";
    private static String OAuthToken = "xoxb-4299325918501-4327140979589-PiJXduaP4u6kRqzQGQv2F53C";
    private static String SlackChannel = "hsochannel";

    public static void main(String[] args) {
        sendMessageProcessadorToSlack("Valor de processador ultrapassado!");
        sendMessageRamToSlack("Valor de memória RAM ultrapassada!");
        sendMessageDiscoToSlack("Valor de disco ultrapassado!");
    }

    public static void sendMessageProcessadorToSlack(String mensagem) {
        Looca looca = new Looca();

        if (looca.getProcessador().getUso() >= 70.0) {
            try {
                StringBuilder msgbuilder = new StringBuilder();
                msgbuilder.append(mensagem);

                Payload payload = Payload.builder().channel(SlackChannel).text(msgbuilder.toString()).build();

                WebhookResponse wbResp = Slack.getInstance().send(webHookUrl, payload);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendMessageRamToSlack(String mensagem) {
        Looca looca = new Looca();

        if (looca.getMemoria().getEmUso() >= 70.0) {
            try {
                StringBuilder msgbuilder = new StringBuilder();
                msgbuilder.append(mensagem);

                Payload payload = Payload.builder().channel(SlackChannel).text(msgbuilder.toString()).build();

                WebhookResponse wbResp = Slack.getInstance().send(webHookUrl, payload);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendMessageDiscoToSlack(String mensagem) {
        Looca looca = new Looca();

        Double disco = 100.0;

        if (disco >= 99.0) {
            try {
                StringBuilder msgbuilder = new StringBuilder();
                msgbuilder.append(mensagem);

                Payload payload = Payload.builder().channel(SlackChannel).text(msgbuilder.toString()).build();

                WebhookResponse wbResp = Slack.getInstance().send(webHookUrl, payload);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
