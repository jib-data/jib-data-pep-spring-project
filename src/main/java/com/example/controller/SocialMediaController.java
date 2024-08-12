package com.example.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController 
public class SocialMediaController {

    MessageService messageService;
    AccountService accountService;

    @Autowired
    SocialMediaController(MessageService messageService, AccountService accountService){
        this.messageService = messageService;
        this.accountService = accountService;
    }

    @PostMapping("register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account){        
        Account existingAccount = accountService.getAccountByUsername(account.getUsername());
        if (existingAccount == null){
            Account  registeredAccount = accountService.registerAccount(account);
            
            if (registeredAccount == null){
                return ResponseEntity.status(400).body(null);
            } else{
                return ResponseEntity.status(200).body(registeredAccount);
            }
        } 

        return ResponseEntity.status(409).body(null);
    }

    @PostMapping("login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account account){
        Account existingAccount = accountService.loginAccount(account);
        if (existingAccount != null){
            return ResponseEntity.ok(existingAccount);
        }
        return ResponseEntity.status(401).body(null);
    }

    @PostMapping("messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        Message createdMessage = messageService.createMessage(message);
        if (createdMessage != null){
            return ResponseEntity.ok(createdMessage);
        }
        return ResponseEntity.status(400).body(null);
    }

    @GetMapping("messages")
    public ResponseEntity<List<Message>> getAllMessages(){
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    @GetMapping("messages/{message_id}")
    public ResponseEntity<Message> getMessageByMessageId(@PathVariable Integer message_id){
        Message message = messageService.getMessageByMessageID(message_id);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("messages/{message_id}")
    public ResponseEntity<Integer> deleteMessageByMessageId(@PathVariable Integer message_id){
        Integer affectedRows = messageService.deleteByMessageID(message_id);
        if (affectedRows> 0){
            return ResponseEntity.ok(affectedRows);
        }
        return ResponseEntity.ok(null);
    }

    @PatchMapping("messages/{message_id}")
    public ResponseEntity<Integer> updateMessageById(@PathVariable Integer message_id, @RequestBody Message message){
        Integer rowsAffected = messageService.updateMessageByMessageID(message.getMessageText(), message_id);

        if (rowsAffected >0){
            return ResponseEntity.ok(rowsAffected);
        }
        return ResponseEntity.status(400).body(null);
    }

    @GetMapping("accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getMessageByAccountId(@PathVariable Integer account_id){
        List<Message> messages = messageService.getAllMessagesGivenAccountID(account_id);        
        return ResponseEntity.ok(messages);
    }
}
