package com.example.demo.Tools;

import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.Payment;
import com.example.demo.service.OrderService;
import com.example.demo.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ChatTools {
	
	private final OrderService orderService;
	private final PaymentService paymentService;
	
	
	@Tool(description = "Retrieve the current fulfillment or delivery status of an order using its order ID.")
    public OrderStatus getOrderStatus(
            @ToolParam(description = "The unique numeric identifier of the order (e.g., 1024)") Long orderId) {
        return orderService.getOrderStatus(orderId);
    }
    
    @Tool(description = "Fetch a list of failed payment transactions for a specific customer within a given number of past days.")
    public List<Payment> getFailedPayments(
            @ToolParam(description = "The unique numeric identifier of the customer") Long customerId,
            @ToolParam(description = "The lookback period in days to check for failed payments (e.g., 7 for the last week, 30 for the last month)") int lastNDays) {
        return paymentService.getFailedPayments(customerId, lastNDays);
    }
    
    @Tool(description = "Cancel an existing order by its ID if it has not yet been processed or shipped.")
    public String cancelOrder(
            @ToolParam(description = "The unique numeric identifier of the order to cancel") Long orderId) {
        return orderService.cancelOrder(orderId);
    }

}
