package com.example.demo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Tools.ChatTools;
import com.example.demo.service.OrderService;
import com.example.demo.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

	private final OrderService orderService;
	private final PaymentService paymentService;
	private final ChatClient chatClient;
	private final ChatTools chatTools;
	
	
	@PostMapping
    public String chat(@RequestParam String message) {

        return chatClient.prompt()
                .system("""
                    You are an order management assistant.

                    Use the available tools to answer
                    questions about orders and payments.

                    Never invent order or payment data.

                   
                    """)
                .user(message)
                .tools(chatTools)
                .call()
                .content();
    }
	
}
