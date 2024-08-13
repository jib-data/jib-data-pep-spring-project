package com.example.service;

// import org.hibernate.mapping.List;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;


@Service
public class MessageService {
    MessageRepository messageRepository;
    AccountRepository accountRepository;

    @Autowired
    MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message message){
        Account existingAccount = accountRepository.findById(message.getPostedBy()).orElse(null);        
        if (existingAccount != null 
          && message.getMessageText().length()> 0
            && message.getMessageText().length() <= 255){
                return messageRepository.save(message);
            }
        return null;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageByMessageID(Integer messageID){
        Message existingMessage =
         messageRepository.findById(messageID).orElse(null);
        
         return existingMessage;
    }


    public Integer deleteByMessageID(Integer messageID){
        return messageRepository.deleteByMessageID(messageID);        
    }
    
    public Integer updateMessageByMessageID(String messageText, Integer messageId){
        Message existingMessage = messageRepository.findById(messageId).orElse(null);
        if (existingMessage != null
            && messageText.length() > 0
                && messageText.length()<=255){
                    return messageRepository.updateMessageByMessageId(messageText, messageId);
                }
        return 0;
    }

    public List<Message> getAllMessagesGivenAccountID(Integer accountID){
        return messageRepository.findMessagesByPostedBy(accountID);
    }
}
