/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sptech.slack.integration.hso;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;

/**
 *
 * @author Pichau
 */
public class SlackIntegrationTest {

    private static String  webHookUrl = "https://hooks.slack.com/services/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";
    private static String  OAuthToken = "xoxb-4299325918501-4302313302899-coZeKzDvXNWpidFcx9uPYm4u";
    private static String  SlackChannel = "hsochannel";
    
    
    
    
    public static void main(String[] args) {
        sendMessageToSlack("Primeira mensagem usando o app do Slack novamente");
    }
    public static void sendMessageToSlack(String mensagem){
        try{
            StringBuilder msgbuilder = new StringBuilder();
            msgbuilder.append(mensagem);
        
            Payload payload = Payload.builder().channel(SlackChannel).text(msgbuilder.toString()).build();
        
            WebhookResponse wbResp = Slack.getInstance().send(webHookUrl ,payload);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
