package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

// import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Message m WHERE m.messageId = ?1")
    Integer deleteByMessageID(Integer messageId);

    @Modifying
    @Transactional
    @Query("UPDATE Message m SET m.messageText =?1 WHERE m.messageId = ?2")
    Integer updateMessageByMessageId(String messageText, Integer messageId);

    @Query("SELECT m FROM Message m WHERE m.postedBy = ?1")
    List<Message> findMessagesByPostedBy(Integer postedBy);

}
